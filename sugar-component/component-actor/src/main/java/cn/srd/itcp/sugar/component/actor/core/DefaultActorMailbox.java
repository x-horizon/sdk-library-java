package cn.srd.itcp.sugar.component.actor.core;

import cn.srd.itcp.sugar.component.actor.event.ActorEvent;
import cn.srd.itcp.sugar.component.actor.id.ActorId;
import cn.srd.itcp.sugar.context.constant.core.ModuleConstant;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author wjm
 * @since 2023-03-23 20:09:17
 */
@Slf4j
@Data
public final class DefaultActorMailbox implements ActorMailbox {
    private static final boolean HIGH_PRIORITY = true;
    private static final boolean NORMAL_PRIORITY = false;

    private static final boolean FREE = false;
    private static final boolean BUSY = true;

    private static final boolean NOT_READY = false;
    private static final boolean READY = true;

    private final ActorSystem system;
    private final ActorSystemSettings systemSettings;
    private final ActorId selfId;
    private final ActorMailbox parentMailbox;
    private final Actor actor;
    private final Dispatcher dispatcher;
    private final ConcurrentLinkedQueue<ActorEvent<?>> highPriorityActorEventQueue = new ConcurrentLinkedQueue<>();
    private final ConcurrentLinkedQueue<ActorEvent<?>> normalPriorityActorEventQueue = new ConcurrentLinkedQueue<>();
    private final AtomicBoolean busy = new AtomicBoolean(FREE);
    private final AtomicBoolean ready = new AtomicBoolean(NOT_READY);
    private final AtomicBoolean destroyInProgress = new AtomicBoolean();
    private volatile ActorStopReason stopReason;

    public void initActor() {
        dispatcher.getExecutor().execute(() -> tryInit(1));
    }

    private void tryInit(int attempt) {
        try {
            log.debug("{}[{}] Trying to init actor, attempt: {}", ModuleConstant.ACTOR_SYSTEM, selfId, attempt);
            if (!destroyInProgress.get()) {
                actor.init(this);
                if (!destroyInProgress.get()) {
                    ready.set(READY);
                    tryProcessQueue(false);
                }
            }
        } catch (Throwable throwable) {
            log.error("{}[{}] Failed to init actor, attempt: {}", ModuleConstant.ACTOR_SYSTEM, selfId, attempt, throwable);
            int attemptIdx = attempt + 1;
            InitFailureStrategy strategy = actor.onInitFailure(attempt, throwable);
            if (strategy.isStop() || (systemSettings.getMaxActorInitAttempts() > 0 && attemptIdx > systemSettings.getMaxActorInitAttempts())) {
                log.error("{}[{}] Failed to init actor, attempt {}, going to stop attempts.", ModuleConstant.ACTOR_SYSTEM, selfId, attempt, throwable);
                stopReason = ActorStopReason.INIT_FAILED;
                destroy();
            } else if (strategy.getRetryDelay() > 0) {
                log.error("{}[{}] Failed to init actor, attempt {}, going to retry in attempts in {}ms", ModuleConstant.ACTOR_SYSTEM, selfId, attempt, strategy.getRetryDelay());
                log.error("{}[{}] Error", ModuleConstant.ACTOR_SYSTEM, selfId, throwable);
                system.getScheduler().schedule(() -> dispatcher.getExecutor().execute(() -> tryInit(attemptIdx)), strategy.getRetryDelay(), TimeUnit.MILLISECONDS);
            } else {
                log.error("{}[{}] Failed to init actor, attempt {}, going to retry immediately", ModuleConstant.ACTOR_SYSTEM, selfId, attempt);
                log.error("{}[{}] Error", ModuleConstant.ACTOR_SYSTEM, selfId, throwable);
                dispatcher.getExecutor().execute(() -> tryInit(attemptIdx));
            }
        }
    }

    private <T> void enqueue(ActorEvent<T> event, boolean isHighPriority) {
        if (!destroyInProgress.get()) {
            if (isHighPriority) {
                highPriorityActorEventQueue.add(event);
            } else {
                normalPriorityActorEventQueue.add(event);
            }
            tryProcessQueue(true);
        }
    }

    private void tryProcessQueue(boolean hasNewEvent) {
        if (ready.get() == READY) {
            if (hasNewEvent || !highPriorityActorEventQueue.isEmpty() || !normalPriorityActorEventQueue.isEmpty()) {
                if (busy.compareAndSet(FREE, BUSY)) {
                    dispatcher.getExecutor().execute(this::processMailbox);
                } else {
                    log.debug("{}[{}] is busy, has new event or not: {}", ModuleConstant.ACTOR_SYSTEM, selfId, hasNewEvent);
                }
            } else {
                log.debug("{}[{}] is empty, has new event or not: {}", ModuleConstant.ACTOR_SYSTEM, selfId, hasNewEvent);
            }
        } else {
            log.debug("{}[{}] is not ready, has new event or not: {}", ModuleConstant.ACTOR_SYSTEM, selfId, hasNewEvent);
        }
    }

    private void processMailbox() {
        boolean noMoreElements = false;
        for (int i = 0; i < systemSettings.getActorThroughput(); i++) {
            ActorEvent<?> event = highPriorityActorEventQueue.poll();
            if (event == null) {
                event = normalPriorityActorEventQueue.poll();
            }
            if (event != null) {
                try {
                    if (log.isTraceEnabled()) {
                        log.trace("{}[{}] Going to process event: {}", ModuleConstant.ACTOR_SYSTEM, selfId, event);
                    }
                    actor.process(event);
                } catch (Throwable throwable) {
                    log.error("{}[{}] Failed to process event: {}", ModuleConstant.ACTOR_SYSTEM, selfId, event, throwable);
                    ProcessFailureStrategy strategy = actor.onProcessFailure(throwable);
                    if (strategy.isStop()) {
                        system.stop(selfId);
                    }
                }
            } else {
                noMoreElements = true;
                break;
            }
        }
        if (noMoreElements) {
            busy.set(FREE);
            dispatcher.getExecutor().execute(() -> tryProcessQueue(false));
        } else {
            dispatcher.getExecutor().execute(this::processMailbox);
        }
    }

    @Override
    public ActorId getSelfActorId() {
        return selfId;
    }

    @Override
    public <T> void tell(ActorId targetId, ActorEvent<T> event) {
        system.tell(targetId, event);
    }

    @Override
    public <T> void broadcastToChildren(ActorEvent<T> event) {
        system.broadcastToChildren(selfId, event);
    }

    @Override
    public <T> void broadcastToChildren(ActorEvent<T> event, Predicate<ActorId> childFilter) {
        system.broadcastToChildren(selfId, childFilter, event);
    }

    @Override
    public List<ActorId> filterChildren(Predicate<ActorId> childFilter) {
        return system.filterChildren(selfId, childFilter);
    }

    @Override
    public void stop(ActorId targetId) {
        system.stop(targetId);
    }

    @Override
    public ActorMailbox getOrCreateChildActor(ActorId actorId, Supplier<String> dispatcher, Supplier<ActorCreator> creator) {
        ActorMailbox mailbox = system.getActor(actorId);
        if (mailbox == null) {
            return system.createChildActor(dispatcher.get(), creator.get(), selfId);
        } else {
            return mailbox;
        }
    }

    public void destroy() {
        if (stopReason == null) {
            stopReason = ActorStopReason.STOPPED;
        }
        destroyInProgress.set(true);
        dispatcher.getExecutor().execute(() -> {
            try {
                ready.set(NOT_READY);
                actor.destroy();
                highPriorityActorEventQueue.forEach(event -> event.onActorStopped(stopReason));
                normalPriorityActorEventQueue.forEach(event -> event.onActorStopped(stopReason));
            } catch (Throwable throwable) {
                log.error("{}[{}] Failed to destroy actor: {}", ModuleConstant.ACTOR_SYSTEM, selfId, throwable);
            }
        });
    }

    @Override
    public <T> void tell(ActorEvent<T> event) {
        enqueue(event, NORMAL_PRIORITY);
    }

    @Override
    public <T> void tellWithHighPriority(ActorEvent<T> event) {
        enqueue(event, HIGH_PRIORITY);
    }

}

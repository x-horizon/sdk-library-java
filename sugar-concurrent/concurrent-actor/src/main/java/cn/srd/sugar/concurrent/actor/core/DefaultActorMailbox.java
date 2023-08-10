package cn.srd.sugar.concurrent.actor.core;

import cn.srd.itcp.sugar.context.constant.core.ModuleView;
import cn.srd.sugar.concurrent.actor.event.ActorEvent;
import cn.srd.sugar.concurrent.actor.id.ActorId;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
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
            log.debug("{}[{}] Trying to init actor, attempt: {}", ModuleView.ACTOR_SYSTEM, selfId, attempt);
            if (!destroyInProgress.get()) {
                actor.init(this);
                if (!destroyInProgress.get()) {
                    ready.set(READY);
                    tryProcessQueueAsync(false);
                }
            }
        } catch (Throwable throwable) {
            log.error("{}[{}] Failed to init actor, attempt: {}", ModuleView.ACTOR_SYSTEM, selfId, attempt, throwable);
            int attemptIdx = attempt + 1;
            InitFailureStrategy strategy = actor.onInitFailure(attempt, throwable);
            if (strategy.isStop() || (systemSettings.getMaxActorInitAttempts() > 0 && attemptIdx > systemSettings.getMaxActorInitAttempts())) {
                log.error("{}[{}] Failed to init actor, attempt {}, going to stop attempts.", ModuleView.ACTOR_SYSTEM, selfId, attempt, throwable);
                stopReason = ActorStopReason.INIT_FAILED;
                destroy();
            } else if (strategy.getRetryDelay() > 0) {
                log.error("{}[{}] Failed to init actor, attempt {}, going to retry in attempts in {}ms", ModuleView.ACTOR_SYSTEM, selfId, attempt, strategy.getRetryDelay());
                log.error("{}[{}] Error", ModuleView.ACTOR_SYSTEM, selfId, throwable);
                system.getScheduler().schedule(() -> dispatcher.getExecutor().execute(() -> tryInit(attemptIdx)), strategy.getRetryDelay(), TimeUnit.MILLISECONDS);
            } else {
                log.error("{}[{}] Failed to init actor, attempt {}, going to retry immediately", ModuleView.ACTOR_SYSTEM, selfId, attempt);
                log.error("{}[{}] Error", ModuleView.ACTOR_SYSTEM, selfId, throwable);
                dispatcher.getExecutor().execute(() -> tryInit(attemptIdx));
            }
        }
    }

    private <T> void enqueueAndProcessAsync(ActorEvent<T> event, boolean isHighPriority) {
        enqueue(event, isHighPriority, this::tryProcessQueueAsync);
    }

    private <T> void enqueueAndProcessSync(ActorEvent<T> event, boolean isHighPriority) {
        enqueue(event, isHighPriority, this::tryProcessQueueSync);
    }

    private <T> void enqueue(ActorEvent<T> event, boolean isHighPriority, Consumer<Boolean> process) {
        if (!destroyInProgress.get()) {
            if (isHighPriority) {
                highPriorityActorEventQueue.add(event);
            } else {
                normalPriorityActorEventQueue.add(event);
            }
            process.accept(true);
        }
    }

    private void tryProcessQueueAsync(boolean hasNewEvent) {
        if (ready.get() == READY) {
            if (hasNewEvent || !highPriorityActorEventQueue.isEmpty() || !normalPriorityActorEventQueue.isEmpty()) {
                if (busy.compareAndSet(FREE, BUSY)) {
                    dispatcher.getExecutor().execute(this::processMailbox);
                } else {
                    log.trace("{}process queue async - [{}] is busy, has new event or not: {}", ModuleView.ACTOR_SYSTEM, selfId, hasNewEvent);
                }
            } else {
                log.trace("{}process queue async - [{}] is empty, has new event or not: {}", ModuleView.ACTOR_SYSTEM, selfId, hasNewEvent);
            }
        } else {
            log.trace("{}process queue async - [{}] is not ready, has new event or not: {}", ModuleView.ACTOR_SYSTEM, selfId, hasNewEvent);
        }
    }

    // TODO wjm 未测试
    @SneakyThrows
    private void tryProcessQueueSync(boolean hasNewEvent) {
        if (ready.get() == READY) {
            if (hasNewEvent || !highPriorityActorEventQueue.isEmpty() || !normalPriorityActorEventQueue.isEmpty()) {
                if (busy.compareAndSet(FREE, BUSY)) {
                    dispatcher.getExecutor().submit(this::processMailbox).get();
                } else {
                    log.trace("{}process queue sync - [{}] is busy, has new event or not: {}", ModuleView.ACTOR_SYSTEM, selfId, hasNewEvent);
                }
            } else {
                log.trace("{}process queue sync - [{}] is empty, has new event or not: {}", ModuleView.ACTOR_SYSTEM, selfId, hasNewEvent);
            }
        } else {
            log.trace("{}process queue sync - [{}] is not ready, has new event or not: {}", ModuleView.ACTOR_SYSTEM, selfId, hasNewEvent);
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
                        log.trace("{}[{}] Going to process event: {}", ModuleView.ACTOR_SYSTEM, selfId, event);
                    }
                    actor.process(event);
                } catch (Throwable throwable) {
                    log.error("{}[{}] Failed to process event: {}", ModuleView.ACTOR_SYSTEM, selfId, event, throwable);
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
            dispatcher.getExecutor().execute(() -> tryProcessQueueAsync(false));
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
                log.error("{}[{}] Failed to destroy actor: {}", ModuleView.ACTOR_SYSTEM, selfId, throwable);
            }
        });
    }

    @Override
    public <T> void tell(ActorEvent<T> event) {
        enqueueAndProcessAsync(event, NORMAL_PRIORITY);
    }

    @Override
    public <T> void tellWithHighPriority(ActorEvent<T> event) {
        enqueueAndProcessAsync(event, HIGH_PRIORITY);
    }

    @Override
    public <T> void tellSync(ActorEvent<T> event) {
        enqueueAndProcessSync(event, NORMAL_PRIORITY);
    }

    @Override
    public <T> void tellSyncWithHighPriority(ActorEvent<T> event) {
        enqueueAndProcessSync(event, HIGH_PRIORITY);
    }

}

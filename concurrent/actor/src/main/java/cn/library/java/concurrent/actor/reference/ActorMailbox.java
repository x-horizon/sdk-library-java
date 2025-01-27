package cn.library.java.concurrent.actor.reference;

import cn.library.java.concurrent.actor.id.ActorId;
import cn.library.java.concurrent.actor.message.ActorMessage;
import cn.library.java.concurrent.actor.model.property.ActorProperty;
import cn.library.java.concurrent.actor.self.Actor;
import cn.library.java.concurrent.actor.self.ActorCreator;
import cn.library.java.concurrent.actor.system.ActorDispatcher;
import cn.library.java.concurrent.actor.system.ActorSystem;
import cn.library.java.contract.constant.module.ModuleView;
import cn.library.java.tool.lang.object.Nil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;

/**
 * @author wjm
 * @since 2025-01-26 23:07
 */
@Slf4j
@RequiredArgsConstructor
public class ActorMailbox<T extends ActorMessage> implements ActorContext<T> {

    private final ActorSystem<T> actorSystem;

    private final ActorProperty actorProperty;

    @Getter private final ActorId selfId;

    private final Actor<T> selfActor;

    @Getter private final ActorReference<T> parentReference;

    private final ActorDispatcher dispatcher;

    private final ConcurrentLinkedQueue<T> highPriorityMessageQueue = new ConcurrentLinkedQueue<>();

    private final ConcurrentLinkedQueue<T> normalPriorityMessageQueue = new ConcurrentLinkedQueue<>();

    private final AtomicBoolean isReadyNow = new AtomicBoolean(false);

    private final AtomicBoolean isBusyNow = new AtomicBoolean(false);

    private final AtomicBoolean isDestroyNow = new AtomicBoolean();

    public void initActor() {
        dispatcher.getExecutor().execute(() -> tryInit(1));
    }

    private void tryInit(int attemptCount) {
        // TODO wjm actor
        // try {
        log.debug("{}trying to init actor [{}], attempt count: {}", ModuleView.CONCURRENT_ACTOR_SYSTEM, selfId, attemptCount);
        if (!isDestroyNow.get()) {
            selfActor.init(this);
            if (!isDestroyNow.get()) {
                isReadyNow.set(true);
                tryProcessQueue(false);
            }
        }
        // } catch (Throwable t) {
        //     InitFailureStrategy strategy;
        //     int attemptIdx = attemptCount + 1;
        //     if (isUnrecoverable(t)) {
        //         strategy = InitFailureStrategy.stop();
        //     } else {
        //         log.debug("[{}] Failed to init actor, attempt: {}", selfId, attemptCount, t);
        //         strategy = actor.onInitFailure(attemptCount, t);
        //     }
        //     if (strategy.isStop() || (settings.getMaxActorInitAttempts() > 0 && attemptIdx > settings.getMaxActorInitAttempts())) {
        //         log.info("[{}] Failed to init actor, attempt {}, going to stop attempts.", selfId, attemptCount, t);
        //         stopReason = TbActorStopReason.INIT_FAILED;
        //         destroy(t.getCause());
        //     } else if (strategy.getRetryDelay() > 0) {
        //         log.info("[{}] Failed to init actor, attempt {}, going to retry in attempts in {}ms", selfId, attemptCount, strategy.getRetryDelay());
        //         log.debug("[{}] Error", selfId, t);
        //         system.getScheduler().schedule(() -> dispatcher.getExecutor().execute(() -> tryInit(attemptIdx)), strategy.getRetryDelay(), TimeUnit.MILLISECONDS);
        //     } else {
        //         log.info("[{}] Failed to init actor, attempt {}, going to retry immediately", selfId, attemptCount);
        //         log.debug("[{}] Error", selfId, t);
        //         dispatcher.getExecutor().execute(() -> tryInit(attemptIdx));
        //     }
        // }
    }

    @Override
    public ActorReference<T> getOrCreateChildActorReference(ActorId actorId, String dispatcherId, ActorCreator<T> creator) {
        return null;
    }

    public void tellWithNormalPriority(ActorId targetActorId, T actorMessage) {
        tell(targetActorId, actorMessage, false);
    }

    public void tellWithHighPriority(ActorId targetActorId, T actorMessage) {
        tell(targetActorId, actorMessage, true);
    }

    @Override
    public void tell(ActorId targetActorId, T actorMessage, boolean highPriority) {
        actorSystem.tell(targetActorId, actorMessage, highPriority);
    }

    public void broadcastToChildrenWithNormalPriority(ActorId parentActorId, T actorMessage) {
        broadcastToChildren(parentActorId, actorMessage, false);
    }

    public void broadcastToChildrenWithNormalPriority(ActorId parentActorId, T actorMessage, Predicate<ActorId> childActorFilter) {
        broadcastToChildren(parentActorId, actorMessage, childActorFilter, false);
    }

    public void broadcastToChildrenWithHighPriority(ActorId parentActorId, T actorMessage) {
        broadcastToChildren(parentActorId, actorMessage, true);
    }

    public void broadcastToChildrenWithHighPriority(ActorId parentActorId, T actorMessage, Predicate<ActorId> childActorFilter) {
        broadcastToChildren(parentActorId, actorMessage, childActorFilter, true);
    }

    public void broadcastToChildren(ActorId parentActorId, T actorMessage, boolean highPriority) {
        actorSystem.broadcastToChildren(parentActorId, actorMessage, highPriority);
    }

    @Override
    public void broadcastToChildren(ActorId parentActorId, T actorMessage, Predicate<ActorId> childActorFilter, boolean highPriority) {
        actorSystem.broadcastToChildren(parentActorId, actorMessage, childActorFilter, highPriority);
    }

    @Override
    public void stop(ActorId targetActorId) {
        actorSystem.stop(targetActorId);
    }

    @Override
    public void tell(T actorMessage, boolean isHighPriority) {
        if (isDestroyNow.get()) {
            actorMessage.onActorStopped();
        } else {
            if (isHighPriority) {
                highPriorityMessageQueue.add(actorMessage);
            } else {
                normalPriorityMessageQueue.add(actorMessage);
            }
            tryProcessQueue(true);
        }
    }

    private void tryProcessQueue(boolean isNewMsg) {
        if (isReadyNow.get() && (isNewMsg || !highPriorityMessageQueue.isEmpty() || !normalPriorityMessageQueue.isEmpty()) && isBusyNow.compareAndSet(false, true)) {
            dispatcher.getExecutor().execute(this::processMailbox);
        }
    }

    private void processMailbox() {
        boolean isQueueEmpty = false;
        for (int index = 0; index < actorProperty.getThroughput(); index++) {
            T actorMessage = highPriorityMessageQueue.poll();
            if (Nil.isNull(actorMessage)) {
                actorMessage = normalPriorityMessageQueue.poll();
            }
            if (Nil.isNotNull(actorMessage)) {
                log.trace("{}actor [{}] going to process message [{}].", ModuleView.CONCURRENT_ACTOR_SYSTEM, selfId, actorMessage);
                selfActor.process(actorMessage);
            } else {
                isQueueEmpty = true;
                break;
            }
        }
        if (isQueueEmpty) {
            isBusyNow.set(false);
            dispatcher.getExecutor().execute(() -> tryProcessQueue(false));
        } else {
            dispatcher.getExecutor().execute(this::processMailbox);
        }
    }

    public void destroy() {
        destroy(null);
    }

    public void destroy(Throwable cause) {
        isDestroyNow.set(true);
        dispatcher.getExecutor().execute(() -> {
            try {
                isReadyNow.set(false);
                selfActor.destroy(cause);
                highPriorityMessageQueue.forEach(T::onActorStopped);
                normalPriorityMessageQueue.forEach(T::onActorStopped);
            } catch (Throwable throwable) {
                log.warn("{}failed to destroy actor [{}].", ModuleView.CONCURRENT_ACTOR_SYSTEM, selfId, throwable);
            }
        });
    }

}
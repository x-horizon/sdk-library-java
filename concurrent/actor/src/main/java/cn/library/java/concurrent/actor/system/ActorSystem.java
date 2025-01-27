package cn.library.java.concurrent.actor.system;

import cn.library.java.concurrent.actor.constant.ActorConstant;
import cn.library.java.concurrent.actor.exception.ActorNotRegisteredException;
import cn.library.java.concurrent.actor.id.ActorId;
import cn.library.java.concurrent.actor.message.ActorMessage;
import cn.library.java.concurrent.actor.model.property.ActorProperty;
import cn.library.java.concurrent.actor.reference.ActorMailbox;
import cn.library.java.concurrent.actor.reference.ActorReference;
import cn.library.java.concurrent.actor.self.Actor;
import cn.library.java.concurrent.actor.self.ActorCreator;
import cn.library.java.contract.constant.module.ModuleView;
import cn.library.java.contract.constant.number.NumberConstant;
import cn.library.java.tool.lang.concurrent.Threads;
import cn.library.java.tool.lang.functional.Assert;
import cn.library.java.tool.lang.object.Nil;
import cn.library.java.tool.spring.contract.support.Springs;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

/**
 * @author wjm
 * @since 2025-01-26 23:30
 */
@Slf4j
public class ActorSystem<T extends ActorMessage> {

    private static final int SHUTDOWN_EACH_DISPATCHER_MAX_WAIT_TIME = 3;

    private static final TimeUnit SHUTDOWN_EACH_DISPATCHER_MAX_WAIT_TIME_UNIT = TimeUnit.SECONDS;

    private final Map<String, ActorDispatcher> dispatcherIdMappingDispatcherMap = new ConcurrentHashMap<>();

    private final Map<ActorId, ActorMailbox<T>> actorIdMappingActorMailboxMap = new ConcurrentHashMap<>();

    private final Map<ActorId, ReentrantLock> actorIdMappingActorCreateLockMap = new ConcurrentHashMap<>();

    private final Map<ActorId, Set<ActorId>> actorIdMappingChildActorIdsMap = new ConcurrentHashMap<>();

    @Getter private final ScheduledExecutorService scheduler;

    @Getter private final ActorProperty actorProperty;

    public ActorSystem(ActorProperty actorProperty) {
        this.actorProperty = actorProperty;
        if (actorProperty.getNeedToEnableVirtualThread()) {
            this.scheduler = Executors.newScheduledThreadPool(NumberConstant.MAX_INT_VALUE, Thread.ofVirtual().name(ActorConstant.SYSTEM_SCHEDULER_NAME, 1).factory());
        } else {
            this.scheduler = Threads.newScheduledThreadPool(actorProperty.getSchedulerPoolSize(), ActorConstant.SYSTEM_SCHEDULER_NAME);
        }
    }

    public void createDispatcher(String dispatcherId, ExecutorService executor) {
        ActorDispatcher actorDispatcher = dispatcherIdMappingDispatcherMap.putIfAbsent(dispatcherId, new ActorDispatcher(dispatcherId, executor));
        Assert.of().setMessage("{}create actor dispatcher failed because the dispatcher [{}] is already registered.", ModuleView.CONCURRENT_ACTOR_SYSTEM, dispatcherId).throwsIfNotNull(actorDispatcher);
    }

    public void destroyDispatcher(String dispatcherId) {
        ActorDispatcher actorDispatcher = dispatcherIdMappingDispatcherMap.remove(dispatcherId);
        Assert.of().setMessage("{}destroy actor dispatcher failed because the dispatcher [{}] is not registered.", ModuleView.CONCURRENT_ACTOR_SYSTEM, dispatcherId).throwsIfNull(actorDispatcher);
        actorDispatcher.getExecutor().shutdownNow();
    }

    public ActorReference<T> getActorReference(ActorId actorId) {
        return actorIdMappingActorMailboxMap.get(actorId);
    }

    public ActorReference<T> createRootActorReference(String dispatcherId, ActorCreator<T> actorCreator) {
        return createActor(dispatcherId, actorCreator, null);
    }

    public ActorReference<T> createChildActorReference(String dispatcherId, ActorCreator<T> actorCreator, ActorId parentActorId) {
        return createActor(dispatcherId, actorCreator, parentActorId);
    }

    private ActorReference<T> createActor(String dispatcherId, ActorCreator<T> actorCreator, ActorId parentActorId) {
        ActorId actorId = actorCreator.createActorId();
        ActorMailbox<T> actorMailbox = actorIdMappingActorMailboxMap.get(actorId);
        if (Nil.isNotNull(actorMailbox)) {
            log.debug("{}create actor reference nothing to do because the actor mailbox [{}] is already registered.", ModuleView.CONCURRENT_ACTOR_SYSTEM, actorId);
            return actorMailbox;
        }

        Lock actorCreateLock = actorIdMappingActorCreateLockMap.computeIfAbsent(actorId, id -> new ReentrantLock());
        actorCreateLock.lock();
        try {
            actorMailbox = actorIdMappingActorMailboxMap.get(actorId);
            if (Nil.isNotNull(actorMailbox)) {
                log.debug("{}create actor reference nothing to do because the actor mailbox [{}] is already registered.", ModuleView.CONCURRENT_ACTOR_SYSTEM, actorId);
                return actorMailbox;
            }

            log.debug("{}creating actor reference [{}].", ModuleView.CONCURRENT_ACTOR_SYSTEM, actorId);

            ActorReference<T> parentActorReference = null;
            if (Nil.isNotNull(parentActorId)) {
                parentActorReference = getActorReference(parentActorId);
                Assert.of().setMessage("{}create actor reference failed because the actor [{}] parent actor [{}] is not registered.", ModuleView.CONCURRENT_ACTOR_SYSTEM, actorId, parentActorId).throwsIfNull(parentActorReference);
                actorIdMappingChildActorIdsMap.computeIfAbsent(parentActorId, _ -> ConcurrentHashMap.newKeySet()).add(actorId);
            }

            ActorDispatcher actorDispatcher = dispatcherIdMappingDispatcherMap.get(dispatcherId);
            Assert.of().setMessage("{}create actor reference failed because the actor dispatcher [{}] is not registered.", ModuleView.CONCURRENT_ACTOR_SYSTEM, dispatcherId).throwsIfNull(actorDispatcher);
            Actor<T> actor = actorCreator.createActor();
            ActorProperty actorProperty = Springs.getBean(ActorProperty.class);
            actorMailbox = new ActorMailbox<>(this, actorProperty, actorId, actor, parentActorReference, actorDispatcher);
            actorIdMappingActorMailboxMap.put(actorId, actorMailbox);
            actorMailbox.initActor();
        } finally {
            actorCreateLock.unlock();
            actorIdMappingActorCreateLockMap.remove(actorId);
        }

        return actorMailbox;
    }

    public void tellWithNormalPriority(ActorId targetActorId, T actorMessage) {
        tell(targetActorId, actorMessage, false);
    }

    public void tellWithHighPriority(ActorId targetActorId, T actorMessage) {
        tell(targetActorId, actorMessage, true);
    }

    public void tell(ActorId targetActorId, T actorMessage, boolean isHighPriority) {
        ActorMailbox<T> actorMailbox = actorIdMappingActorMailboxMap.get(targetActorId);
        Assert.of()
                .setThrowable(ActorNotRegisteredException.class)
                .setMessage("{}tell actor message failed because the actor mailbox [{}] is not registered.", ModuleView.CONCURRENT_ACTOR_SYSTEM, targetActorId)
                .throwsIfNull(actorMailbox);
        if (isHighPriority) {
            actorMailbox.tellWithHighPriority(actorMessage);
        } else {
            actorMailbox.tellWithNormalPriority(actorMessage);
        }
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
        broadcastToChildren(parentActorId, actorMessage, ignore -> true, highPriority);
    }

    public void broadcastToChildren(ActorId parentActorId, T actorMessage, Predicate<ActorId> childActorFilter, boolean highPriority) {
        Optional.ofNullable(actorIdMappingChildActorIdsMap.get(parentActorId)).ifPresent(childActorIds -> childActorIds
                .stream()
                .filter(childActorFilter)
                .forEach(childActorId -> {
                    try {
                        tell(childActorId, actorMessage, highPriority);
                    } catch (ActorNotRegisteredException ignore) {
                        log.warn("{}tell actor message failed because the actor mailbox [{}] is not registered.", ModuleView.CONCURRENT_ACTOR_SYSTEM, childActorId);
                    }
                })
        );
    }

    public void stop(ActorReference<T> actorReference) {
        stop(actorReference.getSelfId());
    }

    public void stop(ActorId actorId) {
        Optional.ofNullable(actorIdMappingChildActorIdsMap.remove(actorId)).ifPresent(childActorIds -> childActorIds.forEach(this::stop));
        actorIdMappingChildActorIdsMap.values().forEach(childActorIds -> childActorIds.remove(actorId));
        Optional.ofNullable(actorIdMappingActorMailboxMap.remove(actorId)).ifPresent(ActorMailbox::destroy);
    }

    public void stop() {
        log.debug("{}stopping actor system...", ModuleView.CONCURRENT_ACTOR_SYSTEM);
        dispatcherIdMappingDispatcherMap.values().forEach(dispatcher -> {
            dispatcher.getExecutor().shutdown();
            try {
                boolean isNotFinished = !dispatcher.getExecutor().awaitTermination(SHUTDOWN_EACH_DISPATCHER_MAX_WAIT_TIME, SHUTDOWN_EACH_DISPATCHER_MAX_WAIT_TIME_UNIT);
                if (isNotFinished) {
                    log.warn("{}stopping actor system and this dispatcher [{}] could not finish task after timeout [{}{}].", ModuleView.CONCURRENT_ACTOR_SYSTEM, dispatcher.getDispatcherId(), SHUTDOWN_EACH_DISPATCHER_MAX_WAIT_TIME, SHUTDOWN_EACH_DISPATCHER_MAX_WAIT_TIME_UNIT.name());
                }
            } catch (InterruptedException cause) {
                log.warn("{}stopping actor system and this dispatcher [{}] failed to stop.", ModuleView.CONCURRENT_ACTOR_SYSTEM, dispatcher.getDispatcherId(), cause);
            }
        });
        Optional.ofNullable(scheduler).ifPresent(ExecutorService::shutdownNow);
        actorIdMappingActorMailboxMap.clear();
        log.debug("{}actor system stopped.", ModuleView.CONCURRENT_ACTOR_SYSTEM);
    }

}
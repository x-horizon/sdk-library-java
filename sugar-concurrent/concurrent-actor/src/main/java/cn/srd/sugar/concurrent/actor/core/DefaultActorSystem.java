package cn.srd.sugar.concurrent.actor.core;

import cn.srd.itcp.sugar.context.constant.core.ModuleView;
import cn.srd.itcp.sugar.tool.core.thread.ThreadsUtil;
import cn.srd.sugar.concurrent.actor.event.ActorEvent;
import cn.srd.sugar.concurrent.actor.exception.ActorNotRegisteredException;
import cn.srd.sugar.concurrent.actor.id.ActorId;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

/**
 * default actor system
 *
 * @author wjm
 * @since 2023-03-20 11:04:19
 */
@Slf4j
public class DefaultActorSystem implements ActorSystem {

    private final ConcurrentMap<String, Dispatcher> dispatchers = new ConcurrentHashMap<>();
    private final ConcurrentMap<ActorId, DefaultActorMailbox> actors = new ConcurrentHashMap<>();
    private final ConcurrentMap<ActorId, ReentrantLock> actorCreationLocks = new ConcurrentHashMap<>();
    private final ConcurrentMap<ActorId, Set<ActorId>> parentChildMap = new ConcurrentHashMap<>();

    @Getter
    private final ActorSystemSettings settings;
    @Getter
    private final ScheduledExecutorService scheduler;

    public DefaultActorSystem(ActorSystemSettings settings) {
        this.settings = settings;
        this.scheduler = ThreadsUtil.newScheduledThreadPool(settings.getSchedulerPoolSize(), "actor-system-scheduler");
    }

    @Override
    public void createDispatcher(String dispatcherId, ExecutorService executor) {
        Dispatcher current = dispatchers.putIfAbsent(dispatcherId, new Dispatcher(dispatcherId, executor));
        if (current != null) {
            throw new RuntimeException("Dispatcher with id [" + dispatcherId + "] is already registered!");
        }
    }

    @Override
    public void destroyDispatcher(String dispatcherId) {
        Dispatcher dispatcher = dispatchers.remove(dispatcherId);
        if (dispatcher != null) {
            dispatcher.getExecutor().shutdownNow();
        } else {
            throw new RuntimeException("Dispatcher with id [" + dispatcherId + "] is not registered!");
        }
    }

    @Override
    public ActorMailbox getActor(ActorId actorId) {
        return actors.get(actorId);
    }

    @Override
    public ActorMailbox createRootActor(String dispatcherId, ActorCreator creator) {
        return createActor(dispatcherId, creator, null);
    }

    @Override
    public ActorMailbox createChildActor(String dispatcherId, ActorCreator creator, ActorId parentId) {
        return createActor(dispatcherId, creator, parentId);
    }

    private ActorMailbox createActor(String dispatcherId, ActorCreator creator, ActorId parentId) {
        Dispatcher dispatcher = dispatchers.get(dispatcherId);
        if (dispatcher == null) {
            log.warn("{}Dispatcher with id [{}] is not registered!", ModuleView.ACTOR_SYSTEM, dispatcherId);
            throw new RuntimeException("Dispatcher with id [" + dispatcherId + "] is not registered!");
        }

        ActorId actorId = creator.createActorId();
        DefaultActorMailbox actorMailbox = actors.get(actorId);
        if (actorMailbox != null) {
            log.warn("{}Actor with id [{}] is already registered!", ModuleView.ACTOR_SYSTEM, actorId);
        } else {
            Lock actorCreationLock = actorCreationLocks.computeIfAbsent(actorId, id -> new ReentrantLock());
            actorCreationLock.lock();
            try {
                actorMailbox = actors.get(actorId);
                if (actorMailbox == null) {
                    log.debug("{}Creating actor with id [{}]!", ModuleView.ACTOR_SYSTEM, actorId);
                    Actor actor = creator.createActor();
                    ActorMailbox parentMailbox = null;
                    if (parentId != null) {
                        parentMailbox = getActor(parentId);
                        if (parentMailbox == null) {
                            throw new ActorNotRegisteredException(parentId, "Parent Actor with id [" + parentId + "] is not registered!");
                        }
                    }
                    DefaultActorMailbox mailbox = new DefaultActorMailbox(this, settings, actorId, parentMailbox, actor, dispatcher);
                    actors.put(actorId, mailbox);
                    mailbox.initActor();
                    actorMailbox = mailbox;
                    if (parentId != null) {
                        parentChildMap.computeIfAbsent(parentId, id -> ConcurrentHashMap.newKeySet()).add(actorId);
                    }
                } else {
                    log.debug("{}Actor with id [{}] is already registered!", ModuleView.ACTOR_SYSTEM, actorId);
                }
            } finally {
                actorCreationLock.unlock();
                actorCreationLocks.remove(actorId);
            }
        }
        return actorMailbox;
    }

    @Override
    public <T> void tellWithHighPriority(ActorId targetId, ActorEvent<T> event) {
        tell(targetId, event, true);
    }

    @Override
    public <T> void tell(ActorId targetId, ActorEvent<T> event) {
        tell(targetId, event, false);
    }

    private <T> void tell(ActorId targetId, ActorEvent<T> event, boolean highPriority) {
        DefaultActorMailbox mailbox = actors.get(targetId);
        if (mailbox == null) {
            throw new ActorNotRegisteredException(targetId, "Actor with id [" + targetId + "] is not registered!");
        }
        if (highPriority) {
            mailbox.tellWithHighPriority(event);
        } else {
            mailbox.tell(event);
        }
    }

    @Override
    public <T> void broadcastToChildren(ActorId parentId, ActorEvent<T> event) {
        broadcastToChildren(parentId, id -> true, event);
    }

    @Override
    public <T> void broadcastToChildren(ActorId parentId, Predicate<ActorId> childFilter, ActorEvent<T> event) {
        Set<ActorId> children = parentChildMap.get(parentId);
        if (children != null) {
            children.stream().filter(childFilter).forEach(id -> tell(id, event));
        }
    }

    @Override
    public List<ActorId> filterChildren(ActorId parentId, Predicate<ActorId> childFilter) {
        Set<ActorId> children = parentChildMap.get(parentId);
        if (children != null) {
            return children.stream().filter(childFilter).toList();
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public void stop(ActorMailbox mailbox) {
        stop(mailbox.getSelfActorId());
    }

    @Override
    public void stop(ActorId actorId) {
        Set<ActorId> childrenIds = parentChildMap.remove(actorId);
        if (childrenIds != null) {
            for (ActorId childId : childrenIds) {
                stop(childId);
            }
        }
        DefaultActorMailbox mailbox = actors.remove(actorId);
        if (mailbox != null) {
            mailbox.destroy();
        }
    }

    @Override
    public void stop() {
        dispatchers.values().forEach(dispatcher -> {
            dispatcher.getExecutor().shutdown();
            try {
                dispatcher.getExecutor().awaitTermination(3, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                log.warn("{}[{}] Failed to stop dispatcher", ModuleView.ACTOR_SYSTEM, dispatcher.getDispatcherId(), e);
            }
        });
        if (scheduler != null) {
            scheduler.shutdownNow();
        }
        actors.clear();
    }

}

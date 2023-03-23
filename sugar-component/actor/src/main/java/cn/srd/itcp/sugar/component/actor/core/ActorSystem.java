package cn.srd.itcp.sugar.component.actor.core;

import cn.srd.itcp.sugar.component.actor.event.ActorEvent;
import cn.srd.itcp.sugar.component.actor.id.ActorId;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Predicate;

/**
 * actor system
 *
 * @author wjm
 * @since 2023-03-20 11:04:19
 */
public interface ActorSystem {

    ScheduledExecutorService getScheduler();

    void createDispatcher(String dispatcherId, ExecutorService executor);

    void destroyDispatcher(String dispatcherId);

    ActorMailbox getActor(ActorId actorId);

    ActorMailbox createRootActor(String dispatcherId, ActorCreator creator);

    ActorMailbox createChildActor(String dispatcherId, ActorCreator creator, ActorId parentId);

    <T> void tell(ActorId targetId, ActorEvent<T> event);

    <T> void tellWithHighPriority(ActorId targetId, ActorEvent<T> event);

    void stop(ActorMailbox mailbox);

    void stop(ActorId actorId);

    void stop();

    <T> void broadcastToChildren(ActorId parentId, ActorEvent<T> event);

    <T> void broadcastToChildren(ActorId parentId, Predicate<ActorId> childFilter, ActorEvent<T> event);

    List<ActorId> filterChildren(ActorId parentId, Predicate<ActorId> childFilter);

}

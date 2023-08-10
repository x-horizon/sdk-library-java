package cn.srd.sugar.concurrent.actor.core;

import cn.srd.sugar.concurrent.actor.event.ActorEvent;
import cn.srd.sugar.concurrent.actor.id.ActorId;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author wjm
 * @since 2023-03-23 20:09:17
 */
public interface ActorMailbox {

    ActorId getSelfActorId();

    ActorMailbox getParentMailbox();

    ActorMailbox getOrCreateChildActor(ActorId actorId, Supplier<String> dispatcher, Supplier<ActorCreator> creator);

    List<ActorId> filterChildren(Predicate<ActorId> childFilter);

    <T> void tell(ActorEvent<T> event);

    <T> void tell(ActorId targetId, ActorEvent<T> event);

    <T> void tellWithHighPriority(ActorEvent<T> event);

    <T> void tellSync(ActorEvent<T> event);

    <T> void tellSyncWithHighPriority(ActorEvent<T> event);

    <T> void broadcastToChildren(ActorEvent<T> event);

    <T> void broadcastToChildren(ActorEvent<T> event, Predicate<ActorId> childFilter);

    void stop(ActorId targetId);

}

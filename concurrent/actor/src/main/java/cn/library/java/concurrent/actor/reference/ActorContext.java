package cn.library.java.concurrent.actor.reference;

import cn.library.java.concurrent.actor.id.ActorId;
import cn.library.java.concurrent.actor.message.ActorMessage;
import cn.library.java.concurrent.actor.self.ActorCreator;

import java.util.function.Predicate;

/**
 * @author wjm
 * @since 2025-01-26 22:49
 */
public interface ActorContext<T extends ActorMessage> extends ActorReference<T> {

    ActorReference<T> getParentReference();

    ActorReference<T> getOrCreateChildActorReference(ActorId actorId, String dispatcherId, ActorCreator<T> creator);

    void tell(ActorId targetActorId, T actorMessage, boolean highPriority);

    void broadcastToChildren(ActorId parentActorId, T actorMessage, Predicate<ActorId> childActorFilter, boolean highPriority);

    void stop(ActorId targetActorId);

}
package cn.library.java.concurrent.actor.reference;

import cn.library.java.concurrent.actor.id.ActorId;
import cn.library.java.concurrent.actor.message.ActorMessage;
import cn.library.java.concurrent.actor.self.ActorCreator;

import java.util.function.Predicate;

/**
 * @author wjm
 * @since 2025-01-26 22:49
 */
public interface ActorContext extends ActorReference {

    ActorReference getParentReference();

    ActorReference getOrCreateChildActorReference(ActorId actorId, String dispatcherId, ActorCreator creator);

    void tell(ActorId targetActorId, ActorMessage message, boolean highPriority);

    void broadcastToChildren(ActorId parentActorId, ActorMessage message, Predicate<ActorId> childActorFilter, boolean highPriority);

    void stop(ActorId targetActorId);

}
package cn.srd.library.java.concurrent.actor.reference;

import cn.srd.library.java.concurrent.actor.id.ActorId;
import cn.srd.library.java.concurrent.actor.message.ActorMessage;

/**
 * @author wjm
 * @since 2025-01-26 21:07
 */
public interface ActorReference<T extends ActorMessage> {

    ActorId getSelfId();

    void tell(T actorMessage, boolean isHighPriority);

    default void tellWithNormalPriority(T actorMessage) {
        tell(actorMessage, false);
    }

    default void tellWithHighPriority(T actorMessage) {
        tell(actorMessage, true);
    }

}
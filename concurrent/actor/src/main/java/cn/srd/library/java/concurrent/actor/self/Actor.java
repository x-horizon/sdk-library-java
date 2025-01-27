package cn.srd.library.java.concurrent.actor.self;

import cn.srd.library.java.concurrent.actor.message.ActorMessage;
import cn.srd.library.java.concurrent.actor.reference.ActorReference;

/**
 * @author wjm
 * @since 2025-01-26 21:06
 */
public interface Actor<T extends ActorMessage> {

    default void init(ActorReference<T> actorReference) {
    }

    default void onInitFailure(int attemptCount, Throwable cause) {
    }

    default void destroy(Throwable cause) {
    }

    default void onProcessFailure(T message, Throwable cause) {
    }

    void process(T message);

    ActorReference<T> getReference();

}
package org.horizon.library.java.concurrent.actor.message;

/**
 * @author wjm
 * @since 2025-01-26 21:08
 */
public interface ActorMessage {

    default void onActorStopped() {
    }

}
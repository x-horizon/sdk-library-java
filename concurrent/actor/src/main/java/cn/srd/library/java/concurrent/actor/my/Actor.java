package cn.srd.library.java.concurrent.actor.my;

/**
 * @author wjm
 * @since 2024-09-25 09:05
 */
public interface Actor {

    ActorMessageType getMessageType();

    /**
     * Executed when the target Actor is stopped or destroyed.
     * For example, rule node failed to initialize or removed from rule chain.
     * Implementation should clean up the resources.
     */
    default void onActorTerminated(ActorTerminateReasonType terminateReasonType) {
    }

}
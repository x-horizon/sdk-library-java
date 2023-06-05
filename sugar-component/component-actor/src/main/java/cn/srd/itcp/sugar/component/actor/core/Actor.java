package cn.srd.itcp.sugar.component.actor.core;

import cn.srd.itcp.sugar.component.actor.event.ActorEvent;
import cn.srd.itcp.sugar.component.actor.exception.ActorException;

/**
 * actor
 *
 * @author wjm
 * @since 2023-03-20 11:04:19
 */
public interface Actor {

    <T> boolean process(ActorEvent<T> event);

    ActorMailbox getMailbox();

    default void init(ActorMailbox mailbox) throws ActorException {
    }

    default void destroy() throws ActorException {
    }

    default InitFailureStrategy onInitFailure(int attempt, Throwable throwable) {
        return InitFailureStrategy.retryWithDelay(5000L * attempt);
    }

    default ProcessFailureStrategy onProcessFailure(Throwable throwable) {
        if (throwable instanceof Error) {
            return ProcessFailureStrategy.stop();
        } else {
            return ProcessFailureStrategy.resume();
        }
    }

}

package cn.srd.itcp.sugar.component.actor.core;

import cn.srd.itcp.sugar.component.actor.exception.ActorException;
import lombok.Getter;

/**
 * @author wjm
 * @since 2023-03-23 20:09:17
 */
@Getter
public abstract class DefaultActor<T> implements Actor<T> {

    private ActorMailbox mailbox;

    @Override
    public void init(ActorMailbox mailbox) throws ActorException {
        this.mailbox = mailbox;
    }

}

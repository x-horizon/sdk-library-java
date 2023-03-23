package cn.srd.itcp.sugar.component.actor.core;

import cn.srd.itcp.sugar.component.actor.exception.ActorException;
import lombok.Getter;

@Getter
public abstract class DefaultActor implements Actor {

    private ActorMailbox mailbox;

    @Override
    public void init(ActorMailbox mailbox) throws ActorException {
        this.mailbox = mailbox;
    }

}

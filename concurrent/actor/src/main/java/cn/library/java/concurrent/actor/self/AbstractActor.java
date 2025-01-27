package cn.library.java.concurrent.actor.self;

import cn.library.java.concurrent.actor.message.ActorMessage;
import cn.library.java.concurrent.actor.reference.ActorReference;

/**
 * @author wjm
 * @since 2025-01-26 22:20
 */
public abstract class AbstractActor<T extends ActorMessage> implements Actor<T> {

    protected ActorReference<T> reference;

    @Override
    public void init(ActorReference<T> reference) {
        this.reference = reference;
    }

    @Override
    public ActorReference<T> getReference() {
        return reference;
    }

}
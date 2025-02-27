package org.horizon.sdk.library.java.concurrent.actor.self;

import org.horizon.sdk.library.java.concurrent.actor.reference.ActorContext;

/**
 * @author wjm
 * @since 2025-01-28 20:44
 */
public abstract class ContextAwareActor implements Actor {

    protected ActorContext context;

    @Override
    public void init(ActorContext context) {
        this.context = context;
    }

    @Override
    public ActorContext getReference() {
        return context;
    }

}
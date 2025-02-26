package org.horizon.library.java.concurrent.actor.foo;

import org.horizon.library.java.concurrent.actor.reference.ActorReference;
import org.horizon.library.java.concurrent.actor.self.Actor;

/**
 * @author wjm
 * @since 2025-01-26 21:18
 */
public class RuleChainToRuleNodeActor implements Actor<RuleChainToRuleNodeActorMessage> {

    @Override
    public void process(RuleChainToRuleNodeActorMessage message) {

    }

    @Override
    public ActorReference<RuleChainToRuleNodeActorMessage> getReference() {
        return null;
    }

}
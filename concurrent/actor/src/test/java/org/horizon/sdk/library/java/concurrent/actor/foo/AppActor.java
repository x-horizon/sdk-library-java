package org.horizon.sdk.library.java.concurrent.actor.foo;

import org.horizon.sdk.library.java.concurrent.actor.id.ActorId;
import org.horizon.sdk.library.java.concurrent.actor.id.ActorStringId;
import org.horizon.sdk.library.java.concurrent.actor.self.Actor;
import org.horizon.sdk.library.java.concurrent.actor.self.ContextActorCreator;
import org.horizon.sdk.library.java.concurrent.actor.self.ContextAwareActor;

/**
 * @author wjm
 * @since 2025-01-28 00:40
 */
public class AppActor extends ContextAwareActor<AppActorMessage> {

    private static final String APP_UUID = "42b9024b-4fb7-413e-bfbf-ad22b3315d56";

    @Override
    public void process(AppActorMessage message) {
        this.context.tell(message, false);
    }

    public static class ActorCreator extends ContextActorCreator<AppActorMessage> {

        @Override
        public ActorId createActorId() {
            return new ActorStringId(APP_UUID);
        }

        @Override
        public Actor<AppActorMessage> createActor() {
            return new AppActor();
        }

    }

}
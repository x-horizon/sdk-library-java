package cn.srd.library.java.concurrent.actor.foo;

import cn.srd.library.java.concurrent.actor.core.ActorCreator;
import cn.srd.library.java.concurrent.actor.core.ActorMailbox;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class TelemetryActorTypeRootStrategy implements TelemetryActorTypeStrategy {

    private static final String DISPATCHER_NAME = "telemetry-root-dispatcher";

    private ActorMailbox mailbox;

    @Override
    public String getDispatcherName() {
        return DISPATCHER_NAME;
    }

    @Override
    public int getDispatcherCount() {
        return 4;
    }

    @Override
    public ActorCreator newActorCreator() {
        return new TelemetryRootActor.ActorCreator();
    }

}
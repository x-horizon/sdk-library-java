package cn.srd.library.java.concurrent.actor.foo;

import cn.srd.library.java.concurrent.actor.event.ActorEvent;
import cn.srd.library.java.concurrent.actor.id.ActorId;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TelemetryRootActor extends TelemetryContextAwareActor<Void, Void> {

    @Getter private static TelemetryRootActor instance = null;

    @PostConstruct
    public void init() {
        instance = this;
    }

    // TODO the same as ActorCreator#createActorId(), need optimize
    @Override
    public ActorId getActorId() {
        return TelemetryRootActorId.INSTANCE;
    }

    @Override
    protected Class<Void> getActualActorEventDataType() {
        return Void.class;
    }

    // TODO wjm consider use parent child broadcast
    @Override
    protected boolean doProcess(ActorEvent<Void> event) {
        return event.broadcastBindingActors();
    }

    public static class ActorCreator extends TelemetryActorCreator {

        @Override
        public ActorId createActorId() {
            return TelemetryRootActorId.INSTANCE;
        }

        @Override
        public TelemetryRootActor createActor() {
            return TelemetryRootActor.getInstance();
        }

    }

}
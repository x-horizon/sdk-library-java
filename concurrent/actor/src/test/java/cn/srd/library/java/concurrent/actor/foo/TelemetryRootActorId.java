package cn.srd.library.java.concurrent.actor.foo;

import cn.srd.library.java.concurrent.actor.id.DefaultActorId;

import java.io.Serial;
import java.util.UUID;

public class TelemetryRootActorId extends DefaultActorId {

    @Serial private static final long serialVersionUID = -3021225730466884680L;

    public static final TelemetryRootActorId INSTANCE = new TelemetryRootActorId();

    private static final UUID UNIQUE_UUID = UUID.fromString("48ee9bd1-84b7-421a-b217-f687ea61e1e0");

    @Override
    public UUID getId() {
        return UNIQUE_UUID;
    }

    @Override
    public TelemetryActorType getActorType() {
        return TelemetryActorType.ROOT;
    }

}
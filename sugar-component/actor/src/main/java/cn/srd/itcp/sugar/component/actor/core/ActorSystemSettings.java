package cn.srd.itcp.sugar.component.actor.core;

import lombok.Data;

@Data
public class ActorSystemSettings {

    private final int actorThroughput;
    private final int schedulerPoolSize;
    private final int maxActorInitAttempts;

}

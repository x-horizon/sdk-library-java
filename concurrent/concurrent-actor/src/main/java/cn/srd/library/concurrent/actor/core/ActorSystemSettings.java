package cn.srd.library.concurrent.actor.core;

import lombok.Data;

/**
 * @author wjm
 * @since 2023-03-23 20:09:17
 */
@Data
public class ActorSystemSettings {

    private final int actorThroughput;
    private final int schedulerPoolSize;
    private final int maxActorInitAttempts;

}

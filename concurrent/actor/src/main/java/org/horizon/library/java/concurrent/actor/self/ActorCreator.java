package org.horizon.library.java.concurrent.actor.self;

import org.horizon.library.java.concurrent.actor.id.ActorId;

/**
 * @author wjm
 * @since 2025-01-26 22:50
 */
public interface ActorCreator {

    ActorId createActorId();

    Actor createActor();

}
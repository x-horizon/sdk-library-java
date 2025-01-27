package cn.library.java.concurrent.actor.self;

import cn.library.java.concurrent.actor.id.ActorId;
import cn.library.java.concurrent.actor.message.ActorMessage;

/**
 * @author wjm
 * @since 2025-01-26 22:50
 */
public interface ActorCreator<T extends ActorMessage> {

    ActorId createActorId();

    Actor<T> createActor();

}
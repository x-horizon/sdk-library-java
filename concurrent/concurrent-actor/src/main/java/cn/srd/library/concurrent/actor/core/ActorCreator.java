package cn.srd.library.concurrent.actor.core;

import cn.srd.library.concurrent.actor.id.ActorId;

/**
 * actor creator
 *
 * @author wjm
 * @since 2023-03-20 11:04:19
 */
public interface ActorCreator {

    ActorId createActorId();

    Actor createActor();

}

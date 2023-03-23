package cn.srd.itcp.sugar.component.actor.core;

import cn.srd.itcp.sugar.component.actor.id.ActorId;

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

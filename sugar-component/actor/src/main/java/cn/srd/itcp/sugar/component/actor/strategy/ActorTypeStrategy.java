package cn.srd.itcp.sugar.component.actor.strategy;

import cn.srd.itcp.sugar.component.actor.core.ActorCreator;
import cn.srd.itcp.sugar.component.actor.core.ActorMailbox;

/**
 * actor type strategy
 *
 * @author wjm
 * @since 2023-03-20 11:04:19
 */
public interface ActorTypeStrategy {

    String getDispatcherName();

    int getDispatcherCount();

    ActorMailbox getActor();

    void setActor(ActorMailbox actorMailbox);

    ActorCreator newActorCreator();

}

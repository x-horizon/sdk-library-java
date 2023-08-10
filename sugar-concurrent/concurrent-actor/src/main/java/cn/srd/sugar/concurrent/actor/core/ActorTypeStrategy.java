package cn.srd.sugar.concurrent.actor.core;

/**
 * actor type strategy
 *
 * @author wjm
 * @since 2023-03-20 11:04:19
 */
public interface ActorTypeStrategy {

    String getDispatcherName();

    int getDispatcherCount();

    ActorMailbox getMailbox();

    void setMailbox(ActorMailbox actorMailbox);

    ActorCreator newActorCreator();

}

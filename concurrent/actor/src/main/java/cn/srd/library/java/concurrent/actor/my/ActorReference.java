package cn.srd.library.java.concurrent.actor.my;

import cn.srd.library.java.concurrent.actor.original.TbActorId;
import cn.srd.library.java.concurrent.actor.original.TbActorMsg;

/**
 * @author wjm
 * @since 2024-09-25 09:12
 */
public interface ActorReference {

    TbActorId getActorId();

    void tell(TbActorMsg actorMsg);

    void tellWithHighPriority(TbActorMsg actorMsg);

}
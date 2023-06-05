package cn.srd.itcp.sugar.component.actor.id;

import cn.srd.itcp.sugar.component.actor.core.ActorType;

import java.io.Serializable;
import java.util.UUID;

/**
 * actor id
 *
 * @author wjm
 * @since 2023-03-20 11:04:19
 */
public interface ActorId extends Serializable {

    /**
     * 获取 id
     *
     * @return id
     */
    UUID getId();

    /**
     * 获取 {@link ActorType}
     *
     * @return {@link ActorType}
     */
    ActorType getActorType();

}

package cn.srd.itcp.sugar.component.actor.event;

import cn.srd.itcp.sugar.component.actor.core.ActorStopReason;
import cn.srd.itcp.sugar.component.actor.core.ActorType;

import java.io.Serializable;
import java.util.List;

/**
 * actor event
 *
 * @author wjm
 * @since 2023-03-20 16:45:11
 */
public interface ActorEvent<T> extends Serializable {

    /**
     * 获取事件数据
     *
     * @return 事件数据
     */
    T getData();

    /**
     * 获取事件绑定的 actor
     *
     * @return 绑定的 actor
     */
    List<? extends ActorType> getBindActorTypes();

    /**
     * 事件分发
     *
     * @return TODO wjm 待定义
     */
    default boolean broadcastBindingActors() {
        getBindActorTypes().forEach(actorType -> actorType.getStrategy().getMailbox().tell(this));
        return true;
    }

    /**
     * Executed when the target TbActor is stopped or destroyed.
     * For example, rule node failed to initialize or removed from rule chain.
     * Implementation should cleanup the resources.
     */
    default void onActorStopped(ActorStopReason reason) {
    }

}

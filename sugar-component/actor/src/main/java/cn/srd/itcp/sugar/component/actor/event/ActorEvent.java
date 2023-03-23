package cn.srd.itcp.sugar.component.actor.event;

import cn.srd.itcp.sugar.component.actor.core.ActorStopReason;

import java.io.Serializable;

/**
 * actor event processor
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
     * 获取事件类型
     *
     * @return 事件类型
     */
    ActorEventType getEventType();

    /**
     * Executed when the target TbActor is stopped or destroyed.
     * For example, rule node failed to initialize or removed from rule chain.
     * Implementation should cleanup the resources.
     */

    default void onActorStopped(ActorStopReason reason) {
    }

}

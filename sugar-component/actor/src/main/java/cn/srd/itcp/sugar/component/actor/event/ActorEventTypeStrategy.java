package cn.srd.itcp.sugar.component.actor.event;

/**
 * actor event strategy
 *
 * @author wjm
 * @since 2023-03-20 16:45:11
 */
public interface ActorEventTypeStrategy<T> {

    void processEvent(ActorEvent<T> event);

}

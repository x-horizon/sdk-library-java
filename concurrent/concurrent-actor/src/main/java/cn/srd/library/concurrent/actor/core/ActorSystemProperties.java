package cn.srd.library.concurrent.actor.core;

import cn.srd.library.concurrent.actor.id.ActorId;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * actor system properties
 *
 * @author wjm
 * @since 2023-03-23 10:38:20
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "library.actor.system")
public class ActorSystemProperties {

    /**
     * TODO wjm 未确定用途
     */
    @Value("${schedulerPoolSize:1}")
    private int schedulerPoolSize;

    /**
     * actor 处理能力吞吐量指标；
     * <pre>
     * 用于在每一次调用 {@link DefaultActorMailbox#processMailbox()} 时，
     * 从 {@link DefaultActorMailbox#getHighPriorityActorEventQueue()} / {@link DefaultActorMailbox#getNormalPriorityActorEventQueue()} 中拉取消息进行处理的拉取次数，
     * 但由于定位到该函数最后，会调用自身再次进行处理，理应不需要该吞吐量的指标，
     * 目前分析是在处理消息或重复调用函数时，可能存在 CAS 等的一些操作有微弱的性能影响，
     * 所以才设计了该指标；
     * </pre>
     */
    @Value("${actorProcessThroughput:5}")
    private int actorProcessThroughput;

    /**
     * 初始化 actor 时的最大重试次数；
     * <pre>
     * 在 {@link DefaultActorSystem#createActor(String, ActorCreator, ActorId)} 时，
     * 会调用 {@link DefaultActorMailbox#tryInit(int)} 初始化一个 actor，
     * 该值描述了初始化 actor 失败的重试次数；
     * </pre>
     */
    @Value("${maxInitActorRetryCount:10}")
    private int maxInitActorRetryCount;

}

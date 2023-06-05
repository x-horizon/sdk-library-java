package cn.srd.itcp.sugar.topic.redisson.core;

import cn.srd.itcp.sugar.context.redisson.core.RedissonManager;
import org.redisson.api.listener.MessageListener;

/**
 * Redisson topic
 *
 * @author wjm
 * @since 2023-06-12 16:41:28
 */
public class RedissonTopics {

    /**
     * private block constructor
     */
    private RedissonTopics() {
    }

    /**
     * 发布消息
     *
     * @param topicName 主题名称
     * @param message   消息
     * @return 收到消息的客户端数量
     */
    public static long publish(String topicName, Object message) {
        return RedissonManager.getClient().getTopic(topicName).publish(message);
    }

    /**
     * 增加主题订阅者
     *
     * @param topicName 主题名称
     * @param type      消息类型
     * @param listener  订阅者
     * @param <T>       消息类型
     * @return 订阅者唯一 id
     */
    public static <T> int addListener(String topicName, Class<T> type, MessageListener<? extends T> listener) {
        return RedissonManager.getClient().getTopic(topicName).addListener(type, listener);
    }

}

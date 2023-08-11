package cn.srd.sugar.message.bus.redis.core;

import cn.srd.sugar.context.redis.core.RedisManager;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.redisson.api.listener.MessageListener;

/**
 * Redis topic
 *
 * @author wjm
 * @since 2023-06-05 16:41:28
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RedisTopic {

    /**
     * 发布消息
     *
     * @param topicName 主题名称
     * @param message   消息
     * @return 收到消息的客户端数量
     */
    @CanIgnoreReturnValue
    public static long publish(String topicName, Object message) {
        return RedisManager.getClient().getTopic(topicName).publish(message);
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
    @CanIgnoreReturnValue
    public static <T> int addListener(String topicName, Class<T> type, MessageListener<? extends T> listener) {
        return RedisManager.getClient().getTopic(topicName).addListener(type, listener);
    }

}

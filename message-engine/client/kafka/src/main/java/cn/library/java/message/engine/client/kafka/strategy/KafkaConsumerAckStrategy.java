package cn.library.java.message.engine.client.kafka.strategy;

/**
 * @author wjm
 * @since 2024-05-31 15:35
 */
public interface KafkaConsumerAckStrategy {

    default boolean needToEnableAutoCommitOffset() {
        return true;
    }

}
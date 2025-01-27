package cn.library.java.message.engine.client.kafka.strategy;

/**
 * @author wjm
 * @since 2024-05-31 15:41
 */
public class KafkaConsumerAckByManualStrategy implements KafkaConsumerAckStrategy {

    @Override
    public boolean needToEnableAutoCommitOffset() {
        return false;
    }

}
package cn.srd.library.java.message.engine.client.kafka;

import cn.srd.library.java.message.engine.client.contract.model.enums.MessageClientIdGenerateType;
import cn.srd.library.java.message.engine.client.kafka.model.enums.KafkaConsumerAckMode;
import cn.srd.library.java.message.engine.client.kafka.model.enums.KafkaConsumerListenerMode;
import cn.srd.library.java.message.engine.client.kafka.model.enums.KafkaConsumerOffsetResetMode;
import cn.srd.library.java.message.engine.client.kafka.support.KafkaDefaultConfigConstant;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wjm
 * @since 2024-05-30 15:36
 */
@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface KafkaConfig {

    ClientConfig clientConfig() default @ClientConfig();

    ProducerConfig producerConfig() default @ProducerConfig();

    ConsumerConfig consumerConfig() default @ConsumerConfig();

    @interface ClientConfig {

        // TODO wjm 此处实现不够好，与 snowflake id 强绑定，客户端不一定需要用到 snowflake id，目前客户端必须提供正确的 redis 配置，否则项目启动报错
        MessageClientIdGenerateType idGenerateType() default MessageClientIdGenerateType.SNOWFLAKE;

    }

    @interface ProducerConfig {

    }

    @interface ConsumerConfig {

        String groupId() default KafkaDefaultConfigConstant.Consumer.GROUP_ID;

        boolean allowToAutoCreateTopic() default KafkaDefaultConfigConstant.Consumer.ALLOW_TO_AUTO_CREATE_TOPIC;

        String autoCommitOffsetInterval() default KafkaDefaultConfigConstant.Consumer.AUTO_COMMIT_OFFSET_INTERVAL;

        KafkaConsumerAckMode ackMode() default KafkaConsumerAckMode.RECORD;

        KafkaConsumerListenerMode listenerMode() default KafkaConsumerListenerMode.RECORD;

        KafkaConsumerOffsetResetMode offsetResetMode() default KafkaConsumerOffsetResetMode.LATEST;

    }

}
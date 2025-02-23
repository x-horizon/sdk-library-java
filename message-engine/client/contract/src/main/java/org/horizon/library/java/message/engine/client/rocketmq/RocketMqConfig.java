package org.horizon.library.java.message.engine.client.rocketmq;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wjm
 * @since 2024-05-30 15:36
 */
@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface RocketMqConfig {

    ProducerConfig producerConfig() default @ProducerConfig();

    ConsumerConfig consumerConfig() default @ConsumerConfig();

    @interface ProducerConfig {

    }

    @interface ConsumerConfig {

    }

}
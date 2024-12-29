package cn.srd.library.java.message.engine.client.rabbitmq.autoconfigure;

import cn.srd.library.java.message.engine.client.rabbitmq.model.property.RabbitMqClientProperty;
import cn.srd.library.java.message.engine.client.rabbitmq.strategy.RabbitMqClientConfigStrategy;
import cn.srd.library.java.message.engine.client.rabbitmq.strategy.RabbitMqClientFlowStrategy;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Message Client RabbitMQ
 *
 * @author wjm
 * @since 2024-05-24 16:56
 */
@AutoConfiguration
@EnableConfigurationProperties(RabbitMqClientProperty.class)
public class RabbitMqClientAutoConfigurer {

    @Bean
    public RabbitMqClientConfigStrategy rabbitMqConfigStrategy() {
        return new RabbitMqClientConfigStrategy();
    }

    @Bean
    public RabbitMqClientFlowStrategy rabbitMqFlowStrategy() {
        return new RabbitMqClientFlowStrategy();
    }

}
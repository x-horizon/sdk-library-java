package cn.library.java.message.engine.client.rocketmq.autoconfigure;

import cn.library.java.message.engine.client.rocketmq.model.property.RocketMqClientProperty;
import cn.library.java.message.engine.client.rocketmq.strategy.RocketMqClientConfigStrategy;
import cn.library.java.message.engine.client.rocketmq.strategy.RocketMqClientFlowStrategy;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Message Client RocketMQ
 *
 * @author wjm
 * @since 2024-05-24 16:56
 */
@AutoConfiguration
@EnableConfigurationProperties(RocketMqClientProperty.class)
public class RocketMqClientAutoConfigurer {

    @Bean
    public RocketMqClientConfigStrategy rocketMqConfigStrategy() {
        return new RocketMqClientConfigStrategy();
    }

    @Bean
    public RocketMqClientFlowStrategy rocketMqFlowStrategy() {
        return new RocketMqClientFlowStrategy();
    }

}
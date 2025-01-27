package cn.library.java.message.engine.client.nil.autoconfigure;

import cn.library.java.message.engine.client.nil.strategy.MessageNilClientConfigStrategy;
import cn.library.java.message.engine.client.nil.strategy.MessageNilClientFlowStrategy;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Message Client Meaningless
 *
 * @author wjm
 * @since 2024-05-24 16:56
 */
@AutoConfiguration
public class MessageNilClientAutoConfigurer {

    @Bean
    public MessageNilClientConfigStrategy messageNilConfigStrategy() {
        return new MessageNilClientConfigStrategy();
    }

    @Bean
    public MessageNilClientFlowStrategy messageNilFlowStrategy() {
        return new MessageNilClientFlowStrategy();
    }

}
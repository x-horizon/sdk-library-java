package cn.library.java.message.engine.client.rocketmq.autoconfigure;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * provide an annotation to enable message engine rocketmq client system.
 *
 * @author wjm
 * @see MessageRocketMqClientRegistrar
 * @since 2024-05-24 16:54
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MessageRocketMqClientRegistrar.class)
public @interface EnableMessageRocketMqClient {

}
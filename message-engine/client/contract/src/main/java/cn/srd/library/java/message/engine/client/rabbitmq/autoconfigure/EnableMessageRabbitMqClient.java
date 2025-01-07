package cn.srd.library.java.message.engine.client.rabbitmq.autoconfigure;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * provide an annotation to enable message engine rabbitmq client system.
 *
 * @author wjm
 * @see MessageRabbitMqClientRegistrar
 * @since 2024-05-24 16:54
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MessageRabbitMqClientRegistrar.class)
public @interface EnableMessageRabbitMqClient {

}
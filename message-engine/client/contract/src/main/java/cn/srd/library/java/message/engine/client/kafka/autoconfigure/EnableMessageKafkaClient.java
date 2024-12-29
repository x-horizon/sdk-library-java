package cn.srd.library.java.message.engine.client.kafka.autoconfigure;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * provide an annotation to enable message engine kafka system.
 *
 * @author wjm
 * @see MessageKafkaClientRegistrar
 * @since 2024-05-24 16:54
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MessageKafkaClientRegistrar.class)
public @interface EnableMessageKafkaClient {

}
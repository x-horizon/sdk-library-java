package cn.srd.library.java.message.engine.client.mqtt.v3.autoconfigure;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * provide an annotation to enable message engine mqtt-v3 client system.
 *
 * @author wjm
 * @see MessageMqttV3ClientRegistrar
 * @since 2024-05-24 16:54
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MessageMqttV3ClientRegistrar.class)
public @interface EnableMessageMqttV3Client {

}
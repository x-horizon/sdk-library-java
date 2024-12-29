package cn.srd.library.java.message.engine.client.mqtt.v5.autoconfigure;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * provide an annotation to enable message engine mqtt-v5 system.
 *
 * @author wjm
 * @see MessageMqttV5ClientRegistrar
 * @since 2024-05-24 16:54
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MessageMqttV5ClientRegistrar.class)
public @interface EnableMessageMqttV5Client {

}
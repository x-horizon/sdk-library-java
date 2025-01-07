package cn.srd.library.java.message.engine.server.mqtt.autoconfigure;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * provide an annotation to enable message engine mqtt server system.
 *
 * @author wjm
 * @since 2025-01-07 18:30
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MessageMqttServerRegistrar.class)
public @interface EnableMessageMqttServer {

}
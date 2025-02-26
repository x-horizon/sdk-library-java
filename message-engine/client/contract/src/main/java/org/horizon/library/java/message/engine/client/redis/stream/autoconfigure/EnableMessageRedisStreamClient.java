package org.horizon.library.java.message.engine.client.redis.stream.autoconfigure;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * provide an annotation to enable message engine redis client system.
 *
 * @author wjm
 * @see MessageRedisStreamClientRegistrar
 * @since 2024-05-24 16:54
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MessageRedisStreamClientRegistrar.class)
public @interface EnableMessageRedisStreamClient {

}
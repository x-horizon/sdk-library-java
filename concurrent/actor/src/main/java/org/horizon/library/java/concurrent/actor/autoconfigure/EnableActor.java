package org.horizon.library.java.concurrent.actor.autoconfigure;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * provide an annotation to enable concurrent actor
 *
 * @author wjm
 * @see ActorRegister
 * @see ActorAutoConfigurer
 * @since 2025-01-28 00:16
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ActorRegister.class)
public @interface EnableActor {

}
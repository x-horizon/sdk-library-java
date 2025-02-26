package org.horizon.library.java.concurrent.actor.autoconfigure;

import org.horizon.library.java.concurrent.actor.message.ActorMessage;
import org.horizon.library.java.concurrent.actor.model.property.ActorProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Concurrent Actor
 *
 * @author wjm
 * @since 2025-01-26 23:14
 */
@Slf4j
@AutoConfiguration
@ConditionalOnBean(ActorRegister.class)
@EnableConfigurationProperties(ActorProperty.class)
public class ActorAutoConfigurer<T extends ActorMessage> {

    // @Autowired private ActorProperty actorProperty;
    //
    // @Bean
    // public ActorSystem<T> actorSystem() {
    //     log.debug("{}actor system is enabled, finish initialized.", ModuleView.CONCURRENT_ACTOR_SYSTEM);
    //     return new ActorSystem<>(actorProperty);
    // }

}
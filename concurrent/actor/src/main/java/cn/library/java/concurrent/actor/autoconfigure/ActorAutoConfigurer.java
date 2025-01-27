package cn.library.java.concurrent.actor.autoconfigure;

import cn.library.java.concurrent.actor.message.ActorMessage;
import cn.library.java.concurrent.actor.model.property.ActorProperty;
import cn.library.java.concurrent.actor.system.ActorSystem;
import cn.library.java.contract.constant.module.ModuleView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

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

    @Autowired private ActorProperty actorProperty;

    @Bean
    public ActorSystem<T> actorSystem() {
        log.debug("{}actor system is enabled, finish initialized.", ModuleView.CONCURRENT_ACTOR_SYSTEM);
        return new ActorSystem<>(actorProperty);
    }

}
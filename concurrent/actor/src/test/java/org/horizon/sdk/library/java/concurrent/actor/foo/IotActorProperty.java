package org.horizon.sdk.library.java.concurrent.actor.foo;

import lombok.Getter;
import lombok.Setter;
import org.horizon.sdk.library.java.concurrent.actor.model.property.ActorProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author wjm
 * @since 2025-01-28 00:32
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "library-java.concurrent.actor")
public class IotActorProperty extends ActorProperty {

    private Integer appDispatcherPoolSize = 1;

    private Integer tenantDispatcherPoolSize = 2;

    private Integer deviceDispatcherPoolSize = 4;

    private Integer ruleEngineDispatcherPoolSize = 8;

}
package cn.srd.library.java.concurrent.actor.model.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author wjm
 * @since 2025-01-26 23:12
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "library-java.concurrent.actor")
public class ActorProperty {

    private Boolean needToEnableVirtualThread = true;

    private Integer throughput = 5;

    private Integer maxInitAttemptCount = 10;

    private Integer schedulerPoolSize = 1;

}
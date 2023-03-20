package cn.srd.itcp.sugar.component.actor.thingsboard.core;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.thingsboard.server.actors.TbActorSystem;

/**
 * actor system context
 *
 * @author wjm
 * @since 2023-03-20 11:04:19
 */
@Getter
@Setter
@Component
public class ActorSystemContext {

    /**
     * actor system
     */
    private TbActorSystem actorSystem;

}

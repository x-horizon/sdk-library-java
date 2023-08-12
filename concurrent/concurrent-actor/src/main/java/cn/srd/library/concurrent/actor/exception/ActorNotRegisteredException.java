package cn.srd.library.concurrent.actor.exception;

import cn.srd.library.concurrent.actor.id.ActorId;
import lombok.Getter;

import java.io.Serial;

/**
 * @author wjm
 * @since 2023-03-23 20:09:17
 */
public class ActorNotRegisteredException extends RuntimeException {

    @Serial private static final long serialVersionUID = 8046882081150394850L;

    @Getter
    private ActorId targetId;

    public ActorNotRegisteredException(ActorId targetId, String message) {
        super(message);
        this.targetId = targetId;
    }

}

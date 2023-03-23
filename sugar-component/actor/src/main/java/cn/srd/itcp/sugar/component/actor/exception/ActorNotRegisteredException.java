package cn.srd.itcp.sugar.component.actor.exception;

import cn.srd.itcp.sugar.component.actor.id.ActorId;
import lombok.Getter;

import java.io.Serial;

public class ActorNotRegisteredException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 8046882081150394850L;

    @Getter
    private ActorId targetId;

    public ActorNotRegisteredException(ActorId targetId, String message) {
        super(message);
        this.targetId = targetId;
    }

}

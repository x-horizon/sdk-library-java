package cn.srd.sugar.concurrent.actor.exception;

import lombok.experimental.StandardException;

import java.io.Serial;

/**
 * @author wjm
 * @since 2023-03-23 20:09:17
 */
@StandardException
public class ActorException extends Exception {

    @Serial private static final long serialVersionUID = -6477813938880924542L;

}

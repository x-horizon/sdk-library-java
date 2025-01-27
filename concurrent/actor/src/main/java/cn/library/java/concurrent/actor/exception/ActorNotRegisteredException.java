package cn.library.java.concurrent.actor.exception;

import lombok.experimental.StandardException;

import java.io.Serial;

/**
 * @author wjm
 * @since 2025-01-27 00:24
 */
@StandardException
public class ActorNotRegisteredException extends RuntimeException {

    @Serial private static final long serialVersionUID = 237571087325681383L;

}
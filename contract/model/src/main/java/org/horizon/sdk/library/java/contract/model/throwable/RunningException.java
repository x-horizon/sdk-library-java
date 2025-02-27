package org.horizon.sdk.library.java.contract.model.throwable;

import lombok.experimental.StandardException;

import java.io.Serial;

/**
 * standard business exception
 *
 * @author wjm
 * @since 2020-06-07 20:52
 */
@StandardException
public class RunningException extends AbstractRuntimeException {

    @Serial private static final long serialVersionUID = 3975594528435116604L;

}
package org.horizon.sdk.library.java.contract.model.throwable;

import lombok.experimental.StandardException;

import java.io.Serial;

/**
 * @author wjm
 * @since 2025-04-12 17:02
 */
@StandardException
public class UnauthenticatedException extends RuntimeException {

    @Serial private static final long serialVersionUID = 7629173978519349494L;

}
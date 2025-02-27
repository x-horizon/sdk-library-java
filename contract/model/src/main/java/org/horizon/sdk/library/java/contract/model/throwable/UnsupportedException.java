package org.horizon.sdk.library.java.contract.model.throwable;

import lombok.experimental.StandardException;

import java.io.Serial;

/**
 * unsupported exception
 *
 * @author wjm
 * @since 2021-04-13 11:52
 */
@StandardException
public class UnsupportedException extends AbstractRuntimeException {

    @Serial private static final long serialVersionUID = -6315727057670035084L;

}
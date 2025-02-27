package org.horizon.sdk.library.java.contract.model.throwable;

import lombok.experimental.StandardException;

import java.io.Serial;

/**
 * data not found exception
 *
 * @author wjm
 * @since 2024-04-16 20:56
 */
@StandardException
public class DataNotFoundException extends AbstractRuntimeException {

    @Serial private static final long serialVersionUID = 4265717372386486126L;

}
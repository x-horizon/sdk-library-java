package org.horizon.library.java.contract.model.throwable;

import lombok.experimental.StandardException;

import java.io.Serial;

/**
 * standard client exception
 *
 * @author wjm
 * @since 2024-06-18 10:53
 */
@StandardException
public class ClientException extends AbstractRuntimeException {

    @Serial private static final long serialVersionUID = -5061184430024294179L;

}
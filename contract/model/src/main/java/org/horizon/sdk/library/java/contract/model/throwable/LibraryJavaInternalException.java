package org.horizon.sdk.library.java.contract.model.throwable;

import lombok.experimental.StandardException;

import java.io.Serial;

/**
 * library java internal exception
 *
 * @author wjm
 * @since 2023-11-08 17:26
 */
@StandardException
public class LibraryJavaInternalException extends AbstractRuntimeException {

    @Serial private static final long serialVersionUID = -6265494407089888791L;

}
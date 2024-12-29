package cn.srd.library.java.contract.model.throwable;

import lombok.experimental.StandardException;

import java.io.Serial;

/**
 * invalid argument exception
 *
 * @author wjm
 * @since 2024-07-03 10:47
 */
@StandardException
public class InvalidArgumentException extends AbstractRuntimeException {

    @Serial private static final long serialVersionUID = 8981649768811793539L;

}
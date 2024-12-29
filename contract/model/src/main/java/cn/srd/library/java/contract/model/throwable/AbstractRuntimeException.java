package cn.srd.library.java.contract.model.throwable;

import cn.srd.library.java.contract.constant.web.HttpStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.StandardException;

import java.io.Serial;

/**
 * standard runtime exception
 *
 * @author wjm
 * @since 2020-06-07 20:52
 */
@Getter
@Setter
@Accessors(chain = true)
@StandardException
public abstract class AbstractRuntimeException extends RuntimeException {

    @Serial private static final long serialVersionUID = 8499653235633312994L;

    private Integer status = HttpStatus.INTERNAL_ERROR.getStatus();

}
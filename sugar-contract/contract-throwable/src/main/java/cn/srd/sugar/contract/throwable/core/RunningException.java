package cn.srd.sugar.contract.throwable.core;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 自定义运行时异常
 *
 * @author wjm
 * @since 2020/6/7 20:52
 */
@Getter
@Setter
public class RunningException extends RuntimeException {

    @Serial private static final long serialVersionUID = 3975594528435116604L;

    /**
     * 自定义异常模板
     */
    ExceptionTemplate exceptionTemplate;

    /**
     * public constructor
     *
     * @param message 异常信息
     */
    public RunningException(String message) {
        super(message);
    }

    /**
     * public constructor
     *
     * @param status  状态码
     * @param message 异常信息
     */
    public RunningException(Integer status, String message) {
        super(message);
        this.exceptionTemplate = new ExceptionTemplate() {
            @Override
            public Integer getCode() {
                return status;
            }

            @Override
            public String getDescription() {
                return message;
            }

            @Override
            public Exception getException() {
                return null;
            }
        };
    }

    /**
     * public constructor
     *
     * @param message 异常信息
     * @param cause   wrapper exception
     */
    public RunningException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * public constructor
     *
     * @param exceptionTemplate 自定义异常模板
     */
    public RunningException(ExceptionTemplate exceptionTemplate) {
        super(exceptionTemplate.getDescription());
        this.exceptionTemplate = exceptionTemplate;
    }

    /**
     * public constructor
     *
     * @param exceptionTemplate 自定义异常模板
     * @param cause             wrapper exception
     */
    public RunningException(ExceptionTemplate exceptionTemplate, Throwable cause) {
        super(exceptionTemplate.getDescription(), cause);
        this.exceptionTemplate = exceptionTemplate;
    }

}

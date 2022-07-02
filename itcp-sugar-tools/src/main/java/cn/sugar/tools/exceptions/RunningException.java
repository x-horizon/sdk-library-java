package cn.sugar.tools.exceptions;

import lombok.Getter;
import lombok.Setter;

/**
 * 自定义运行时异常
 *
 * @author wjm
 * @date 2020/6/7 20:52
 */
@Getter
@Setter
public class RunningException extends RuntimeException {

    ExceptionTemplate exceptionTemplate;

    public RunningException(String msg) {
        super(msg);
    }

    public RunningException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public RunningException(ExceptionTemplate exceptionTemplate) {
        super(exceptionTemplate.getDescription());
        this.exceptionTemplate = exceptionTemplate;
    }

    public RunningException(ExceptionTemplate exceptionTemplate, Throwable cause) {
        super(exceptionTemplate.getDescription(), cause);
        this.exceptionTemplate = exceptionTemplate;
    }

}

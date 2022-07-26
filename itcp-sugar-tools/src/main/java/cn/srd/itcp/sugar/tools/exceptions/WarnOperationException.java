package cn.srd.itcp.sugar.tools.exceptions;

import cn.srd.itcp.sugar.tools.core.asserts.WarnAssert;

/**
 * 用于告警的异常，参考{@link WarnAssert}
 *
 * @author wjm
 * @since 2021/5/6 17:27
 */
public class WarnOperationException extends RuntimeException {

    private static final long serialVersionUID = 8208004569924712655L;

    ExceptionTemplate exceptionTemplate;

    public WarnOperationException(ExceptionTemplate exceptionTemplate) {
        super(exceptionTemplate.getDescription());
        this.exceptionTemplate = exceptionTemplate;
    }

    public WarnOperationException(ExceptionTemplate exceptionTemplate, Throwable cause) {
        super(exceptionTemplate.getDescription(), cause);
        this.exceptionTemplate = exceptionTemplate;
    }

    public ExceptionTemplate getExceptionTemplate() {
        return exceptionTemplate;
    }

    public void setExceptionTemplate(ExceptionTemplate exceptionTemplate) {
        this.exceptionTemplate = exceptionTemplate;
    }

}

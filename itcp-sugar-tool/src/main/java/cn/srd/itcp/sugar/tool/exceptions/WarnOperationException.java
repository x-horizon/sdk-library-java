package cn.srd.itcp.sugar.tool.exceptions;

import cn.srd.itcp.sugar.tool.core.asserts.WarnAssert;

import java.io.Serial;

/**
 * 用于告警的异常，参考{@link WarnAssert}
 *
 * @author wjm
 * @since 2021/5/6 17:27
 */
public class WarnOperationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 8208004569924712655L;

    /**
     * 自定义异常模板
     */
    ExceptionTemplate exceptionTemplate;

    /**
     * public constructor
     *
     * @param exceptionTemplate 自定义异常模板
     */
    public WarnOperationException(ExceptionTemplate exceptionTemplate) {
        super(exceptionTemplate.getDescription());
        this.exceptionTemplate = exceptionTemplate;
    }

    /**
     * public constructor
     *
     * @param exceptionTemplate 自定义异常模板
     * @param cause             wrapper exception
     */
    public WarnOperationException(ExceptionTemplate exceptionTemplate, Throwable cause) {
        super(exceptionTemplate.getDescription(), cause);
        this.exceptionTemplate = exceptionTemplate;
    }

    /**
     * 获取异常模板
     *
     * @return 自定义异常模板
     */
    public ExceptionTemplate getExceptionTemplate() {
        return exceptionTemplate;
    }

    /**
     * 设置异常模板
     *
     * @param exceptionTemplate 自定义异常模板
     */
    public void setExceptionTemplate(ExceptionTemplate exceptionTemplate) {
        this.exceptionTemplate = exceptionTemplate;
    }

}

package cn.srd.itcp.sugar.framework.spring.tool.webflux.support;

import java.io.Serial;

/**
 * 用于标记没加入 {@link WebFluxExceptionHandler#EXCEPTION_HANDLERS} 的异常处理逻辑，最终未找到异常处理器时，进入该异常
 *
 * @author wjm
 * @since 2023-02-27 17:10:34
 */
public class NotRegisterHandlerException extends Throwable {

    @Serial
    private static final long serialVersionUID = 5735385740693788825L;
    
}

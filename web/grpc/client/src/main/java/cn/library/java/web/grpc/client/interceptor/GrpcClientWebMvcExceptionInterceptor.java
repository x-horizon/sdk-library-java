package cn.library.java.web.grpc.client.interceptor;

import cn.library.java.contract.constant.module.ModuleView;
import cn.library.java.contract.constant.spring.SpringInitializeConstant;
import cn.library.java.contract.constant.web.HttpStatus;
import cn.library.java.contract.model.protocol.WebResponse;
import cn.library.java.contract.model.throwable.ClientException;
import cn.library.java.contract.model.throwable.DataNotFoundException;
import cn.library.java.contract.model.throwable.UnsupportedException;
import cn.library.java.tool.spring.webmvc.interceptor.WebMvcExceptionInterceptor;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.StatusRuntimeException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static cn.library.java.contract.model.protocol.WebResponse.error;

/**
 * @author wjm
 * @since 2024-06-17 17:46
 */
@Slf4j
@Order(SpringInitializeConstant.LOWER_INITIALIZE_PRIORITY)
@RestControllerAdvice
public class GrpcClientWebMvcExceptionInterceptor extends WebMvcExceptionInterceptor {

    @Override
    protected String getModuleView() {
        return ModuleView.WEB_GRPC_SYSTEM;
    }

    @ExceptionHandler({StatusRuntimeException.class, StatusException.class})
    public WebResponse<Void> handleStatusException(HttpServletRequest httpServletRequest, StatusRuntimeException exception) {
        return switch (exception.getStatus().getCode()) {
            case Status.Code.UNIMPLEMENTED -> handleUnsupportedException(httpServletRequest, new UnsupportedException(exception.getStatus().getDescription()));
            case Status.Code.NOT_FOUND -> handleDataNotFoundException(httpServletRequest, new DataNotFoundException(exception.getStatus().getDescription()));
            case Status.Code.ABORTED -> handleClientException(httpServletRequest, new ClientException(exception.getStatus().getDescription()));
            default -> error(HttpStatus.INTERNAL_ERROR);
        };
    }

}
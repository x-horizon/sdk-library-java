package cn.srd.itcp.sugar.framework.spring.tool.webmvc.support;

import cn.srd.itcp.sugar.tool.core.Objects;
import cn.srd.itcp.sugar.tool.web.WebResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * spring mvc 响应结果通知
 *
 * @author wjm
 * @since 2020/6/13 20:05
 */
@ControllerAdvice
public class WebMVCResponseAdvice implements ResponseBodyAdvice<WebResponse<?>> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class converterType) {
        if (Objects.isNull(methodParameter.getMethod())) {
            return false;
        }
        /**
         * 只拦截响应参数为{@link WebResponse}的类型
         */
        return methodParameter.getMethod().getReturnType() == WebResponse.class;
    }

    @Override
    public WebResponse<?> beforeBodyWrite(WebResponse<?> webResponse, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> httpMessageConverter, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // 统一格式化响应信息
        return webResponse;
    }

}

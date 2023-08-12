package cn.srd.library.tool.spring.webmvc.support;

import cn.srd.library.tool.lang.core.object.Objects;
import cn.srd.library.tool.lang.web.WebResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
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
    public boolean supports(MethodParameter methodParameter, @NonNull Class converterType) {
        return Objects.isNotNull(methodParameter.getMethod()) && methodParameter.getMethod().getReturnType() == WebResponse.class;
    }

    @Override
    public WebResponse<?> beforeBodyWrite(WebResponse<?> webResponse, @NonNull MethodParameter methodParameter, @NonNull MediaType mediaType, @NonNull Class<? extends HttpMessageConverter<?>> httpMessageConverter, @NonNull ServerHttpRequest serverHttpRequest, @NonNull ServerHttpResponse serverHttpResponse) {
        return webResponse;
    }

}

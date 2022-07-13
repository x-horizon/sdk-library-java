package cn.srd.itcp.sugar.tools.web;

import cn.srd.itcp.sugar.tools.core.Objects;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * webmvc 响应结果通知
 *
 * @author wjm
 * @date 2020/6/13 20:05
 */
@ControllerAdvice
public class WebResponseAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 统一格式化响应信息
     *
     * @param body
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        return body;
    }

    /**
     * 只拦截返回结果为 {@link WebResponse} 类型
     *
     * @param returnType
     * @param converterType
     * @return
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        if (Objects.isNull(returnType.getMethod())) {
            return false;
        }
        return returnType.getMethod().getReturnType() == WebResponse.class;
    }

}

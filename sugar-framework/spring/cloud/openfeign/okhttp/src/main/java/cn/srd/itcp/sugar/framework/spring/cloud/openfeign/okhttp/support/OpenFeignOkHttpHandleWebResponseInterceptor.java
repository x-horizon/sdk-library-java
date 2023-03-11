package cn.srd.itcp.sugar.framework.spring.cloud.openfeign.okhttp.support;

import cn.srd.itcp.sugar.component.convert.all.core.Converts;
import cn.srd.itcp.sugar.tool.core.ReflectsUtil;
import cn.srd.itcp.sugar.tool.web.WebResponse;
import lombok.SneakyThrows;

/**
 * open feign okhttp 响应拦截器，用于拦截 {@link WebResponse}
 *
 * @author wjm
 * @since 2023-03-04 16:48:19
 */
public class OpenFeignOkHttpHandleWebResponseInterceptor implements OpenFeignOkHttpInterceptor<WebResponse<?>> {

    @SneakyThrows
    @Override
    public WebResponse<?> parse(String responseBody) {
        WebResponse<?> response = Converts.withJackson().toBean(responseBody, WebResponse.class);
        if (response.errorIs()) {
            throw ReflectsUtil.newInstance(getExceptionToThrow(), response.getStatus(), response.getMessage());
        }
        return response;
    }

    @Override
    public String filter(WebResponse<?> response) {
        return Converts.withJackson().toString(response.getData());
    }

}
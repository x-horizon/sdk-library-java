package cn.srd.itcp.sugar.framework.spring.cloud.openfeign.okhttp.support;

import cn.srd.itcp.sugar.component.convert.all.core.Converts;
import cn.srd.itcp.sugar.tool.exceptions.RunningException;
import cn.srd.itcp.sugar.tool.web.WebResponse;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;

/**
 * open feign okhttp 响应拦截器，用于拦截 {@link WebResponse}
 *
 * @author wjm
 * @since 2023-03-04 16:48:19
 */
public class OpenFeignOkHttpHandleWebResponseInterceptor implements OpenFeignOkHttpInterceptor<WebResponse<?>> {

    @SneakyThrows
    @Override
    public String parseProductionResponse(ResponseBody responseBody) {
        WebResponse<?> productionResponse = Converts.withJackson().toBean(responseBody.string(), WebResponse.class);
        if (productionResponse.errorIs()) {
            throw new RunningException(productionResponse.getStatus(), productionResponse.getMessage());
        }
        return Converts.withJackson().toString(productionResponse.getData());
    }

}
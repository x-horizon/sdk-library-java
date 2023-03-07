package cn.srd.itcp.sugar.framework.spring.cloud.openfeign.okhttp.support;

import lombok.SneakyThrows;
import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.lang.NonNull;

/**
 * open feign okhttp 拦截器
 *
 * @author wjm
 * @since 2023-03-04 16:48:19
 */
public interface OpenFeignOkHttpInterceptor<T> extends Interceptor {

    /**
     * 解析生产端响应结果
     *
     * @param responseBody okhttp response body
     * @return 生产端响应结果
     */
    String parseProductionResponse(ResponseBody responseBody);

    @SneakyThrows
    @NonNull
    @Override
    default Response intercept(Chain chain) {
        Response response = chain.proceed(chain.request());
        ResponseBody responseBody = response.body();
        return response.newBuilder()
                .body(ResponseBody.create(parseProductionResponse(responseBody), responseBody.contentType()))
                .build();
    }

}
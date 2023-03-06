package cn.srd.itcp.sugar.framework.spring.cloud.openfeign.okhttp.support;

import lombok.SneakyThrows;
import okhttp3.Interceptor;
import okhttp3.Response;
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
     * @param response okhttp response
     * @return 生产端响应结果
     */
    T parseProductionResponse(Response response);

    /**
     * 构造 okhttp response
     *
     * @param response           okhttp response
     * @param productionResponse 生产端响应结果
     * @return okhttp response
     */
    Response ofResponse(Response response, T productionResponse);

    @SneakyThrows
    @NonNull
    @Override
    default Response intercept(Chain chain) {
        Response response = chain.proceed(chain.request());
        return ofResponse(response, parseProductionResponse(response));
    }

}
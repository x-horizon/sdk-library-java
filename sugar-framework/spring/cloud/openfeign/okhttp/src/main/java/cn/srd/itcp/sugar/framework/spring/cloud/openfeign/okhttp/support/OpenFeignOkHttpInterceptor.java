package cn.srd.itcp.sugar.framework.spring.cloud.openfeign.okhttp.support;

import cn.srd.itcp.sugar.tool.core.ClassesUtil;
import cn.srd.itcp.sugar.tool.core.Objects;
import cn.srd.itcp.sugar.tool.exceptions.RunningException;
import io.vavr.control.Try;
import lombok.Cleanup;
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
    T parse(String responseBody);

    /**
     * 从 {@link #parse(String)} 中过滤后的数据作为消费端接收的结果
     *
     * @param response 生产端响应结果
     * @return 消费端接收的结果
     */
    String filter(T response);

    /**
     * <pre>
     *    当前拦截器在遇到该函数返回的异常时，进行抛出；
     *    若遇到不是该函数返回的异常，该拦截器返回拦截前的数据，由下一拦截器继续拦截
     * </pre>
     *
     * @return 要抛出的异常
     */
    default Class<? extends Throwable> getExceptionToThrow() {
        return RunningException.class;
    }

    @SneakyThrows
    @NonNull
    @Override
    default Response intercept(Chain chain) {
        @Cleanup Response response = chain.proceed(chain.request());
        ResponseBody responseBody = response.body();
        String responseBodyString = responseBody.string();
        return response.newBuilder()
                .body(ResponseBody.create(
                        Try.of(() -> filter(parse(responseBodyString)))
                                .onFailure(throwable -> Objects.throwsIfTrue(ClassesUtil.isAssignable(getExceptionToThrow(), throwable.getClass()), throwable))
                                .getOrElse(responseBodyString),
                        responseBody.contentType()
                ))
                .build();
    }

}
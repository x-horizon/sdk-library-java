package org.horizon.library.java.web.openfeign.interceptor;

import org.horizon.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.library.java.contract.model.protocol.TransportModel;
import org.horizon.library.java.contract.model.throwable.LibraryJavaInternalException;
import org.horizon.library.java.tool.convert.api.Converts;
import org.horizon.library.java.tool.lang.object.Classes;
import org.horizon.library.java.tool.lang.object.Nil;
import org.horizon.library.java.tool.lang.text.Strings;
import org.horizon.library.java.web.openfeign.autoconfigue.EnableOpenFeignClientResponseModelResolver;
import org.horizon.library.java.web.openfeign.cache.OpenFeignClientResponseModelCache;
import io.vavr.control.Try;
import lombok.Cleanup;
import lombok.SneakyThrows;
import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.NonNull;

/**
 * open feign response interceptor
 *
 * @author wjm
 * @since 2023-03-04 16:48
 */
public class OpenFeignClientResponseInterceptor implements Interceptor {

    @SneakyThrows
    @NonNull
    @Override
    public Response intercept(@NotNull Chain chain) {
        @Cleanup Response response = chain.proceed(chain.request());
        ResponseBody responseBody = response.body();
        if (Nil.isNull(responseBody)) {
            return response;
        }
        return response.newBuilder().body(ResponseBody.create(resolve(responseBody.string()), responseBody.contentType())).build();
    }

    /**
     * resolve response body
     *
     * @param responseBody okhttp response body
     * @return the resolve result
     */
    @SneakyThrows
    @SuppressWarnings(SuppressWarningConstant.RAW_TYPE)
    public String resolve(String responseBody) {
        TransportModel<?> responseModel = null;
        for (Class<? extends TransportModel> responseModelClass : OpenFeignClientResponseModelCache.get()) {
            responseModel = Try.of(() -> Converts.onJackson().toBean(responseBody, responseModelClass)).getOrNull();
            if (Nil.isNotNull(responseModel)) {
                break;
            }
        }
        if (Nil.isNull(responseModel)) {
            throw new LibraryJavaInternalException(Strings.format(
                    "\ncould not parse feign result to any model defined in class [{}], \ncurrent define models are {}, \nplease check your config! \ncurrent feign result is {}",
                    Classes.getClassFullName(EnableOpenFeignClientResponseModelResolver.class),
                    org.horizon.library.java.tool.lang.convert.Converts.toList(OpenFeignClientResponseModelCache.get(), Classes::getClassFullName),
                    responseBody
            ));
        }
        if (responseModel.notSuccessIs()) {
            throw responseModel.buildRunningException();
        }
        return Converts.onJackson().toString(responseModel.getData());
    }

}
// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.web.openfeign;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.contract.model.protocol.TransportModel;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.tool.convert.all.Converts;
import cn.srd.library.java.tool.lang.object.Classes;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.text.Strings;
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
public class FeignClientResponseInterceptor implements Interceptor {

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
        for (Class<? extends TransportModel> responseModelClass : FeignClientResponseModelCache.get()) {
            responseModel = Try.of(() -> Converts.withJackson().toBean(responseBody, responseModelClass)).getOrNull();
            if (Nil.isNotNull(responseModel)) {
                break;
            }
        }
        if (Nil.isNull(responseModel)) {
            throw new LibraryJavaInternalException(Strings.format(
                    "\ncould not parse feign result to any model defined in class [{}], \ncurrent define models are {}, \nplease check your config! \ncurrent feign result is {}",
                    Classes.getClassFullName(EnableFeignClientResponseModelResolver.class),
                    cn.srd.library.java.tool.lang.convert.Converts.toList(FeignClientResponseModelCache.get(), Classes::getClassFullName),
                    responseBody
            ));
        }
        if (responseModel.notSuccessIs()) {
            throw responseModel.buildRunningException();
        }
        return Converts.withJackson().toString(responseModel.getData());
    }

}
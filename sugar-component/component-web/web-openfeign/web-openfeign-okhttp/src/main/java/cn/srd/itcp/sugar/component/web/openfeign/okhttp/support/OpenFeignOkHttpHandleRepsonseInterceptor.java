package cn.srd.itcp.sugar.component.web.openfeign.okhttp.support;

import cn.srd.itcp.sugar.component.convert.all.core.Converts;
import cn.srd.itcp.sugar.component.web.openfeign.okhttp.core.EnableOpenFeignOkHttpHandleResponseInterceptor;
import cn.srd.itcp.sugar.tool.core.ClassesUtil;
import cn.srd.itcp.sugar.tool.core.Objects;
import cn.srd.itcp.sugar.tool.core.StringsUtil;
import cn.srd.itcp.sugar.tool.exceptions.RunningException;
import cn.srd.itcp.sugar.tool.web.ResponseModel;
import io.vavr.control.Try;
import lombok.Cleanup;
import lombok.SneakyThrows;
import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.NonNull;

/**
 * open feign okhttp 拦截器
 *
 * @author wjm
 * @since 2023-03-04 16:48:19
 */
public class OpenFeignOkHttpHandleRepsonseInterceptor implements Interceptor {

    /**
     * 解析生产端响应结果
     *
     * @param responseBody okhttp response body
     * @return 生产端响应结果
     */
    @SneakyThrows
    public String parse(String responseBody) {
        ResponseModel<?> responseModel = null;
        for (Class<? extends ResponseModel> responseModelClass : OpenFeignOkHttpConfigurator.RESPONSE_MODELS_TO_PARSE) {
            responseModel = Try.of(() -> Converts.withJackson().toBean(responseBody, responseModelClass)).getOrNull();
            if (Objects.isNotNull(responseModel)) {
                break;
            }
        }
        if (Objects.isNull(responseModel)) {
            throw new RunningException(StringsUtil.format(
                    "\ncannot parse feign result to any model defined in class[{}], \ncurrent define models are[{}], \nplease check your config! \ncurrent feign result is[{}]",
                    ClassesUtil.getClassFullName(EnableOpenFeignOkHttpHandleResponseInterceptor.class),
                    StringsUtil.pretty(OpenFeignOkHttpConfigurator.RESPONSE_MODEL_NAMES_TO_PARSE),
                    responseBody
            ));
        }
        if (responseModel.errorIs()) {
            throw responseModel.buildRunningException();
        }
        return Converts.withJackson().toString(responseModel.getData());
    }

    @SneakyThrows
    @NonNull
    @Override
    public Response intercept(@NotNull Chain chain) {
        @Cleanup Response response = chain.proceed(chain.request());
        ResponseBody responseBody = response.body();
        String responseBodyString = responseBody.string();
        return response.newBuilder()
                .body(ResponseBody.create(parse(responseBodyString), responseBody.contentType()))
                .build();
    }

}
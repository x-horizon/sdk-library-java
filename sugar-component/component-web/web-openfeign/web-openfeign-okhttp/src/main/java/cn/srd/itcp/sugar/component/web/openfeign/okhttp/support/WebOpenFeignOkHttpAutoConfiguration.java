package cn.srd.itcp.sugar.component.web.openfeign.okhttp.support;

import cn.srd.itcp.sugar.component.web.openfeign.okhttp.core.EnableOpenFeignOkHttpHandleResponseInterceptor;
import cn.srd.itcp.sugar.framework.spring.tool.common.core.SpringsUtil;
import cn.srd.itcp.sugar.tool.constant.StringPool;
import cn.srd.itcp.sugar.tool.core.AnnotationsUtil;
import cn.srd.itcp.sugar.tool.core.ClassesUtil;
import cn.srd.itcp.sugar.tool.core.CollectionsUtil;
import cn.srd.itcp.sugar.tool.core.Objects;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Set;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Sugar Framework Spring Cloud Open Feign OkHttp
 *
 * @author wjm
 * @since 2023-03-04 16:48:19
 */
@AutoConfiguration
@ConditionalOnProperty(name = "spring.cloud.openfeign.okhttp.enabled", havingValue = StringPool.TRUE, matchIfMissing = false)
public class WebOpenFeignOkHttpAutoConfiguration implements OpenFeignOkHttpConfigurator {

    /**
     * 装配 {@link OkHttpClient.Builder}
     *
     * @return 装配对象
     */
    @Bean
    public OkHttpClient.Builder okHttpClientBuilder() {
        Set<Class<?>> classesWithEnableOpenFeignOkHttpHandleResponseInterceptor = SpringsUtil.scanPackageByAnnotation(EnableOpenFeignOkHttpHandleResponseInterceptor.class);
        if (Objects.isNotEmpty(classesWithEnableOpenFeignOkHttpHandleResponseInterceptor)) {
            classesWithEnableOpenFeignOkHttpHandleResponseInterceptor.forEach(item -> {
                EnableOpenFeignOkHttpHandleResponseInterceptor enableOpenFeignOkHttpHandleResponseInterceptor = AnnotationsUtil.getAnnotation(item, EnableOpenFeignOkHttpHandleResponseInterceptor.class);
                RESPONSE_MODELS_TO_PARSE.addAll(Arrays.asList(enableOpenFeignOkHttpHandleResponseInterceptor.models()));
            });
            RESPONSE_MODEL_NAMES_TO_PARSE.addAll(CollectionsUtil.toList(RESPONSE_MODELS_TO_PARSE, ClassesUtil::getClassFullName));
            OKHTTP_CLIENT_BUILDER_INSTANCE.addInterceptor(new OpenFeignOkHttpHandleRepsonseInterceptor());
        }
        return OKHTTP_CLIENT_BUILDER_INSTANCE;
    }

}

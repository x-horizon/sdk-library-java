package org.horizon.sdk.library.java.web.openfeign.autoconfigure;

import okhttp3.OkHttpClient;
import org.horizon.sdk.library.java.contract.constant.booleans.BooleanConstant;
import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.horizon.sdk.library.java.tool.spring.contract.support.Annotations;
import org.horizon.sdk.library.java.web.openfeign.cache.OpenFeignClientResponseModelCache;
import org.horizon.sdk.library.java.web.openfeign.interceptor.OpenFeignClientResponseInterceptor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import java.util.Set;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Web Open Feign
 *
 * @author wjm
 * @since 2023-03-04 16:48
 */
@AutoConfiguration
@ConditionalOnProperty(name = "spring.cloud.openfeign.okhttp.enabled", havingValue = BooleanConstant.TRUE_STRING_LOWER_CASE)
public class OpenFeignAutoConfigurer {

    /**
     * get singleton {@link OkHttpClient.Builder}
     */
    private static final OkHttpClient.Builder OKHTTP_CLIENT_BUILDER_INSTANCE = new OkHttpClient.Builder();

    /**
     * rebuild {@link OkHttpClient.Builder}
     *
     * @return 装配对象
     */
    @SuppressWarnings(SuppressWarningConstant.RAW_TYPE)
    @Bean
    public OkHttpClient.Builder okHttpClientBuilder() {
        Set<Class> resolvedModels = Annotations.getAnnotationNestValues(EnableOpenFeignClientResponseModelResolver.class, Class[].class);
        if (Nil.isNotEmpty(resolvedModels)) {
            OpenFeignClientResponseModelCache.set(resolvedModels);
            OKHTTP_CLIENT_BUILDER_INSTANCE.addInterceptor(new OpenFeignClientResponseInterceptor());
        }
        return OKHTTP_CLIENT_BUILDER_INSTANCE;
    }

}
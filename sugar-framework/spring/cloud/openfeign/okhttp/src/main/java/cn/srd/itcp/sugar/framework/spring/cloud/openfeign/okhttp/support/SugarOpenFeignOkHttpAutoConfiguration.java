package cn.srd.itcp.sugar.framework.spring.cloud.openfeign.okhttp.support;

import cn.srd.itcp.sugar.framework.spring.cloud.openfeign.okhttp.core.EnableOpenFeignOkHttpInterceptor;
import cn.srd.itcp.sugar.framework.spring.tool.common.core.SpringsUtil;
import cn.srd.itcp.sugar.tool.constant.StringPool;
import cn.srd.itcp.sugar.tool.core.AnnotationsUtil;
import cn.srd.itcp.sugar.tool.core.ReflectsUtil;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Sugar Open Feign OkHttp
 *
 * @author wjm
 * @since 2023-03-04 16:48:19
 */
@AutoConfiguration
@ConditionalOnProperty(name = "spring.cloud.openfeign.okhttp.enabled", havingValue = StringPool.TRUE, matchIfMissing = false)
public class SugarOpenFeignOkHttpAutoConfiguration implements OpenFeignOkHttpConfigurator {

    /**
     * 装配 {@link OkHttpClient.Builder}
     *
     * @return 装配对象
     */
    @Bean
    public OkHttpClient.Builder okHttpClientBuilder() {
        Set<Class<?>> classesWithEnableOpenFeignOkHttpInterceptor = SpringsUtil.scanPackageByAnnotation(EnableOpenFeignOkHttpInterceptor.class);
        Set<Class<? extends OpenFeignOkHttpInterceptor<?>>> interceptors = new HashSet<>();
        classesWithEnableOpenFeignOkHttpInterceptor.forEach(item -> {
            EnableOpenFeignOkHttpInterceptor enableOpenFeignOkHttpInterceptor = AnnotationsUtil.getAnnotation(item, EnableOpenFeignOkHttpInterceptor.class);
            interceptors.addAll(Arrays.asList(enableOpenFeignOkHttpInterceptor.interceptors()));
        });
        interceptors.forEach(interceptor -> OKHTTP_CLIENT_BUILDER_INSTANCE.addInterceptor(ReflectsUtil.newInstance(interceptor)));
        return OKHTTP_CLIENT_BUILDER_INSTANCE;
    }

}

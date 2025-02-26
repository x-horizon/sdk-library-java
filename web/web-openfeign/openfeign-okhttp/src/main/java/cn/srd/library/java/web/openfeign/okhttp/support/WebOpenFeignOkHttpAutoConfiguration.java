package cn.srd.library.java.web.openfeign.okhttp.support;

import cn.srd.library.java.tool.constant.core.StringPool;
import cn.srd.library.java.tool.lang.core.AnnotationsUtil;
import cn.srd.library.java.tool.lang.core.ClassesUtil;
import cn.srd.library.java.tool.lang.core.CollectionsUtil;
import cn.srd.library.java.tool.lang.core.object.Objects;
import cn.srd.library.java.tool.spring.common.core.SpringsUtil;
import cn.srd.library.java.web.openfeign.okhttp.core.EnableOpenFeignOkHttpHandleResponseInterceptor;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Set;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Framework Spring Cloud Open Feign OkHttp
 *
 * @author wjm
 * @since 2023-03-04 16:48:19
 */
@AutoConfiguration
// 解决与 3.0 代码共存的冲突问题
@ConditionalOnMissingClass("cn.srd.library.java.web.openfeign.autoconfigue.OpenFeignAutoConfiguration")
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

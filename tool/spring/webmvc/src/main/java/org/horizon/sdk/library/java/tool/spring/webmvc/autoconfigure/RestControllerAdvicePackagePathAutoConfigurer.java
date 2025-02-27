package org.horizon.sdk.library.java.tool.spring.webmvc.autoconfigure;

import org.horizon.sdk.library.java.contract.constant.module.ModuleView;
import org.horizon.sdk.library.java.contract.constant.spring.SpringInitializeConstant;
import org.horizon.sdk.library.java.contract.constant.spring.SpringWebMVCConstant;
import org.horizon.sdk.library.java.tool.lang.annotation.Annotations;
import org.horizon.sdk.library.java.tool.lang.convert.Converts;
import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.horizon.sdk.library.java.tool.lang.reflect.Reflects;
import org.horizon.sdk.library.java.tool.spring.contract.support.Classes;
import org.horizon.sdk.library.java.tool.spring.webmvc.advice.WebMvcResponseBodyAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;
import java.util.Set;

/**
 * @author wjm
 * @since 2023-10-07 15:23
 */
@Slf4j
@Order(SpringInitializeConstant.HIGH_INITIALIZE_PRIORITY)
public class RestControllerAdvicePackagePathAutoConfigurer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(@NonNull ConfigurableApplicationContext applicationContext) {
        log.debug("{}rest controller advice package path starting optimizing...", ModuleView.TOOL_SPRING_WEBMVC_SYSTEM);

        Optional.ofNullable(Annotations.getAnnotation(EnableWebMvcResponseBodyAdvice.class)).ifPresent(webMVCResponseBodyAdvice -> {
            Set<String> advicePackagePaths = Classes.parseAntStylePackagePathsToPackagePaths(webMVCResponseBodyAdvice.advicePackagePaths());
            if (Nil.isNotEmpty(advicePackagePaths)) {
                RestControllerAdvice restControllerAdvice = Annotations.getAnnotation(WebMvcResponseBodyAdvice.class, RestControllerAdvice.class);
                String[] beforeReplaceBasePackagePaths = restControllerAdvice.basePackages();
                Reflects.setAnnotationValue(restControllerAdvice, SpringWebMVCConstant.FIELD_NAME_BASE_PACKAGE_ON_ANNOTATION_REST_CONTROLLER_ADVICE, Converts.toArray(advicePackagePaths, String[]::new));
                log.debug("{}replace the annotation [@{}] field [{}] value on class [@{}], before replace value {}, after replace value {}.",
                        ModuleView.TOOL_SPRING_WEBMVC_SYSTEM,
                        RestControllerAdvice.class.getSimpleName(),
                        SpringWebMVCConstant.FIELD_NAME_BASE_PACKAGE_ON_ANNOTATION_REST_CONTROLLER_ADVICE,
                        WebMvcResponseBodyAdvice.class.getSimpleName(),
                        beforeReplaceBasePackagePaths,
                        advicePackagePaths
                );
            }
        });

        log.debug("{}rest controller advice package path has been optimized.", ModuleView.TOOL_SPRING_WEBMVC_SYSTEM);
    }

}
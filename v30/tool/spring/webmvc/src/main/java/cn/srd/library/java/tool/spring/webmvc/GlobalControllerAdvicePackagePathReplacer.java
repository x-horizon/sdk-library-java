// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.spring.webmvc;

import cn.srd.library.java.contract.constant.jvm.SuppressWarningConstant;
import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.tool.lang.functional.Assert;
import cn.srd.library.java.tool.lang.object.BasePackagePath;
import cn.srd.library.java.tool.lang.reflect.Reflects;
import cn.srd.library.java.tool.lang.text.Strings;
import cn.srd.library.java.tool.spring.base.Classes;
import cn.srd.library.java.tool.spring.base.Springs;
import jakarta.annotation.PostConstruct;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.handler.HandlerExceptionResolverComposite;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.util.Map;
import java.util.Set;

/**
 * @author wjm
 * @since 2023-10-07 15:23
 */
public class GlobalControllerAdvicePackagePathReplacer {

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    @PostConstruct
    public void init() {
        Set<String> scanPackagePaths = Classes.parseAnnotationAntStylePackagePathToPackagePath(EnableGlobalControllerAdvicePackagePathReplacer.class);
        Assert.of().setMessage(Strings.format("{}could not found the package path to replace controller advice default package path because the class marked with [@{}] does not exist on these paths {}.", ModuleView.WEB_INTERCEPTOR, EnableGlobalControllerAdvicePackagePathReplacer.class.getSimpleName(), BasePackagePath.get(Springs.getSpringBootApplicationPackagePath())))
                .throwsIfEmpty(scanPackagePaths);

        HandlerExceptionResolver handlerExceptionResolver = Springs.getBean(Strings.lowerFirst(HandlerExceptionResolver.class.getSimpleName()), HandlerExceptionResolver.class);
        if (handlerExceptionResolver instanceof HandlerExceptionResolverComposite handlerExceptionResolverComposite) {
            handlerExceptionResolverComposite.getExceptionResolvers().forEach(exceptionResolver -> {
                if (exceptionResolver instanceof ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver) {
                    Reflects.getFieldValue(exceptionHandlerExceptionResolver, "exceptionHandlerAdviceCache", Map.class)
                            .keySet()
                            .forEach(controllerAdviceBean -> {
                                HandlerTypePredicate handlerTypePredicate = Reflects.getFieldValue(controllerAdviceBean, "beanTypePredicate", HandlerTypePredicate.class);
                                Reflects.setFieldValue(handlerTypePredicate, "basePackages", scanPackagePaths);
                            });
                }
            });
        }
    }

}

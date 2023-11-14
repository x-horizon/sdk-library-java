// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.spring.webmvc.advice;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.constant.spring.SpringWebMVCConstant;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.spring.contract.Annotations;
import cn.srd.library.java.tool.spring.contract.Classes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Set;

/**
 * TODO wjm bug 需要最早初始化而不是最后初始化，但若使用 ApplicationContextInitializer<ConfigurableApplicationContext> 的方式，则无法使用 Springs 的相关扫描器，因为当前 Spring 上下文还未初始化，目前该类不起作用
 *
 * @author wjm
 * @since 2023-10-07 15:23
 */
@Slf4j
public class ControllerAdvicePackagePathReplacer implements SmartInitializingSingleton {

    @Override
    public void afterSingletonsInstantiated() {
        Set<String> advicePackagePaths = Classes.parseAnnotationAntStylePackagePathsToPackagePaths(EnableWebMVCResponseBodyAdvice.class, "advicePackagePaths");
        if (Nil.isNotEmpty(advicePackagePaths)) {
            RestControllerAdvice restControllerAdvice = Annotations.getAnnotation(WebMVCResponseBodyAdvice.class, RestControllerAdvice.class);
            String[] beforeReplaceBasePackagePaths = restControllerAdvice.basePackages();
            Annotations.setAnnotationValue(restControllerAdvice, SpringWebMVCConstant.FIELD_NAME_BASE_PACKAGE_ON_ANNOTATION_REST_CONTROLLER_ADVICE, Collections.toArray(advicePackagePaths, String[]::new));
            log.debug("{}replace the annotation [@{}] field [{}] value on class [@{}], before replace value {}, after replace value {}.",
                    ModuleView.WEB_SYSTEM,
                    RestControllerAdvice.class.getSimpleName(),
                    SpringWebMVCConstant.FIELD_NAME_BASE_PACKAGE_ON_ANNOTATION_REST_CONTROLLER_ADVICE,
                    WebMVCResponseBodyAdvice.class.getSimpleName(),
                    beforeReplaceBasePackagePaths,
                    advicePackagePaths
            );
        }
    }

}

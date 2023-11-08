// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.spring.webmvc;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.constant.spring.SpringFrameworkConstant;
import cn.srd.library.java.tool.lang.annotation.Annotations;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.spring.contract.Classes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Set;

/**
 * @author wjm
 * @since 2023-10-07 15:23
 */
@Slf4j
public class ControllerAdvicePackagePathReplacer implements SmartInitializingSingleton {

    @Override
    public void afterSingletonsInstantiated() {
        Set<String> advicePackagePaths = Classes.parseAnnotationAntStylePackagePathToPackagePath(EnableWebMVCResponseBodyAdvice.class, "advicePackagePaths");
        if (Nil.isNotEmpty(advicePackagePaths)) {
            RestControllerAdvice restControllerAdvice = Annotations.getAnnotation(WebMVCResponseBodyAdvice.class, RestControllerAdvice.class);
            String[] beforeReplaceBasePackagePaths = restControllerAdvice.basePackages();
            Annotations.setAnnotationValue(restControllerAdvice, SpringFrameworkConstant.FIELD_NAME_BASE_PACKAGE_ON_ANNOTATION_REST_CONTROLLER_ADVICE, Collections.toArray(advicePackagePaths, String[]::new));
            log.debug("{}replace the annotation [@{}] field [{}] value on class [@{}], before replace value {}, after replace value {}.",
                    ModuleView.WEB_SYSTEM,
                    RestControllerAdvice.class.getSimpleName(),
                    SpringFrameworkConstant.FIELD_NAME_BASE_PACKAGE_ON_ANNOTATION_REST_CONTROLLER_ADVICE,
                    WebMVCResponseBodyAdvice.class.getSimpleName(),
                    beforeReplaceBasePackagePaths,
                    advicePackagePaths
            );
        }
    }

}

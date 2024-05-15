// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.enums.test;

import cn.srd.library.java.tool.enums.autowired.EnumAutowired;
import cn.srd.library.java.tool.enums.model.GenderStrategy;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.spring.contract.Classes;
import cn.srd.library.java.tool.spring.contract.Springs;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

/**
 * test for {@link EnumAutowired}
 *
 * @author wjm
 * @since 2023-10-05 19:23
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
class EnumAutowiredTest {

    @Test
    void testEnumAutowired() {
        String basePackage = Springs.getSpringBootApplicationPackagePath();
        Set<BeanDefinition> beanDefinitions = Classes.scanByTypeFilter(Collections.ofArrayList(new AnnotationTypeFilter(EnumAutowired.class), new AssignableTypeFilter(GenderStrategy.class)), basePackage);
    }

}
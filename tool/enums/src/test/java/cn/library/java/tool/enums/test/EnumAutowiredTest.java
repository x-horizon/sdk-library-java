package cn.library.java.tool.enums.test;

import cn.library.java.tool.enums.EnumAutowired;
import cn.library.java.tool.enums.model.GenderStrategy;
import cn.library.java.tool.lang.collection.Collections;
import cn.library.java.tool.spring.contract.support.Classes;
import cn.library.java.tool.spring.contract.support.Springs;
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
@ExtendWith(SpringExtension.class)
@SpringBootTest
class EnumAutowiredTest {

    @Test
    void testEnumAutowired() {
        String basePackage = Springs.getSpringBootApplicationPackagePath();
        Set<BeanDefinition> beanDefinitions = Classes.scanByTypeFilter(Collections.ofArrayList(new AnnotationTypeFilter(EnumAutowired.class), new AssignableTypeFilter(GenderStrategy.class)), basePackage);
    }

}
// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.autoconfigure;

import cn.srd.library.java.contract.constant.spring.SpringInitializeConstant;
import cn.srd.library.java.tool.lang.annotation.Annotations;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.spring.contract.Classes;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;

import java.util.Set;

/**
 * @author wjm
 * @since 2023-11-14 21:25
 */
@Slf4j
@MapperScan
@Order(SpringInitializeConstant.HIGH_INITIALIZE_PRIORITY)
public class MybatisMapperPackagePathAutoConfigurer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(@NonNull ConfigurableApplicationContext applicationContext) {
        EnableMybatisFlexCustomizer mybatisFlexCustomizer = Annotations.getAnnotation(EnableMybatisFlexCustomizer.class);
        if (Nil.isNotNull(mybatisFlexCustomizer)) {
            MapperScan mapperScan = Annotations.getAnnotation(MapperScan.class);
            Set<String> mybatisMapperPackagePaths = Classes.parseAntStylePackagePathsToPackagePaths(mybatisFlexCustomizer.propertyConfig().mapperPackagePaths());
            if (Nil.isNotEmpty(mybatisMapperPackagePaths)) {

            }
        }
        // if (Nil.isNotNull(mybatisFlexCustomizer) && mybatisFlexCustomizer.propertyConfig().mapperScanBasePackagePaths()) {
        //
        // }
        // MybatisFlexProperties mybatisFlexProperties = Springs.getBean(MybatisFlexProperties.class);
        // if (Comparators.equals(MYBATIS_FLEX_PROPERTIES_XML_MAPPER_CLASS_PATHS_DEFAULT_FIELD_VALUE, mybatisFlexProperties.getMapperLocations())) {
        //     Reflects.setFieldValue(mybatisFlexProperties,
        //             MYBATIS_FLEX_PROPERTIES_XML_MAPPER_CLASS_PATHS_FIELD_NAME,
        //             propertyConfig.xmlMapperClassPaths()
        //     );
        // }
        // if (Nil.isNull(mybatisFlexProperties.getTypeAliasesPackage())) {
        //     Reflects.setFieldValue(mybatisFlexProperties,
        //             MYBATIS_FLEX_PROPERTIES_XML_MAPPER_ENTITY_PACKAGE_ALIAS_CLASS_PATHS_FIELD_NAME,
        //             Strings.joinWithComma(Classes.parseAntStylePackagePathsToPackagePaths(propertyConfig.xmlMapperEntityPackageAliasPackagePaths()))
        //     );
        // }
    }

}

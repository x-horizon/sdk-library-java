// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.autoconfigure;

import cn.srd.library.java.tool.spring.contract.Annotations;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.lang.NonNull;

/**
 * @author wjm
 * @since 2023-11-14 21:25
 */
@Slf4j
public class MapperScanPackagePathReplacer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(@NonNull ConfigurableApplicationContext applicationContext) {
        EnableMybatisFlexCustomizer mybatisFlexCustomizer = Annotations.getAnnotation(EnableMybatisFlexCustomizer.class);
        MapperScan mapperScan = Annotations.getAnnotation(MapperScan.class);
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

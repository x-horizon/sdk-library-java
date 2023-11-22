// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.postgresql.autoconfigure;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.orm.mybatis.flex.postgresql.cache.ColumnJsonbMappingJavaTypeCache;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.text.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.stream.Collectors;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Orm Mybatis Flex PostgreSQL
 *
 * @author wjm
 * @since 2023-11-08 15:42
 */
@Slf4j
@AutoConfiguration
public class MybatisFlexPostgresqlAutoConfigurer {

    /**
     * register {@link ColumnJsonbMappingJavaTypeCache}
     *
     * @return {@link ColumnJsonbMappingJavaTypeCache columnJsonbMappingJavaTypeCache} bean
     */
    @Bean
    public ColumnJsonbMappingJavaTypeCache mybatisFlexColumnJsonbMappingRelationCache() {
        log.debug("{}mybatis flex jdbc jsonb type caching system is enabled, starting initializing...", ModuleView.ORM_MYBATIS_SYSTEM);

        ColumnJsonbMappingJavaTypeCache columnJsonbMappingJavaTypeCache = new ColumnJsonbMappingJavaTypeCache();

        if (Nil.isNotEmpty(columnJsonbMappingJavaTypeCache.getCache())) {
            log.debug(""" 
                            {}mybatis flex jdbc jsonb type caching system has loaded the following cache:
                            --------------------------------------------------------------------------------------------------------------------------------
                            {}
                            --------------------------------------------------------------------------------------------------------------------------------""",
                    ModuleView.ORM_MYBATIS_SYSTEM,
                    Strings.joinWithLF(columnJsonbMappingJavaTypeCache.getCache()
                            .entrySet()
                            .stream()
                            .map(entry -> Strings.format("[{}] = [{}]", entry.getKey(), Strings.joinWithComma(entry.getValue().stream().map(Class::getName).collect(Collectors.toSet()))))
                            .collect(Collectors.toSet())
                    )
            );
        }

        log.debug("{}mybatis flex jdbc jsonb type caching system initialized.", ModuleView.ORM_MYBATIS_SYSTEM);

        return columnJsonbMappingJavaTypeCache;
    }

}

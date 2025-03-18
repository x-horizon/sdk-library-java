package org.horizon.sdk.library.java.orm.mybatis.flex.postgresql.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.horizon.sdk.library.java.contract.constant.module.ModuleView;
import org.horizon.sdk.library.java.orm.mybatis.flex.postgresql.cache.ColumnJsonMappingJavaTypeCache;
import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.horizon.sdk.library.java.tool.lang.text.Strings;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.stream.Collectors;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java ORM Mybatis Flex PostgreSQL
 *
 * @author wjm
 * @since 2023-11-08 15:42
 */
@Slf4j
@AutoConfiguration
public class MybatisFlexPostgresqlAutoConfigurer {

    /**
     * register {@link ColumnJsonMappingJavaTypeCache}
     *
     * @return {@link ColumnJsonMappingJavaTypeCache columnJsonbMappingJavaTypeCache} bean
     */
    @Bean
    public ColumnJsonMappingJavaTypeCache mybatisFlexColumnJsonbMappingRelationCache() {
        log.debug("{}mybatis flex jdbc json type caching system is enabled, starting initializing...", ModuleView.ORM_MYBATIS_SYSTEM);

        ColumnJsonMappingJavaTypeCache columnJsonbMappingJavaTypeCache = new ColumnJsonMappingJavaTypeCache();

        if (Nil.isNotEmpty(columnJsonbMappingJavaTypeCache.getCache())) {
            log.debug(""" 
                            {}mybatis flex jdbc json type caching system has loaded the following cache:
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

        log.debug("{}mybatis flex jdbc json type caching system initialized.", ModuleView.ORM_MYBATIS_SYSTEM);

        return columnJsonbMappingJavaTypeCache;
    }

}
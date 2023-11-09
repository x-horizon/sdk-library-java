// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.library.java.orm.mybatis.flex.postgresql.autoconfigure;

import cn.library.java.orm.mybatis.flex.postgresql.cache.ColumnJsonbMappingJavaTypeCache;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Orm Mybatis Flex PostgreSQL
 *
 * @author wjm
 * @since 2023-11-08 15:42
 */
@AutoConfiguration
public class MybatisFlexPostgreSQLAutoConfigurer {

    /**
     * register {@link ColumnJsonbMappingJavaTypeCache}
     *
     * @return {@link ColumnJsonbMappingJavaTypeCache columnJsonbMappingJavaTypeCache} bean
     */
    @Bean
    public ColumnJsonbMappingJavaTypeCache mybatisFlexColumnJsonbMappingRelationCache() {
        return new ColumnJsonbMappingJavaTypeCache();
    }

}

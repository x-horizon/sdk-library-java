// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.autoconfigure;

import com.mybatisflex.spring.boot.MybatisFlexAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library ORM Mybatis Flex
 *
 * @author wjm
 * @since 2023-11-12 21:06
 */
@AutoConfigureBefore(MybatisFlexAutoConfiguration.class)
@ConditionalOnBean(MybatisFlexCustomizerSwitcher.class)
@DependsOn("mybatis-flex-com.mybatisflex.spring.boot.MybatisFlexProperties")
public class MybatisFlexAutoConfigurer {

    /**
     * enable mybatis flex customer if {@link EnableMybatisFlexCustomizer}
     *
     * @return the mybatis flex customer bean
     */
    @Bean
    public MybatisFlexCustomizer mybatisFlexCapableCustomizer() {
        return new MybatisFlexCustomizer();
    }

}

// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.autoconfigure;

import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.orm.mybatis.flex.base.adapter.MybatisFlexAdapter;
import cn.srd.library.java.orm.mybatis.flex.base.dao.GenericCurdDao;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.spring.boot.MybatisFlexAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java ORM Mybatis Flex
 *
 * @author wjm
 * @since 2023-11-12 21:06
 */
@AutoConfigureBefore(MybatisFlexAutoConfiguration.class)
@DependsOn("mybatis-flex-com.mybatisflex.spring.boot.MybatisFlexProperties")
public class MybatisFlexAutoConfigurer {

    /**
     * enable mybatis flex customer if {@link EnableMybatisFlexCustomizer}
     *
     * @return the mybatis flex customer bean
     */
    @Bean
    @ConditionalOnBean(MybatisFlexCustomizerSwitcher.class)
    public MybatisFlexCustomizer mybatisFlexCapableCustomizer() {
        return new MybatisFlexCustomizer();
    }

    @Bean
    public <P extends PO, D extends GenericCurdDao<P>, B extends BaseMapper<P>> MybatisFlexAdapter<P, D, B> genericDaoAdapter() {
        return new MybatisFlexAdapter<>();
    }

}
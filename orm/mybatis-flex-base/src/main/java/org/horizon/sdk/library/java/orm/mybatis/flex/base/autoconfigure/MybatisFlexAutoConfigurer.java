package org.horizon.sdk.library.java.orm.mybatis.flex.base.autoconfigure;

import com.mybatisflex.core.BaseMapper;
import org.horizon.sdk.library.java.contract.model.base.PO;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.cache.MybatisFlexSystemCache;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.repository.GenericRepository;
import org.springframework.boot.autoconfigure.AutoConfiguration;
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
@AutoConfiguration
@DependsOn("mybatis-flex-com.mybatisflex.spring.boot.MybatisFlexProperties")
public class MybatisFlexAutoConfigurer {

    /**
     * enable mybatis flex customer if {@link EnableMybatisFlexCustomizer}
     *
     * @return the mybatis flex customer bean
     */
    @Bean
    @ConditionalOnBean(MybatisFlexCustomizerRegistrar.class)
    public MybatisFlexCustomizer mybatisFlexCapableCustomizer() {
        return new MybatisFlexCustomizer();
    }

    @Bean
    public <P extends PO, R extends GenericRepository<P>, B extends BaseMapper<P>> MybatisFlexSystemCache<P, R, B> mybatisFlexSystemCache() {
        return new MybatisFlexSystemCache<>();
    }

}
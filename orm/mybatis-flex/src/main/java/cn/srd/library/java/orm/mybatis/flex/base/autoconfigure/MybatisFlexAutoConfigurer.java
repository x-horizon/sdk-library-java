package cn.srd.library.java.orm.mybatis.flex.base.autoconfigure;

import com.mybatisflex.spring.boot.MybatisFlexAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;

@AutoConfigureBefore(MybatisFlexAutoConfiguration.class)
public class MybatisFlexAutoConfigurer {

    @Bean
    @ConditionalOnBean(MybatisFlexCustomizerSwitcher.class)
    public MybatisFlexCustomizer mybatisFlexCapableCustomizer() {
        return new MybatisFlexCustomizer();
    }

}

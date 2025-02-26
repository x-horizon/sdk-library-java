package org.horizon.library.java.tool.enums.autoconfigure;

import org.horizon.library.java.tool.enums.strategy.EnumAutowiredCollector;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Tool Enum
 *
 * @author wjm
 * @since 2023-11-09 21:01
 */
@AutoConfiguration
public class EnumAutoConfigurer {

    @Bean
    @ConditionalOnBean(EnumAutowiredRegistrar.class)
    public <E extends Enum<E>> EnumAutowiredCollector<E> enumAutowiredCollector() {
        return new EnumAutowiredCollector<>();
    }

}
// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.enums.autoconfigure;

import cn.srd.library.java.tool.enums.autowired.EnumAutowiredHandler;
import cn.srd.library.java.tool.enums.autowired.EnumAutowiredSwitcher;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Tool Enum
 *
 * @author wjm
 * @since 2023-11-09 21:01
 */
@AutoConfiguration
public class EnumAutoConfigurer {

    @Bean
    @ConditionalOnBean(EnumAutowiredSwitcher.class)
    public <E extends Enum<E>> EnumAutowiredHandler<E> enumAutowiredHandler() {
        return new EnumAutowiredHandler<>();
    }

}

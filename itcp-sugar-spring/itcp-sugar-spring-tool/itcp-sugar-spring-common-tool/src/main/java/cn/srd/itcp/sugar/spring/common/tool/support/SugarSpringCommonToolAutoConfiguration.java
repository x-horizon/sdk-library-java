package cn.srd.itcp.sugar.spring.common.tool.support;

import cn.srd.itcp.sugar.spring.common.tool.core.SpringsUtil;
import cn.srd.itcp.sugar.spring.common.tool.core.enums.autowired.EnableEnumAutowired;
import cn.srd.itcp.sugar.spring.common.tool.core.enums.autowired.EnumAutowiredSupport;
import cn.srd.itcp.sugar.tool.core.Objects;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Sugar Tools
 *
 * @author wjm
 * @since 2022-07-14
 */
public class SugarSpringCommonToolAutoConfiguration {

    /**
     * 装配 {@link EnumAutowiredSupport}
     *
     * @return 装配对象
     */
    @Bean
    public EnumAutowiredSupport enumAutowiredSupport() {
        if (Objects.isNotEmpty(SpringsUtil.scanPackageByAnnotation(EnableEnumAutowired.class))) {
            return new EnumAutowiredSupport();
        }
        return null;
    }

}

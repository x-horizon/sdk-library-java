package cn.srd.itcp.sugar.tools.support;

import cn.srd.itcp.sugar.tools.core.Objects;
import cn.srd.itcp.sugar.tools.core.SpringsUtil;
import cn.srd.itcp.sugar.tools.core.enums.autowired.EnableEnumAutowired;
import cn.srd.itcp.sugar.tools.core.enums.autowired.EnumAutowiredSupport;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Sugar Tools
 *
 * @author wjm
 * @date 2022-07-14
 */
public class SugarToolsAutoConfiguration {

    @Bean
    public EnumAutowiredSupport enumAutowiredSupport() {
        if (Objects.isNotEmpty(SpringsUtil.scanPackageByAnnotation(EnableEnumAutowired.class))) {
            return new EnumAutowiredSupport();
        }
        return null;
    }

}

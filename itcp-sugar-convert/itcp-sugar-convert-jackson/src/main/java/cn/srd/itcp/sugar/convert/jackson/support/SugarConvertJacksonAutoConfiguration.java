package cn.srd.itcp.sugar.convert.jackson.support;

import cn.srd.itcp.sugar.convert.jackson.core.EnableJacksonCapableToEnumDeserialize;
import cn.srd.itcp.sugar.tools.core.Objects;
import cn.srd.itcp.sugar.tools.core.SpringsUtil;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Sugar Tools
 *
 * @author wjm
 * @date 2022-07-14
 */
public class SugarConvertJacksonAutoConfiguration {

    @Bean
    public JacksonCapableToEnumDeserializer jacksonCapableToEnumDeserializer() {
        if (Objects.isNotEmpty(SpringsUtil.scanPackageByAnnotation(EnableJacksonCapableToEnumDeserialize.class))) {
            return SpringsUtil.registerCapableBean(JacksonCapableToEnumDeserializer.class);
        }
        return null;
    }

}

package org.horizon.sdk.library.java.tool.convert.mapstruct.plus.autoconfigure;

import io.github.linpeilie.Converter;
import org.horizon.sdk.library.java.tool.convert.mapstruct.plus.MapstructConverts;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;

/**
 * @author wjm
 * @since 2024-05-22 11:39
 */
@AutoConfigureAfter(Converter.class)
public class MapstructConverterAutoConfigurer {

    @Bean
    public MapstructConverts mapstructConverts() {
        return new MapstructConverts();
    }

}
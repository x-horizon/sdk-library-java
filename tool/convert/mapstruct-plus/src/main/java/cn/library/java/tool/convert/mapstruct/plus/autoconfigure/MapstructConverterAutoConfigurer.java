package cn.library.java.tool.convert.mapstruct.plus.autoconfigure;

import cn.library.java.tool.convert.mapstruct.plus.MapstructConverts;
import io.github.linpeilie.Converter;
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
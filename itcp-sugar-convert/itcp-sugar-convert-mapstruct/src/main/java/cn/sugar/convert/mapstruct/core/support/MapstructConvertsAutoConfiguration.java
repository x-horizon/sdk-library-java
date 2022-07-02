package cn.sugar.convert.mapstruct.core.support;

import cn.sugar.convert.mapstruct.core.MapstructConverts;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Mapstruct 转换器自动装配
 *
 * @author wjm
 * @date 2021/5/1 14:13
 */
@Configuration
@Import(MapstructConverts.class)
public class MapstructConvertsAutoConfiguration {

}

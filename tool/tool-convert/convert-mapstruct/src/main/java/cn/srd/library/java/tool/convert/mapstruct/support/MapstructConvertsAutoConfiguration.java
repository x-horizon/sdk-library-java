package cn.srd.library.java.tool.convert.mapstruct.support;

import cn.srd.library.java.tool.convert.mapstruct.core.MapstructConverts;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * Mapstruct 转换器自动装配
 *
 * @author wjm
 * @since 2021/5/1 14:13
 */
@AutoConfiguration
@Import(MapstructConverts.class)
public class MapstructConvertsAutoConfiguration {

}

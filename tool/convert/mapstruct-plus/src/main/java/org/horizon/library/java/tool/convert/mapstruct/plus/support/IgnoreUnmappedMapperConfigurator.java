package org.horizon.library.java.tool.convert.mapstruct.plus.support;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

/**
 * Mapstruct 忽略未映射的字段配置器
 *
 * @author wjm
 * @since 2023-01-26 16:44
 */
@MapperConfig(unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IgnoreUnmappedMapperConfigurator {

}
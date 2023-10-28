// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.convert.mapstruct.utils;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

/**
 * Mapstruct 忽略未映射的字段配置器
 *
 * @author wjm
 * @since 2023-01-26 16:44:12
 */
@MapperConfig(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IgnoreUnmappedMapperConfigurator {

}
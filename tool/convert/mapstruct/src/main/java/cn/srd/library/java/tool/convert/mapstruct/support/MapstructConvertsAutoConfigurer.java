// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.convert.mapstruct.support;

import cn.srd.library.java.tool.convert.mapstruct.MapstructConverts;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * Mapstruct 转换器自动装配
 *
 * @author wjm
 * @since 2021-05-01 14:13
 */
@AutoConfiguration
@Import(MapstructConverts.class)
@Deprecated
public class MapstructConvertsAutoConfigurer {

}
// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.convert.mapstruct.autoconfigure;

import cn.srd.library.java.tool.convert.mapstruct.MapstructConverts;
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
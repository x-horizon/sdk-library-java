// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.convert.jackson.deserializer;

import cn.srd.library.java.contract.constant.time.TimePatternConstant;
import cn.srd.library.java.tool.lang.time.Times;
import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDateTime;

/**
 * Jackson 反序列化处理器：String =&gt; LocalDateTime（遵循 RFC3339 标准，仅支持如：2006-01-02T15:04:05Z07:00）
 *
 * @author wjm
 * @since 2023-03-28 10:00
 */
public class JacksonStringToLocalDateTimeRFC3339Deserializer extends StdConverter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String from) {
        return Times.toLocalDateTime(from, TimePatternConstant.DATETIME_RFC3339_EAST_EIGHTH_TIMEZONE);
    }

}
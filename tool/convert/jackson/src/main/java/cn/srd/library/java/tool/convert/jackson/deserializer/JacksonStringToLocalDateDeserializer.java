// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.convert.jackson.deserializer;

import cn.srd.library.java.tool.lang.time.Times;
import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Jackson 反序列化处理器：String =&gt; LocalDate（仅支持如：2023-03-27）
 *
 * @author wjm
 * @since 2022-07-20 11:37:28
 */
public class JacksonStringToLocalDateDeserializer extends StdConverter<String, LocalDate> {

    @Override
    public LocalDate convert(String from) {
        return Times.toLocalDate(from, DateTimeFormatter.ISO_DATE);
    }

}

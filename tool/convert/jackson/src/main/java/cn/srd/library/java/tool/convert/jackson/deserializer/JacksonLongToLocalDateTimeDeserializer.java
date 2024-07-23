// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.convert.jackson.deserializer;

import cn.srd.library.java.tool.lang.time.Times;
import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * the jackson deserializer to convert {@link Long} to {@link LocalDate}
 *
 * @author wjm
 * @since 2022-10-28 11:18
 */
public class JacksonLongToLocalDateTimeDeserializer extends StdConverter<Long, LocalDateTime> {

    @Override
    public LocalDateTime convert(Long from) {
        return Times.toLocalDateTime(from);
    }

}
// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.convert.jackson.deserializer;

import cn.srd.library.java.tool.lang.time.Times;
import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDateTime;

/**
 * Jackson 反序列化处理器：Long =&gt; LocalDateTime
 *
 * @author wjm
 * @since 2022-10-28 11:18:19
 */
public class JacksonLongToLocalDateTimeDeserializer extends StdConverter<Long, LocalDateTime> {

    @Override
    public LocalDateTime convert(Long from) {
        return Times.toLocalDateTime(from);
    }

}

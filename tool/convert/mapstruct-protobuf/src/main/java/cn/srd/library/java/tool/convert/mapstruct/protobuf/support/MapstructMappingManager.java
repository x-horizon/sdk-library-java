// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.convert.mapstruct.protobuf.support;

import cn.srd.library.java.tool.convert.mapstruct.plus.support.MapstructMappingQualify;
import cn.srd.library.java.tool.convert.protobuf.ProtobufConverts;
import com.google.protobuf.Timestamp;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author wjm
 * @since 2024-07-09 18:55
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@MapstructMappingQualify
public class MapstructMappingManager extends cn.srd.library.java.tool.convert.mapstruct.plus.support.MapstructMappingManager {

    @MapstructLocalDateTimeToProtobufTimestamp
    public static Timestamp localDateTimeToProtobufTimestamp(LocalDateTime value) {
        return ProtobufConverts.getInstance().toTimestamp(value);
    }

}
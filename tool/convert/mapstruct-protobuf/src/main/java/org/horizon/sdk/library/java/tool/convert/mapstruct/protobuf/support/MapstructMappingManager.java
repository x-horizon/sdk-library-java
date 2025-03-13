package org.horizon.sdk.library.java.tool.convert.mapstruct.protobuf.support;

import com.google.protobuf.Timestamp;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.horizon.sdk.library.java.tool.convert.mapstruct.plus.support.MapstructMappingQualify;
import org.horizon.sdk.library.java.tool.convert.protobuf.ProtobufConverts;

import java.time.LocalDateTime;

/**
 * @author wjm
 * @since 2024-07-09 18:55
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@MapstructMappingQualify
public class MapstructMappingManager extends org.horizon.sdk.library.java.tool.convert.mapstruct.plus.support.MapstructMappingManager {

    @MapstructLocalDateTimeToProtobufTimestamp
    public static Timestamp localDateTimeToProtobufTimestamp(LocalDateTime value) {
        return ProtobufConverts.getInstance().toTimestamp(value);
    }

}
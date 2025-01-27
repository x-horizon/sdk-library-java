package cn.library.java.tool.convert.mapstruct.protobuf.support;

import cn.library.java.tool.convert.mapstruct.plus.support.MapstructMappingQualify;
import cn.library.java.tool.convert.protobuf.ProtobufConverts;
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
public class MapstructMappingManager extends cn.library.java.tool.convert.mapstruct.plus.support.MapstructMappingManager {

    @MapstructLocalDateTimeToProtobufTimestamp
    public static Timestamp localDateTimeToProtobufTimestamp(LocalDateTime value) {
        return ProtobufConverts.getInstance().toTimestamp(value);
    }

}
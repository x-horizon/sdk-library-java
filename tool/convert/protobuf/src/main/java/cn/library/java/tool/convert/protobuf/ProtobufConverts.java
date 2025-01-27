package cn.library.java.tool.convert.protobuf;

import cn.library.java.contract.constant.suppress.SuppressWarningConstant;
import cn.library.java.contract.constant.time.TimeZoneType;
import com.google.protobuf.Message;
import com.google.protobuf.TextFormat;
import com.google.protobuf.Timestamp;
import com.google.protobuf.util.JsonFormat;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author wjm
 * @since 2024-06-12 16:08
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProtobufConverts {

    private static final ProtobufConverts INSTANCE = new ProtobufConverts();

    public static ProtobufConverts getInstance() {
        return INSTANCE;
    }

    @SneakyThrows
    public String toString(Message source) {
        return TextFormat.printer().printToString(source);
    }

    @SneakyThrows
    public String toJsonString(Message source) {
        return JsonFormat.printer().print(source);
    }

    public Timestamp toTimestamp(LocalDateTime time) {
        return toTimestamp(time, TimeZoneType.SHANG_HAI);
    }

    public Timestamp toTimestamp(LocalDateTime time, TimeZoneType timeZoneType) {
        return Timestamp.newBuilder()
                .setSeconds(time.atZone(ZoneId.of(timeZoneType.getValue())).toEpochSecond())
                .setNanos(time.getNano())
                .build();
    }

    public LocalDateTime toLocalDateTime(Timestamp timestamp) {
        return toLocalDateTime(timestamp, TimeZoneType.SHANG_HAI);
    }

    public LocalDateTime toLocalDateTime(Timestamp timestamp, TimeZoneType timeZoneType) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp.getSeconds(), timestamp.getNanos()), ZoneId.of(timeZoneType.getValue()));
    }

    @SneakyThrows
    public <B extends Message.Builder> B toBuilder(String source, B targetBuilder) {
        JsonFormat.parser().ignoringUnknownFields().merge(source, targetBuilder);
        return targetBuilder;
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public <M extends Message, B extends Message.Builder> M toBean(String source, B targetBuilder) {
        return (M) toBuilder(source, targetBuilder).build();
    }

}
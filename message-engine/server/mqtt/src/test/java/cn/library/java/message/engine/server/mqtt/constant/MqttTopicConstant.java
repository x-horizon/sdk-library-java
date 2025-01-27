package cn.library.java.message.engine.server.mqtt.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author wjm
 * @since 2025-01-07 22:42
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MqttTopicConstant {

    public static final String V1 = "v1";

    public static final String DEVICE_TELEMETRY = V1 + "/device/telemetry";

}
package org.horizon.sdk.library.java.message.engine.server.mqtt.constant;

import io.netty.handler.codec.mqtt.MqttQoS;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author wjm
 * @since 2025-01-05 22:33
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MqttServerConstant {

    public static final MqttQoS MAX_SUPPORTED_QUALITY_OF_SERVICE = MqttQoS.AT_LEAST_ONCE;

}
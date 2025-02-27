package org.horizon.sdk.library.java.message.engine.server.mqtt.model.enums;

import org.horizon.sdk.library.java.message.engine.server.mqtt.strategy.MqttVersionStrategy;
import org.horizon.sdk.library.java.tool.enums.EnumAutowired;
import org.horizon.sdk.library.java.tool.enums.strategy.EnumAutowiredFieldMatchByContainIgnoreCaseAndMinLengthRule;
import io.netty.handler.codec.mqtt.MqttConnectMessage;
import io.netty.handler.codec.mqtt.MqttVersion;
import lombok.Getter;

/**
 * @author wjm
 * @since 2025-01-05 22:37
 */
@Getter
@EnumAutowired(rootClasses = MqttVersionStrategy.class, matchRule = EnumAutowiredFieldMatchByContainIgnoreCaseAndMinLengthRule.class)
public enum MqttVersionType {

    V31(MqttVersion.MQTT_3_1),
    V311(MqttVersion.MQTT_3_1_1),
    V5(MqttVersion.MQTT_5),

    ;

    private final MqttVersion nettyMqttVersion;

    private MqttVersionStrategy strategy;

    MqttVersionType(MqttVersion nettyMqttVersion) {
        this.nettyMqttVersion = nettyMqttVersion;
    }

    public static MqttVersionType from(MqttConnectMessage mqttConnectMessage) {
        return from(mqttConnectMessage.variableHeader().version());
    }

    public static MqttVersionType from(int versionCode) {
        return switch (versionCode) {
            case 3 -> V31;
            case 5 -> V5;
            default -> V311;
        };
    }

}
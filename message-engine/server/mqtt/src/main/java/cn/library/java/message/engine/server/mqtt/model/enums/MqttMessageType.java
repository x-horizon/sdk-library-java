package cn.library.java.message.engine.server.mqtt.model.enums;

import cn.library.java.message.engine.server.mqtt.strategy.MqttMessageStrategy;
import cn.library.java.tool.enums.EnumAutowired;
import cn.library.java.tool.enums.strategy.EnumAutowiredFieldMatchByContainIgnoreCaseAndMinLengthRule;
import cn.library.java.tool.lang.convert.Converts;
import io.netty.handler.codec.mqtt.MqttMessage;
import lombok.Getter;

/**
 * @author wjm
 * @since 2025-01-06 17:27
 */
@Getter
@EnumAutowired(rootClasses = MqttMessageStrategy.class, matchRule = EnumAutowiredFieldMatchByContainIgnoreCaseAndMinLengthRule.class)
public enum MqttMessageType {

    CONNECT(io.netty.handler.codec.mqtt.MqttMessageType.CONNECT),
    CONNECT_ACK(io.netty.handler.codec.mqtt.MqttMessageType.CONNACK),
    DISCONNECT(io.netty.handler.codec.mqtt.MqttMessageType.DISCONNECT),

    PUBLISH(io.netty.handler.codec.mqtt.MqttMessageType.PUBLISH),
    PUBLISH_ACK_ON_QUALITY_OF_SERVICE_1(io.netty.handler.codec.mqtt.MqttMessageType.PUBACK),
    PUBLISH_RECEIVE_ACK_ON_QUALITY_OF_SERVICE_2(io.netty.handler.codec.mqtt.MqttMessageType.PUBREC),
    PUBLISH_RELEASE_ACK_ON_QUALITY_OF_SERVICE_2(io.netty.handler.codec.mqtt.MqttMessageType.PUBREL),
    PUBLISH_COMPLETE_ACK_ON_QUALITY_OF_SERVICE_2(io.netty.handler.codec.mqtt.MqttMessageType.PUBCOMP),

    SUBSCRIBE(io.netty.handler.codec.mqtt.MqttMessageType.SUBSCRIBE),
    SUBSCRIBE_ACK(io.netty.handler.codec.mqtt.MqttMessageType.SUBACK),
    UNSUBSCRIBE(io.netty.handler.codec.mqtt.MqttMessageType.UNSUBSCRIBE),
    UNSUBSCRIBE_ACK(io.netty.handler.codec.mqtt.MqttMessageType.UNSUBACK),

    PING_REQUEST(io.netty.handler.codec.mqtt.MqttMessageType.PINGREQ),
    PING_RESPONSE(io.netty.handler.codec.mqtt.MqttMessageType.PINGRESP),

    AUTH(io.netty.handler.codec.mqtt.MqttMessageType.AUTH),

    ;

    private final io.netty.handler.codec.mqtt.MqttMessageType nettyMqttMessageType;

    private MqttMessageStrategy<? super MqttMessage> strategy;

    MqttMessageType(io.netty.handler.codec.mqtt.MqttMessageType nettyMqttMessageType) {
        this.nettyMqttMessageType = nettyMqttMessageType;
    }

    public static MqttMessageType from(io.netty.handler.codec.mqtt.MqttMessageType nettyMqttMessageType) {
        return Converts.toEnumByValue(nettyMqttMessageType, MqttMessageType.class);
    }

}
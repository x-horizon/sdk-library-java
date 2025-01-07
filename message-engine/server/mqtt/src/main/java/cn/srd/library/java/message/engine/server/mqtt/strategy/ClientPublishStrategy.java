package cn.srd.library.java.message.engine.server.mqtt.strategy;

import io.netty.handler.codec.mqtt.MqttPublishMessage;

/**
 * @author wjm
 * @since 2025-01-07 19:57
 */
public interface ClientPublishStrategy {

    Void process(MqttPublishMessage mqttPublishMessage);

}
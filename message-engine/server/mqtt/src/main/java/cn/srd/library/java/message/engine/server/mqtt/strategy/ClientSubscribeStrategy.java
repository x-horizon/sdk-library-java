package cn.srd.library.java.message.engine.server.mqtt.strategy;

import io.netty.handler.codec.mqtt.MqttTopicSubscription;

/**
 * @author wjm
 * @since 2025-01-07 20:33
 */
public interface ClientSubscribeStrategy {

    Void process(MqttTopicSubscription mqttTopicSubscription);

}
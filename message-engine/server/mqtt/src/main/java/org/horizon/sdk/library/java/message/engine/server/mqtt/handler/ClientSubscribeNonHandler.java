package org.horizon.sdk.library.java.message.engine.server.mqtt.handler;

import io.netty.handler.codec.mqtt.MqttTopicSubscription;
import lombok.extern.slf4j.Slf4j;
import org.horizon.sdk.library.java.contract.constant.module.ModuleView;

/**
 * @author wjm
 * @since 2025-01-07 20:33
 */
@Slf4j
public class ClientSubscribeNonHandler implements ClientSubscribeHandler {

    @Override
    public Void process(MqttTopicSubscription mqttTopicSubscription) {
        log.trace("{}non handle client subscribe message and success directly.", ModuleView.MESSAGE_ENGINE_MQTT_SERVER_SYSTEM);
        return null;
    }

}
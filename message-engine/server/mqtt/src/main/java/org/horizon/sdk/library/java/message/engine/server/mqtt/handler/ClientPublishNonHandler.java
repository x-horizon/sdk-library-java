package org.horizon.sdk.library.java.message.engine.server.mqtt.handler;

import io.netty.handler.codec.mqtt.MqttPublishMessage;
import lombok.extern.slf4j.Slf4j;
import org.horizon.sdk.library.java.contract.constant.module.ModuleView;

/**
 * @author wjm
 * @since 2025-01-07 19:58
 */
@Slf4j
public class ClientPublishNonHandler implements ClientPublishHandler {

    @Override
    public Void process(MqttPublishMessage mqttPublishMessage) {
        log.trace("{}non handle client publish message and success directly.", ModuleView.MESSAGE_ENGINE_MQTT_SERVER_SYSTEM);
        return null;
    }

}
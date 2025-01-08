package cn.srd.library.java.message.engine.server.mqtt.manager.handler;

import cn.srd.library.java.message.engine.server.mqtt.handler.ClientPublishHandler;
import io.netty.handler.codec.mqtt.MqttPublishMessage;

/**
 * @author wjm
 * @since 2025-01-07 23:22
 */
public class DevicePublishHandler implements ClientPublishHandler {

    @Override
    public Void process(MqttPublishMessage mqttPublishMessage) {
        return null;
    }

}
package cn.srd.library.java.message.engine.server.mqtt.strategy;

import cn.srd.library.java.contract.constant.module.ModuleView;
import io.netty.handler.codec.mqtt.MqttTopicSubscription;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wjm
 * @since 2025-01-07 20:33
 */
@Slf4j
public class ClientSubscribeNonHandleStrategy implements ClientSubscribeStrategy {

    @Override
    public Void process(MqttTopicSubscription mqttTopicSubscription) {
        log.trace("{}non handle client subscribe message and success directly.", ModuleView.MESSAGE_ENGINE_MQTT_SERVER_SYSTEM);
        return null;
    }

}
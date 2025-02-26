package org.horizon.library.java.message.engine.server.mqtt.handler;

import org.horizon.library.java.contract.constant.module.ModuleView;
import io.netty.handler.codec.mqtt.MqttReasonCodes;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wjm
 * @since 2025-01-07 21:39
 */
@Slf4j
public class ClientUnsubscribeNonHandler implements ClientUnsubscribeHandler {

    @Override
    public short process(String unsubscribeTopic) {
        log.trace("{}non handle client unsubscribe message and success directly.", ModuleView.MESSAGE_ENGINE_MQTT_SERVER_SYSTEM);
        return MqttReasonCodes.UnsubAck.SUCCESS.byteValue();
    }

}
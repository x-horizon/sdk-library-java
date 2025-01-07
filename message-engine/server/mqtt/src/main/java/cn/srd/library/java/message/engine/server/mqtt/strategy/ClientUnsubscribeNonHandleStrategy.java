package cn.srd.library.java.message.engine.server.mqtt.strategy;

import cn.srd.library.java.contract.constant.module.ModuleView;
import io.netty.handler.codec.mqtt.MqttReasonCodes;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wjm
 * @since 2025-01-07 21:39
 */
@Slf4j
public class ClientUnsubscribeNonHandleStrategy implements ClientUnsubscribeStrategy {

    @Override
    public short process(String unsubscribeTopic) {
        log.trace("{}non handle client unsubscribe message and success directly.", ModuleView.MESSAGE_ENGINE_MQTT_SERVER_SYSTEM);
        return MqttReasonCodes.UnsubAck.SUCCESS.byteValue();
    }

}
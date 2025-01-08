package cn.srd.library.java.message.engine.server.mqtt.strategy;

import cn.srd.library.java.message.engine.server.mqtt.callback.MessageCallback;
import cn.srd.library.java.message.engine.server.mqtt.context.MqttClientSessionContext;
import cn.srd.library.java.message.engine.server.mqtt.context.MqttServerContext;
import cn.srd.library.java.message.engine.server.mqtt.handler.ClientPublishAckOnQualityOfService1Handler;
import cn.srd.library.java.message.engine.server.mqtt.model.dto.RpcRequestDTO;
import cn.srd.library.java.tool.lang.object.Nil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttPubAckMessage;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wjm
 * @since 2025-01-06 17:37
 */
public class MqttMessagePublishAckOnQualityOfService1Strategy implements MqttMessageStrategy<MqttPubAckMessage> {

    @Autowired private ClientPublishAckOnQualityOfService1Handler clientPublishAckOnQualityOfService1Handler;

    @Override
    public void process(ChannelHandlerContext channelHandlerContext, MqttServerContext mqttServerContext, MqttClientSessionContext mqttClientSessionContext, MqttPubAckMessage mqttPubAckMessage) {
        int messageId = mqttPubAckMessage.variableHeader().messageId();
        RpcRequestDTO rpcRequestDTO = mqttClientSessionContext.getRpcAwaitingAckMap().remove(messageId);
        if (Nil.isNotNull(rpcRequestDTO)) {
            MessageCallback.EMPTY.process(mqttServerContext.getMessageCallbackExecutor(), () -> clientPublishAckOnQualityOfService1Handler.process(rpcRequestDTO));
        }
    }

}
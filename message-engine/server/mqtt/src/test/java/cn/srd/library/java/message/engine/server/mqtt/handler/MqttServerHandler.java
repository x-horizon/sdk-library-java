package cn.srd.library.java.message.engine.server.mqtt.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.mqtt.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wjm
 * @since 2024-12-29 17:44
 */
@Slf4j
public class MqttServerHandler extends SimpleChannelInboundHandler<MqttMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MqttMessage mqttMessage) {
        // 根据 MQTT 消息类型处理
        switch (mqttMessage.fixedHeader().messageType()) {
            case CONNECT:
                handleConnect(channelHandlerContext, (MqttConnectMessage) mqttMessage);
                break;
            case PUBLISH:
                handlePublish((MqttPublishMessage) mqttMessage);
                break;
            case SUBSCRIBE:
                handleSubscribe(channelHandlerContext, (MqttSubscribeMessage) mqttMessage);
                break;
            case PINGREQ:
                handlePingReq(channelHandlerContext);
                break;
            default:
                break;
        }
    }

    // 处理连接请求
    private void handleConnect(ChannelHandlerContext channelHandlerContext, MqttConnectMessage connectMessage) {
        log.debug("收到消息，connect 类型");
        // 1. 验证客户端标识
        String clientId = connectMessage.payload().clientIdentifier();
        // 2. 认证处理
        boolean isAuthenticated = authenticate(connectMessage);
        // 3. 构建连接响应
        MqttConnAckMessage connAck = createConnAckMessage(isAuthenticated);
        // 4. 发送响应
        channelHandlerContext.writeAndFlush(connAck);
    }

    // 处理发布消息
    private void handlePublish(MqttPublishMessage publishMessage) {
        log.debug("收到消息，publish 类型");
        // 1. 获取主题
        String topic = publishMessage.variableHeader().topicName();
        // 2. 获取消息内容
        ByteBuf payload = publishMessage.payload();
        byte[] bytes = new byte[payload.readableBytes()];
        payload.readBytes(bytes);
        // 3. 业务处理
        System.out.println("Received publish: " + topic + ", message: " + new String(bytes));
    }

    // 处理订阅请求
    private void handleSubscribe(ChannelHandlerContext channelHandlerContext, MqttSubscribeMessage subscribeMessage) {
        log.debug("收到消息，subscribe 类型");
        // 1. 获取订阅主题
        List<MqttTopicSubscription> topicSubscriptions = subscribeMessage.payload().topicSubscriptions();
        // 2. 构建订阅响应
        MqttSubAckMessage subAck = createSubAckMessage(subscribeMessage.variableHeader().messageId(), topicSubscriptions);
        // 3. 发送响应
        channelHandlerContext.writeAndFlush(subAck);
    }

    // 处理心跳
    private void handlePingReq(ChannelHandlerContext channelHandlerContext) {
        log.debug("收到消息，ping 类型");
        MqttFixedHeader mqttFixedHeader = new MqttFixedHeader(MqttMessageType.PINGRESP, false, MqttQoS.AT_MOST_ONCE, false, 0);
        MqttMessage pingResp = MqttMessageFactory.newMessage(mqttFixedHeader, null, null);
        channelHandlerContext.writeAndFlush(pingResp);
    }

    // 认证方法
    private boolean authenticate(MqttConnectMessage connectMessage) {
        // 实现你的认证逻辑
        return true;
    }

    // 创建连接响应
    private MqttConnAckMessage createConnAckMessage(boolean isAuthenticated) {
        log.debug("创建连接 ack");
        MqttConnAckVariableHeader variableHeader = new MqttConnAckVariableHeader(
                MqttConnectReturnCode.CONNECTION_ACCEPTED,
                !isAuthenticated
        );
        MqttFixedHeader mqttFixedHeader = new MqttFixedHeader(MqttMessageType.CONNACK, false, MqttQoS.AT_MOST_ONCE, false, 0);
        return new MqttConnAckMessage(mqttFixedHeader, variableHeader);
    }

    // 创建订阅响应
    private MqttSubAckMessage createSubAckMessage(int messageId, List<MqttTopicSubscription> topicSubscriptions) {
        log.debug("创建订阅 ack");
        List<Integer> grantedQoSLevels = topicSubscriptions.stream()
                .map(sub -> sub.qualityOfService().value())
                .collect(Collectors.toList());

        MqttFixedHeader mqttFixedHeader = new MqttFixedHeader(MqttMessageType.SUBACK, false, MqttQoS.AT_MOST_ONCE, false, 0);
        MqttMessageIdVariableHeader mqttMessageIdVariableHeader = MqttMessageIdVariableHeader.from(messageId);
        return new MqttSubAckMessage(mqttFixedHeader, mqttMessageIdVariableHeader, new MqttSubAckPayload(grantedQoSLevels));
    }

}
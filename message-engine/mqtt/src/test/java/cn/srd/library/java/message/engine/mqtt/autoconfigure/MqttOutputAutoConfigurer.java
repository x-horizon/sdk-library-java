// // Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// // Use of this source code is governed by SRD.
// // license that can be found in the LICENSE file.
//
// package cn.srd.library.java.message.engine.mqtt.autoconfigure;
//
// import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
// import org.springframework.boot.autoconfigure.AutoConfiguration;
// import org.springframework.context.annotation.Bean;
// import org.springframework.integration.annotation.IntegrationComponentScan;
// import org.springframework.integration.annotation.MessagingGateway;
// import org.springframework.integration.annotation.ServiceActivator;
// import org.springframework.integration.channel.DirectChannel;
// import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
// import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
// import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
// import org.springframework.messaging.MessageChannel;
// import org.springframework.messaging.MessageHandler;
//
// /**
//  * @author wjm
//  * @since 2024-05-21 21:55
//  */
// @IntegrationComponentScan
// @AutoConfiguration
// public class MqttOutputAutoConfigurer {
//
//     @Bean
//     public MqttPahoClientFactory mqttClientFactory() {
//         DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
//         MqttConnectOptions options = new MqttConnectOptions();
//         options.setServerURIs(new String[]{"tcp://host1:1883", "tcp://host2:1883"});
//         options.setUserName("username");
//         options.setPassword("password".toCharArray());
//         factory.setConnectionOptions(options);
//         return factory;
//     }
//
//     @Bean
//     @ServiceActivator(inputChannel = "mqttOutboundChannel")
//     public MessageHandler mqttOutbound() {
//         MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler("testClient", mqttClientFactory());
//         messageHandler.setAsync(true);
//         messageHandler.setDefaultTopic("testTopic");
//         return messageHandler;
//     }
//
//     @Bean
//     public MessageChannel mqttOutboundChannel() {
//         return new DirectChannel();
//     }
//
//     @MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
//     public interface MyGateway {
//
//         void sendToMqtt(String data);
//
//     }
//
// }
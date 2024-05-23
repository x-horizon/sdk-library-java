// // Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// // Use of this source code is governed by SRD.
// // license that can be found in the LICENSE file.
//
// package cn.srd.library.java.message.engine.mqtt.autoconfigure;
//
// import org.springframework.boot.autoconfigure.AutoConfiguration;
// import org.springframework.context.annotation.Bean;
// import org.springframework.integration.annotation.ServiceActivator;
// import org.springframework.integration.channel.DirectChannel;
// import org.springframework.integration.core.MessageProducer;
// import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
// import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
// import org.springframework.messaging.Message;
// import org.springframework.messaging.MessageChannel;
// import org.springframework.messaging.MessageHandler;
// import org.springframework.messaging.MessagingException;
//
// /**
//  * @author wjm
//  * @since 2024-05-21 21:55
//  */
// @AutoConfiguration
// public class MqttInputAutoConfigurer {
//
//     @Bean
//     public MessageChannel mqttInputChannel() {
//         return new DirectChannel();
//     }
//
//     @Bean
//     public MessageProducer inbound() {
//         MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(
//                 "tcp://localhost:1883",
//                 "testClient",
//                 "topic1",
//                 "topic2"
//         );
//         adapter.setCompletionTimeout(5000);
//         adapter.setConverter(new DefaultPahoMessageConverter());
//         adapter.setQos(1);
//         adapter.setOutputChannel(mqttInputChannel());
//         return adapter;
//     }
//
//     @Bean
//     @ServiceActivator(inputChannel = "mqttInputChannel")
//     public MessageHandler handler() {
//         return new MessageHandler() {
//
//             @Override
//             public void handleMessage(Message<?> message) throws MessagingException {
//                 System.out.println(message.getPayload());
//             }
//
//         };
//     }
//
// }
// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.contract.strategy;

import cn.srd.library.java.message.engine.contract.MessageProducer;
import cn.srd.library.java.message.engine.contract.model.dto.MessageConfigDTO;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * @author wjm
 * @since 2024-06-04 11:55
 */
@Slf4j
public abstract class MessageConfigStrategy<F extends MessageConfigDTO, B extends MessageConfigDTO.BrokerDTO, L extends MessageConfigDTO.ClientDTO, P extends MessageConfigDTO.ProducerDTO, C extends MessageConfigDTO.ConsumerDTO> {

    // default void test() {
    //     log.info("{}message engine mqtt-v3 customizer is enabled, starting initializing...", ModuleView.MESSAGE_ENGINE_SYSTEM);
    //
    //     B brokerDTO = registerBroker();
    //     Map<Method, P> producerRouter = registerProducerRouter();
    //     Map<Method, C> consumerRouter = registerConsumerRouter();
    //     F mqttV3ConfigDTO = MessageMqttV3ConfigDTO.builder()
    //             .brokerDTO(brokerDTO)
    //             .producerRouter(producerRouter)
    //             .consumerRouter(consumerRouter)
    //             .producerDTOs(Collections.getMapValues(producerRouter))
    //             .consumerDTOs(Collections.getMapValues(consumerRouter))
    //             .build();
    //
    //     log.info("""
    //                     {}message engine mqtt-v3 customizer has loaded the following configurations:
    //                     --------------------------------------------------------------------------------------------------------------------------------
    //                     MqttV3 Broker Info:
    //                     {}
    //                     --------------------------------------------------------------------------------------------------------------------------------
    //                     MqttV3 Producer Infos:
    //                     {}
    //                     --------------------------------------------------------------------------------------------------------------------------------
    //                     MqttV3 Consumer Infos:
    //                     {}
    //                     --------------------------------------------------------------------------------------------------------------------------------""",
    //             ModuleView.MESSAGE_ENGINE_SYSTEM,
    //             Converts.withJackson().toStringFormatted(mqttV3ConfigDTO.getBrokerDTO()),
    //             Converts.withJackson().toStringFormatted(mqttV3ConfigDTO.getProducerDTOs()),
    //             Converts.withJackson().toStringFormatted(mqttV3ConfigDTO.getConsumerDTOs())
    //     );
    //     log.info("{}message engine mqtt-v3 customizer initialized.", ModuleView.MESSAGE_ENGINE_SYSTEM);
    //
    //     return mqttV3ConfigDTO;
    // }

    public abstract F initialize();

    public abstract void onInitializeComplete();

    public abstract void registerProducerRouter(Method executeMethod, MessageConfigDTO.ProducerDTO producerDTO);

    public abstract MessageConfigDTO.ProducerDTO registerProducer(Method executeMethod, MessageProducer producerAnnotation);

}
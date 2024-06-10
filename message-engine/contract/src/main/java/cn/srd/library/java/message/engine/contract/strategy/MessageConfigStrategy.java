// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.contract.strategy;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.message.engine.contract.MessageConsumer;
import cn.srd.library.java.message.engine.contract.MessageProducer;
import cn.srd.library.java.message.engine.contract.model.dto.MessageConfigDTO;
import cn.srd.library.java.message.engine.contract.model.enums.MessageEngineType;
import cn.srd.library.java.tool.convert.all.Converts;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.compare.Comparators;
import cn.srd.library.java.tool.lang.reflect.Reflects;
import cn.srd.library.java.tool.spring.contract.Annotations;
import cn.srd.library.java.tool.spring.contract.Springs;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wjm
 * @since 2024-06-04 11:55
 */
@Slf4j
public abstract class MessageConfigStrategy<F extends MessageConfigDTO, B extends MessageConfigDTO.BrokerDTO, L extends MessageConfigDTO.ClientDTO, P extends MessageConfigDTO.ProducerDTO, C extends MessageConfigDTO.ConsumerDTO> {

    public void initialize(MessageEngineType engineType) {
        log.info("{}message engine {} customizer is enabled, starting initializing...", ModuleView.MESSAGE_ENGINE_SYSTEM, engineType.getDescription());

        B brokerDTO = registerBroker();
        Map<Method, P> producerRouter = registerProducerRouter(engineType);
        Map<Method, C> consumerRouter = registerConsumerRouter(engineType);
        F configDTO = Reflects.newInstance(getConfigType());
        configDTO.setBrokerDTO(brokerDTO)
                .setProducerRouter(producerRouter)
                .setConsumerRouter(consumerRouter)
                .setProducerDTOs(Collections.getMapValues(producerRouter))
                .setConsumerDTOs(Collections.getMapValues(consumerRouter));
        Springs.registerBean(configDTO.getClass().getName(), configDTO);

        log.info("""
                        {}message engine {} customizer has loaded the following configurations:
                        --------------------------------------------------------------------------------------------------------------------------------
                        {} Broker Info:
                        {}
                        --------------------------------------------------------------------------------------------------------------------------------
                        {} Producer Infos:
                        {}
                        --------------------------------------------------------------------------------------------------------------------------------
                        {} Consumer Infos:
                        {}
                        --------------------------------------------------------------------------------------------------------------------------------""",
                ModuleView.MESSAGE_ENGINE_SYSTEM,
                engineType.getDescription(), engineType.getDescription(),
                Converts.withJackson().toStringFormatted(configDTO.getBrokerDTO()),
                engineType.getDescription(),
                Converts.withJackson().toStringFormatted(configDTO.getProducerDTOs()),
                engineType.getDescription(),
                Converts.withJackson().toStringFormatted(configDTO.getConsumerDTOs())
        );
        log.info("{}message engine {} customizer initialized.", ModuleView.MESSAGE_ENGINE_SYSTEM, engineType.getDescription());
    }

    protected abstract Class<F> getConfigType();

    protected abstract B getBrokerDTO();

    protected abstract void registerClientFactory(B brokerDTO);

    protected abstract L getClientDTO(Annotation clientConfig, Method executeMethod);

    public void onInitializeComplete(MessageEngineType engineType) {
        Annotations.getAnnotatedMethods(MessageConsumer.class)
                .stream()
                .filter(consumerMethod -> Comparators.equals(engineType, consumerMethod.getAnnotation(MessageConsumer.class).config().engineType()))
                .forEach(consumerMethod -> {
                    MessageConsumer consumerAnnotation = consumerMethod.getAnnotation(MessageConsumer.class);
                    MessageEngineType forwardProducerEngineType = consumerAnnotation.forwardTo().config().engineType();
                    if (MessageEngineType.NIL != forwardProducerEngineType) {
                        forwardProducerEngineType.getConfigStrategy().registerProducerRouter(consumerMethod, Springs.getBean(getConfigType()).getConsumerRouter().get(consumerMethod).getForwardProducerDTO());
                    }
                });
    }

    private B registerBroker() {
        B brokerDTO = getBrokerDTO();
        registerClientFactory(brokerDTO);
        return brokerDTO;
    }

    protected Map<Method, P> registerProducerRouter(MessageEngineType engineType) {
        return Annotations.getAnnotatedMethods(MessageProducer.class).stream()
                .filter(producerMethod -> Comparators.equals(engineType, producerMethod.getAnnotation(MessageProducer.class).config().engineType()))
                .map(producerMethod -> Collections.ofPair(producerMethod, getProducerDTO(producerMethod)))
                .peek(producerRouter -> registerProducerFlow(producerRouter.getValue()))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    protected void registerProducerRouter(Method executeMethod, MessageConfigDTO.ProducerDTO producerDTO) {
        Map<Method, P> producerRouter = (Map<Method, P>) Springs.getBean(getConfigType()).getProducerRouter();
        producerRouter.put(executeMethod, (P) producerDTO);
        registerProducerFlow((P) producerDTO);
    }

    private P getProducerDTO(Method producerMethod) {
        return getProducerDTO(producerMethod, producerMethod.getAnnotation(MessageProducer.class));
    }

    protected abstract void registerProducerFlow(P producerDTO);

    protected abstract void registerConsumerFactory(C consumerDTO);

    protected abstract void registerConsumerFlow(C consumerDTO);

    protected abstract P getProducerDTO(Method executeMethod, MessageProducer producerAnnotation);

    // protected void registerFlow(IntegrationFlow flow, String flowId) {
    //     IntegrationFlowContext flowContext = Springs.getBean(IntegrationFlowContext.class);
    //     if (Nil.isNull(flowContext.getRegistrationById(flowId))) {
    //         flowContext.registration(flow)
    //                 .id(flowId)
    //                 .useFlowIdAsPrefix()
    //                 .register();
    //     }
    // }

    protected abstract C getConsumerDTO(Method executeMethod, MessageConsumer consumerAnnotation, MessageConfigDTO.ProducerDTO forwardProducerDTO);

    private Map<Method, C> registerConsumerRouter(MessageEngineType engineType) {
        return Annotations.getAnnotatedMethods(MessageConsumer.class)
                .stream()
                .filter(consumerMethod -> Comparators.equals(engineType, consumerMethod.getAnnotation(MessageConsumer.class).config().engineType()))
                .map(consumerMethod -> {
                    MessageConsumer consumerAnnotation = consumerMethod.getAnnotation(MessageConsumer.class);
                    MessageConfigDTO.ProducerDTO forwardProducerDTO = consumerAnnotation.forwardTo()
                            .config()
                            .engineType()
                            .getConfigStrategy()
                            .getProducerDTO(consumerMethod, consumerAnnotation.forwardTo());

                    C consumerDTO = getConsumerDTO(consumerMethod, consumerAnnotation, forwardProducerDTO);
                    registerConsumerFactory(consumerDTO);
                    registerConsumerFlow(consumerDTO);
                    return Collections.ofPair(consumerMethod, consumerDTO);
                })
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
    }

}
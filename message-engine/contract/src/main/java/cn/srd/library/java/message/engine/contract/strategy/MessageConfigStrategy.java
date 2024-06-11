// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.contract.strategy;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.message.engine.contract.MessageConsumer;
import cn.srd.library.java.message.engine.contract.MessageProducer;
import cn.srd.library.java.message.engine.contract.model.dto.MessageConfigDTO;
import cn.srd.library.java.message.engine.contract.model.dto.MessageVerificationConfigDTO;
import cn.srd.library.java.message.engine.contract.model.enums.MessageEngineType;
import cn.srd.library.java.message.engine.contract.model.properties.MessageEngineProperties;
import cn.srd.library.java.tool.convert.all.Converts;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.compare.Comparators;
import cn.srd.library.java.tool.lang.functional.Assert;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.reflect.Reflects;
import cn.srd.library.java.tool.spring.contract.Annotations;
import cn.srd.library.java.tool.spring.contract.Springs;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.context.IntegrationFlowContext;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wjm
 * @since 2024-06-04 11:55
 */
@Slf4j
@Component
public abstract class MessageConfigStrategy<S extends MessageEngineProperties, F extends MessageConfigDTO, B extends MessageConfigDTO.BrokerDTO, L extends MessageConfigDTO.ClientDTO, P extends MessageConfigDTO.ProducerDTO, C extends MessageConfigDTO.ConsumerDTO> {

    @Autowired private IntegrationFlowContext flowContext;

    protected abstract Class<F> getConfigType();

    protected abstract Class<S> getPropertiesType();

    protected abstract MessageVerificationConfigDTO getVerificationConfigDTO(F configDTO);

    protected abstract B getBrokerDTO();

    protected abstract L getClientDTO(Annotation clientConfig, Method executeMethod);

    protected abstract P getProducerDTO(Method executeMethod, MessageProducer producerAnnotation);

    protected abstract C getConsumerDTO(Method executeMethod, MessageConsumer consumerAnnotation, MessageConfigDTO.ProducerDTO forwardProducerDTO);

    protected abstract IntegrationFlow getProducerFlow(P producerDTO);

    protected abstract IntegrationFlow getConsumerFlow(C consumerDTO);

    protected abstract void registerClientFactory(B brokerDTO);

    protected abstract void registerProducerFactory(P producerDTO);

    protected abstract void registerConsumerFactory(C consumerDTO);

    public void initialize(MessageEngineType engineType) {
        log.info("{}message engine {} customizer is enabled, starting initializing...", ModuleView.MESSAGE_ENGINE_SYSTEM, engineType.getDescription());

        B brokerDTO = getBrokerDTO();
        List<P> producerDTOs = getProducerDTOs(engineType);
        List<C> consumerDTOs = getConsumerDTOs(engineType);
        F configDTO = Reflects.newInstance(getConfigType());
        configDTO.setBrokerDTO(brokerDTO)
                .setProducerRouter(getProducerRouter(producerDTOs))
                .setConsumerRouter(getConsumerRouter(consumerDTOs))
                .setProducerDTOs(producerDTOs)
                .setConsumerDTOs(consumerDTOs);

        verifyConfig(engineType, configDTO);

        registerClientFactory(brokerDTO);
        registerProducerFactory(producerDTOs);
        registerProducerFlow(producerDTOs);
        registerConsumerFactory(consumerDTOs);
        registerConsumerFlow(consumerDTOs);

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
                ModuleView.MESSAGE_ENGINE_SYSTEM, engineType.getDescription(),
                engineType.getDescription(),
                Converts.withJackson().toStringFormatted(configDTO.getBrokerDTO()),
                engineType.getDescription(),
                Converts.withJackson().toStringFormatted(configDTO.getProducerDTOs()),
                engineType.getDescription(),
                Converts.withJackson().toStringFormatted(configDTO.getConsumerDTOs())
        );
        log.info("{}message engine {} customizer initialized.", ModuleView.MESSAGE_ENGINE_SYSTEM, engineType.getDescription());
    }

    public void onInitializeComplete(MessageEngineType engineType) {
        Annotations.getAnnotatedMethods(MessageConsumer.class)
                .stream()
                .filter(consumerMethod -> Comparators.equals(engineType, consumerMethod.getAnnotation(MessageConsumer.class).config().engineType()))
                .forEach(consumerMethod -> {
                    MessageConsumer consumerAnnotation = consumerMethod.getAnnotation(MessageConsumer.class);
                    MessageEngineType forwardProducerEngineType = consumerAnnotation.forwardTo().config().engineType();
                    if (Comparators.notEquals(MessageEngineType.NIL, forwardProducerEngineType)) {
                        forwardProducerEngineType.getConfigStrategy().registerForwardProducerRouter(consumerMethod, Springs.getBean(getConfigType()).getConsumerRouter().get(consumerMethod).getForwardProducerDTO());
                    }
                });
    }

    @SuppressWarnings(SuppressWarningConstant.PREVIEW)
    private void verifyConfig(MessageEngineType engineType, F configDTO) {
        MessageVerificationConfigDTO verificationConfigDTO = getVerificationConfigDTO(configDTO);
        if (Nil.isEmpty(configDTO.getBrokerDTO().getServerUrls())) {
            verificationConfigDTO.getBrokerFailedReason().put("invalid server urls", STR."invalid server urls, you must provide them in the config file, see [\{getPropertiesType().getName()}].");
        }

        Map<String, MessageVerificationConfigDTO.ProducerDTO> producerMethodPointCache = Converts.toMap(verificationConfigDTO.getProducerFailedReasons(), MessageVerificationConfigDTO.ProducerDTO::getMethodPoint);
        verificationConfigDTO.setProducerFailedReasons(configDTO.getProducerDTOs()
                .stream()
                .map(producerDTO -> {
                    Map<String, String> producerFailedReasons = Collections.newHashMap();
                    if (Nil.isBlank(producerDTO.getTopic())) {
                        producerFailedReasons.put("invalid topic name!", "the topic name should not be blank, please check!");
                    }
                    Class<?> producerDeclaringClass = producerDTO.getClientDTO().getExecuteMethod().getDeclaringClass();
                    if (Nil.isNull(Springs.getBean(producerDeclaringClass))) {
                        producerFailedReasons.put("producer instance not found!", STR."could not find the producer [\{producerDeclaringClass.getName()}] instance in spring ioc, please add it into spring ioc!");
                    }

                    MessageVerificationConfigDTO.ProducerDTO producerFailedReasonDTO = producerMethodPointCache.computeIfAbsent(
                            producerDTO.getClientDTO().getFlowId(),
                            flowId -> MessageVerificationConfigDTO.ProducerDTO.builder()
                                    .methodPoint(flowId)
                                    .failedReason(Collections.ofHashMap(producerFailedReasons))
                                    .build()
                    );
                    producerFailedReasonDTO.getFailedReason().putAll(producerFailedReasons);
                    return producerFailedReasonDTO;
                })
                .filter(producerFailedReasonDTO -> Nil.isNotEmpty(producerFailedReasonDTO.getFailedReason()))
                .toList()
        );

        Map<String, MessageVerificationConfigDTO.ConsumerDTO> consumerMethodPointCache = Converts.toMap(verificationConfigDTO.getConsumerFailedReasons(), MessageVerificationConfigDTO.ConsumerDTO::getMethodPoint);
        verificationConfigDTO.setConsumerFailedReasons(configDTO.getConsumerDTOs()
                .stream()
                .map(consumerDTO -> {
                    Map<String, String> consumerFailedReasons = Collections.newHashMap();
                    if (Nil.isEmpty(consumerDTO.getTopics())) {
                        consumerFailedReasons.put("topics not found!", "could not find topics to consume, please check!");
                    }
                    if (Nil.isNotZeroValue(consumerDTO.getTopics().stream().filter(Strings::isBlank).count())) {
                        consumerFailedReasons.put("invalid topic names!", STR."found blank topic name in \{consumerDTO.getTopics()}, please check!");
                    }
                    Class<?> consumerDeclaringClass = consumerDTO.getClientDTO().getExecuteMethod().getDeclaringClass();
                    if (Nil.isNull(Springs.getBean(consumerDeclaringClass))) {
                        consumerFailedReasons.put("consumer instance not found!", STR."could not find the consumer [\{consumerDeclaringClass.getName()}] instance in spring ioc, please add it into spring ioc!");
                    }

                    MessageVerificationConfigDTO.ConsumerDTO consumerFailedReasonDTO = consumerMethodPointCache.computeIfAbsent(
                            consumerDTO.getClientDTO().getFlowId(),
                            flowId -> MessageVerificationConfigDTO.ConsumerDTO.builder()
                                    .methodPoint(flowId)
                                    .failedReason(Collections.ofHashMap(consumerFailedReasons))
                                    .build()
                    );
                    consumerFailedReasonDTO.getFailedReason().putAll(consumerFailedReasons);
                    return consumerFailedReasonDTO;
                })
                .filter(consumerFailedReasonDTO -> Nil.isNotEmpty(consumerFailedReasonDTO.getFailedReason()))
                .toList()
        );

        verificationConfigDTO
                .setEngineType(engineType)
                .setVerifyPassed(Nil.isEmpty(verificationConfigDTO.getBrokerFailedReason()) && Nil.isAllEmpty(verificationConfigDTO.getProducerFailedReasons(), verificationConfigDTO.getConsumerFailedReasons()));

        Assert.of().setMessage("{}message engine {} initialize failed, the failed reason as following: \n{}", ModuleView.MESSAGE_ENGINE_SYSTEM, engineType.getDescription(), Converts.withJackson().toStringFormatted(verificationConfigDTO))
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfFalse(verificationConfigDTO.isVerifyPassed());
    }

    private List<P> getProducerDTOs(MessageEngineType engineType) {
        return Annotations.getAnnotatedMethods(MessageProducer.class).stream()
                .filter(producerMethod -> Comparators.equals(engineType, producerMethod.getAnnotation(MessageProducer.class).config().engineType()))
                .map(producerMethod -> getProducerDTO(producerMethod, producerMethod.getAnnotation(MessageProducer.class)))
                .toList();
    }

    private Map<Method, P> getProducerRouter(List<P> producerDTOs) {
        return producerDTOs.stream()
                .map(producerDTO -> Collections.ofPair(producerDTO.getClientDTO().getExecuteMethod(), producerDTO))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    private void registerForwardProducerRouter(Method executeMethod, MessageConfigDTO.ProducerDTO producerDTO) {
        Map<Method, P> producerRouter = (Map<Method, P>) Springs.getBean(getConfigType()).getProducerRouter();
        producerRouter.put(executeMethod, (P) producerDTO);
        registerProducerFlow(Collections.ofArrayList((P) producerDTO));
    }

    private void registerProducerFactory(List<P> producerDTOs) {
        producerDTOs.forEach(this::registerProducerFactory);
    }

    private void registerProducerFlow(List<P> producerDTOs) {
        producerDTOs.stream()
                .filter(producerDTO -> Nil.isNull(this.flowContext.getRegistrationById(producerDTO.getClientDTO().getFlowId())))
                .forEach(producerDTO -> registerFlow(producerDTO.getClientDTO().getFlowId(), getProducerFlow(producerDTO)));
    }

    private List<C> getConsumerDTOs(MessageEngineType engineType) {
        return Annotations.getAnnotatedMethods(MessageConsumer.class)
                .stream()
                .filter(consumerMethod -> Comparators.equals(engineType, consumerMethod.getAnnotation(MessageConsumer.class).config().engineType()))
                .map(consumerMethod -> {
                    MessageConsumer consumerAnnotation = consumerMethod.getAnnotation(MessageConsumer.class);
                    return getConsumerDTO(consumerMethod, consumerAnnotation, consumerAnnotation.forwardTo()
                            .config()
                            .engineType()
                            .getConfigStrategy()
                            .getProducerDTO(consumerMethod, consumerAnnotation.forwardTo())
                    );
                })
                .toList();
    }

    private Map<Method, C> getConsumerRouter(List<C> consumerDTOs) {
        return consumerDTOs.stream()
                .map(consumerDTO -> Collections.ofPair(consumerDTO.getClientDTO().getExecuteMethod(), consumerDTO))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
    }

    private void registerConsumerFactory(List<C> consumerDTOs) {
        consumerDTOs.forEach(this::registerConsumerFactory);
    }

    private void registerConsumerFlow(List<C> consumerDTOs) {
        consumerDTOs.stream()
                .filter(consumerDTO -> Nil.isNull(this.flowContext.getRegistrationById(consumerDTO.getClientDTO().getFlowId())))
                .forEach(consumerDTO -> registerFlow(consumerDTO.getClientDTO().getFlowId(), getConsumerFlow(consumerDTO)));
    }

    private void registerFlow(String flowId, IntegrationFlow flow) {
        this.flowContext.registration(flow)
                .id(flowId)
                .useFlowIdAsPrefix()
                .register();
    }

}
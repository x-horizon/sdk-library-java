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
import cn.srd.library.java.message.engine.contract.model.property.MessageEngineProperty;
import cn.srd.library.java.tool.convert.api.Converts;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.compare.Comparators;
import cn.srd.library.java.tool.lang.functional.Assert;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.reflect.Reflects;
import cn.srd.library.java.tool.spring.contract.support.Annotations;
import cn.srd.library.java.tool.spring.contract.support.Springs;
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
public abstract class MessageConfigStrategy<Property extends MessageEngineProperty, Config extends MessageConfigDTO, BrokerConfig extends MessageConfigDTO.BrokerDTO, ClientConfig extends MessageConfigDTO.ClientDTO, ProducerConfig extends MessageConfigDTO.ProducerDTO, ConsumerConfig extends MessageConfigDTO.ConsumerDTO> {

    @Autowired private IntegrationFlowContext flowContext;

    protected abstract Class<Config> getConfigType();

    protected abstract Class<Property> getPropertyType();

    protected abstract MessageVerificationConfigDTO getVerificationConfigDTO(Config configDTO);

    protected abstract BrokerConfig getBrokerDTO();

    protected abstract ClientConfig getClientDTO(Annotation clientConfig, Method executeMethod);

    protected abstract ProducerConfig getProducerDTO(Method executeMethod, MessageProducer producerAnnotation);

    protected abstract ConsumerConfig getConsumerDTO(Method executeMethod, MessageConsumer consumerAnnotation, MessageConfigDTO.ProducerDTO forwardProducerDTO);

    protected abstract IntegrationFlow getProducerFlow(ProducerConfig producerDTO);

    protected abstract IntegrationFlow getConsumerFlow(ConsumerConfig consumerDTO);

    protected abstract void completeNativeConfigDTO(Config configDTO);

    protected abstract void registerClientFactory(BrokerConfig brokerDTO);

    protected abstract void registerProducerFactory(ProducerConfig producerDTO);

    protected abstract void registerConsumerFactory(ConsumerConfig consumerDTO);

    public void initialize(MessageEngineType engineType) {
        log.debug("{}message engine {} customizer is enabled, starting initializing...", ModuleView.MESSAGE_ENGINE_SYSTEM, engineType.getDescription());

        BrokerConfig brokerDTO = getBrokerDTO();
        List<ProducerConfig> producerDTOs = getProducerDTOs(engineType);
        List<ConsumerConfig> consumerDTOs = getConsumerDTOs(engineType);
        Config configDTO = Reflects.newInstance(getConfigType());
        configDTO.setBrokerDTO(brokerDTO)
                .setProducerRouter(getProducerRouter(producerDTOs))
                .setConsumerRouter(getConsumerRouter(consumerDTOs))
                .setProducerDTOs(producerDTOs)
                .setConsumerDTOs(consumerDTOs);

        verifyConfig(engineType, configDTO);
        completeNativeConfigDTO(configDTO);

        registerClientFactory(brokerDTO);
        registerProducerFactory(producerDTOs);
        registerProducerFlow(producerDTOs);
        registerConsumerFactory(consumerDTOs);
        registerConsumerFlow(consumerDTOs);

        Springs.registerBean(configDTO.getClass().getName(), configDTO);

        log.debug("""
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
                Converts.onJackson().toJsonString(configDTO.getBrokerDTO()),
                engineType.getDescription(),
                Converts.onJackson().toJsonString(configDTO.getProducerDTOs()),
                engineType.getDescription(),
                Converts.onJackson().toJsonString(configDTO.getConsumerDTOs())
        );
        log.debug("{}message engine {} customizer initialized.", ModuleView.MESSAGE_ENGINE_SYSTEM, engineType.getDescription());
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

    private List<ProducerConfig> getProducerDTOs(MessageEngineType engineType) {
        return Annotations.getAnnotatedMethods(MessageProducer.class).stream()
                .filter(producerMethod -> Comparators.equals(engineType, producerMethod.getAnnotation(MessageProducer.class).config().engineType()))
                .map(producerMethod -> getProducerDTO(producerMethod, producerMethod.getAnnotation(MessageProducer.class)))
                .toList();
    }

    private Map<Method, ProducerConfig> getProducerRouter(List<ProducerConfig> producerDTOs) {
        return producerDTOs.stream()
                .map(producerDTO -> Collections.ofPair(producerDTO.getClientDTO().getExecuteMethod(), producerDTO))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    private void registerForwardProducerRouter(Method executeMethod, MessageConfigDTO.ProducerDTO producerDTO) {
        Map<Method, ProducerConfig> producerRouter = (Map<Method, ProducerConfig>) Springs.getBean(getConfigType()).getProducerRouter();
        producerRouter.put(executeMethod, (ProducerConfig) producerDTO);
        registerProducerFlow(Collections.ofArrayList((ProducerConfig) producerDTO));
    }

    private void registerProducerFactory(List<ProducerConfig> producerDTOs) {
        producerDTOs.forEach(this::registerProducerFactory);
    }

    private void registerProducerFlow(List<ProducerConfig> producerDTOs) {
        producerDTOs.stream()
                .filter(producerDTO -> Nil.isNull(this.flowContext.getRegistrationById(producerDTO.getClientDTO().getFlowId())))
                .forEach(producerDTO -> registerFlow(producerDTO.getClientDTO().getFlowId(), getProducerFlow(producerDTO)));
    }

    private List<ConsumerConfig> getConsumerDTOs(MessageEngineType engineType) {
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

    private Map<Method, ConsumerConfig> getConsumerRouter(List<ConsumerConfig> consumerDTOs) {
        return consumerDTOs.stream()
                .map(consumerDTO -> Collections.ofPair(consumerDTO.getClientDTO().getExecuteMethod(), consumerDTO))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
    }

    private void registerConsumerFactory(List<ConsumerConfig> consumerDTOs) {
        consumerDTOs.forEach(this::registerConsumerFactory);
    }

    private void registerConsumerFlow(List<ConsumerConfig> consumerDTOs) {
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

    @SuppressWarnings(SuppressWarningConstant.PREVIEW)
    private void verifyConfig(MessageEngineType engineType, Config configDTO) {
        MessageVerificationConfigDTO verificationConfigDTO = getVerificationConfigDTO(configDTO);
        if (Nil.isEmpty(configDTO.getBrokerDTO().getServerUrls())) {
            verificationConfigDTO.getBrokerFailedReason().put("invalid server urls", STR."invalid server urls, you must provide them in the config file, see [\{getPropertyType().getName()}].");
        }

        Map<String, MessageVerificationConfigDTO.ProducerDTO> producerMethodPointCache = Converts.toMap(verificationConfigDTO.getProducerFailedReasons(), MessageVerificationConfigDTO.ProducerDTO::getMethodPoint);
        verificationConfigDTO.setProducerFailedReasons(configDTO.getProducerDTOs()
                .stream()
                .map(producerDTO -> {
                    Map<String, String> producerFailedReasons = Collections.newHashMap();
                    if (Nil.isBlank(producerDTO.getTopic())) {
                        producerFailedReasons.put("invalid topic name", "the topic name should not be blank, please check!");
                    }
                    Class<?> producerDeclaringClass = producerDTO.getClientDTO().getExecuteMethod().getDeclaringClass();
                    if (Springs.notExistBean(producerDeclaringClass)) {
                        producerFailedReasons.put("producer instance not found", STR."could not find the producer [\{producerDeclaringClass.getName()}] instance in spring ioc, please add it into spring ioc!");
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
                        consumerFailedReasons.put("topics not found", "could not find topics to consume, please check!");
                    }
                    if (Nil.isNotZeroValue(consumerDTO.getTopics().stream().filter(Strings::isBlank).count())) {
                        consumerFailedReasons.put("invalid topic names", STR."found blank topic name in \{consumerDTO.getTopics()}, please check!");
                    }
                    Class<?> consumerDeclaringClass = consumerDTO.getClientDTO().getExecuteMethod().getDeclaringClass();
                    if (Springs.notExistBean(consumerDeclaringClass)) {
                        consumerFailedReasons.put("consumer instance not found", STR."could not find the consumer [\{consumerDeclaringClass.getName()}] instance in spring ioc, please add it into spring ioc!");
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

        Assert.of().setMessage("{}message engine {} initialize failed, the failed reason as following: \n{}", ModuleView.MESSAGE_ENGINE_SYSTEM, engineType.getDescription(), Converts.onJackson().toJsonString(verificationConfigDTO))
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfFalse(verificationConfigDTO.isVerifyPassed());
    }

}
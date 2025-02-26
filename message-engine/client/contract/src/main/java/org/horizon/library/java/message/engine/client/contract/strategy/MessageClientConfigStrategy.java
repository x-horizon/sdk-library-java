package org.horizon.library.java.message.engine.client.contract.strategy;

import org.horizon.library.java.contract.constant.module.ModuleView;
import org.horizon.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.library.java.contract.constant.text.SymbolConstant;
import org.horizon.library.java.contract.model.throwable.LibraryJavaInternalException;
import org.horizon.library.java.message.engine.client.contract.MessageClientConsumer;
import org.horizon.library.java.message.engine.client.contract.MessageClientProducer;
import org.horizon.library.java.message.engine.client.contract.model.dto.MessageClientConfigDTO;
import org.horizon.library.java.message.engine.client.contract.model.dto.MessageClientVerificationConfigDTO;
import org.horizon.library.java.message.engine.client.contract.model.enums.MessageClientType;
import org.horizon.library.java.message.engine.client.contract.model.property.MessageClientProperty;
import org.horizon.library.java.message.engine.client.contract.support.MessageClientFlows;
import org.horizon.library.java.tool.convert.api.Converts;
import org.horizon.library.java.tool.lang.collection.Collections;
import org.horizon.library.java.tool.lang.compare.Comparators;
import org.horizon.library.java.tool.lang.functional.Assert;
import org.horizon.library.java.tool.lang.object.Methods;
import org.horizon.library.java.tool.lang.object.Nil;
import org.horizon.library.java.tool.lang.object.Objects;
import org.horizon.library.java.tool.lang.reflect.Reflects;
import org.horizon.library.java.tool.lang.text.Strings;
import org.horizon.library.java.tool.spring.contract.support.Annotations;
import org.horizon.library.java.tool.spring.contract.support.Expressions;
import org.horizon.library.java.tool.spring.contract.support.Springs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.context.IntegrationFlowContext;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author wjm
 * @since 2024-06-04 11:55
 */
@Slf4j
@Component
public abstract class MessageClientConfigStrategy<Property extends MessageClientProperty, Config extends MessageClientConfigDTO, BrokerConfig extends MessageClientConfigDTO.BrokerDTO, ClientConfig extends MessageClientConfigDTO.ClientDTO, ProducerConfig extends MessageClientConfigDTO.ProducerDTO, ConsumerConfig extends MessageClientConfigDTO.ConsumerDTO> {

    @Autowired private IntegrationFlowContext flowContext;

    protected abstract Class<Config> getConfigType();

    protected abstract Class<Property> getPropertyType();

    protected abstract MessageClientType getMessageEngineType();

    protected abstract Config getMessageConfigDTO();

    protected abstract MessageClientVerificationConfigDTO getVerificationConfigDTO(Config configDTO);

    protected abstract BrokerConfig getBrokerDTO();

    protected abstract ClientConfig getClientDTO(Annotation clientConfig, Method executeMethod);

    protected abstract ProducerConfig getProducerDTO(Method executeMethod, MessageClientProducer producerAnnotation);

    protected abstract ConsumerConfig getConsumerDTO(Method executeMethod, MessageClientConsumer consumerAnnotation, MessageClientConfigDTO.ProducerDTO forwardProducerDTO);

    protected abstract IntegrationFlow getProducerFlow(ProducerConfig producerDTO);

    protected abstract IntegrationFlow getConsumerFlow(ConsumerConfig consumerDTO);

    protected abstract void completeNativeConfigDTO(Config configDTO);

    protected abstract void registerClientFactory(BrokerConfig brokerDTO);

    protected abstract void registerProducerFactory(ProducerConfig producerDTO);

    protected abstract void registerConsumerFactory(ConsumerConfig consumerDTO);

    protected boolean computeDynamicIs(String topic) {
        return Strings.startWith(topic, SymbolConstant.WELL_NUMBER);
    }

    public void initialize(MessageClientType engineType) {
        log.debug("{}message engine {} client customizer is enabled, starting initializing...", ModuleView.MESSAGE_ENGINE_CLIENT_SYSTEM, engineType.getDescription());

        BrokerConfig brokerDTO = getBrokerDTO();
        List<ProducerConfig> producerDTOs = getProducerDTOs(engineType);
        List<ProducerConfig> dynamicProducerDTOs = producerDTOs.stream().filter(ProducerConfig::getDynamicIs).toList();
        List<ProducerConfig> staticProducerDTOs = producerDTOs.stream().filter(producerDTO -> !producerDTO.getDynamicIs()).toList();
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
        registerProducerFactory(staticProducerDTOs);
        registerProducerFlow(staticProducerDTOs);
        registerConsumerFactory(consumerDTOs);
        registerConsumerFlow(consumerDTOs);

        Springs.registerBean(configDTO.getClass().getName(), configDTO);

        log.debug("""
                        {}message engine {} client customizer has loaded the following configurations:
                        --------------------------------------------------------------------------------------------------------------------------------
                        {} broker info:
                        {}
                        --------------------------------------------------------------------------------------------------------------------------------
                        {} static producer infos:
                        {}
                        --------------------------------------------------------------------------------------------------------------------------------
                        {} dynamic producer infos:
                        {}
                        --------------------------------------------------------------------------------------------------------------------------------
                        {} consumer infos:
                        {}
                        --------------------------------------------------------------------------------------------------------------------------------""",
                ModuleView.MESSAGE_ENGINE_CLIENT_SYSTEM, engineType.getDescription(),
                engineType.getDescription(),
                Converts.onJackson().toJsonString(configDTO.getBrokerDTO()),
                engineType.getDescription(),
                Converts.onJackson().toJsonString(staticProducerDTOs),
                engineType.getDescription(),
                Converts.onJackson().toJsonString(dynamicProducerDTOs),
                engineType.getDescription(),
                Converts.onJackson().toJsonString(configDTO.getConsumerDTOs())
        );
        log.debug("{}message engine {} client customizer initialized.", ModuleView.MESSAGE_ENGINE_CLIENT_SYSTEM, engineType.getDescription());
    }

    public void onInitializeComplete(MessageClientType engineType) {
        Annotations.getAnnotatedMethods(MessageClientConsumer.class)
                .stream()
                .filter(consumerMethod -> Comparators.equals(engineType, consumerMethod.getAnnotation(MessageClientConsumer.class).config().engineType()))
                .forEach(consumerMethod -> {
                    MessageClientConsumer consumerAnnotation = consumerMethod.getAnnotation(MessageClientConsumer.class);
                    MessageClientType forwardProducerEngineType = consumerAnnotation.forwardTo().config().engineType();
                    if (Comparators.notEquals(MessageClientType.NIL, forwardProducerEngineType)) {
                        forwardProducerEngineType.getConfigStrategy().registerForwardProducerRouter(consumerMethod, Springs.getBean(getConfigType()).getConsumerRouter().get(consumerMethod).getForwardProducerDTO());
                    }
                });
    }

    @SuppressWarnings({SuppressWarningConstant.PREVIEW, SuppressWarningConstant.UNCHECKED})
    public IntegrationFlowContext.IntegrationFlowRegistration getIntegrationFlowRegistration(Method producerMethod, String[] producerMethodParameterNames, Object[] producerMethodParameterValues, String staticFlowId) {
        IntegrationFlowContext integrationFlowContext = Springs.getBean(IntegrationFlowContext.class);
        IntegrationFlowContext.IntegrationFlowRegistration integrationFlowRegistration = integrationFlowContext.getRegistrationById(staticFlowId);
        if (Nil.isNotNull(integrationFlowRegistration)) {
            return integrationFlowRegistration;
        }

        Config messageConfigDTO = getMessageConfigDTO();
        ProducerConfig producerDTO = (ProducerConfig) messageConfigDTO.getProducerRouter().get(producerMethod);
        MessageClientType messageClientType = getMessageEngineType();
        String topic = Optional.ofNullable(Expressions.getInstance().parse(producerMethodParameterNames, producerMethodParameterValues, producerDTO.getTopic()))
                .map(Object::toString)
                .orElseThrow(() -> new LibraryJavaInternalException(STR."\{ModuleView.MESSAGE_ENGINE_CLIENT_SYSTEM}could not parse the topic from method [\{Methods.getFullName(producerMethod)}], the method parameter names are \{producerMethodParameterNames}, the method parameter value are \{producerMethodParameterValues}, the topic expression is [\{producerDTO.getTopic()}], please check!"));
        String dynamicFlowId = MessageClientFlows.getDynamicFlowId(messageClientType, producerMethod, topic);
        integrationFlowRegistration = integrationFlowContext.getRegistrationById(dynamicFlowId);
        if (Nil.isNotNull(integrationFlowRegistration)) {
            return integrationFlowRegistration;
        }

        ProducerConfig dynamicProducerDTO = Objects.clone(producerDTO);
        dynamicProducerDTO
                .setTopic(topic)
                .getClientDTO()
                .setClientId(MessageClientFlows.getDistributedUniqueClientId(dynamicProducerDTO.getClientDTO().getIdGenerateType(), dynamicFlowId))
                .setFlowId(dynamicFlowId);
        messageConfigDTO.getDynamicProducerRouter().put(dynamicFlowId, dynamicProducerDTO);

        registerProducerFactory(List.of(dynamicProducerDTO));
        registerProducerFlow(List.of(dynamicProducerDTO));

        log.debug("""
                        {}message engine {} client has loaded the the dynamic producer info:
                        {}""",
                ModuleView.MESSAGE_ENGINE_CLIENT_SYSTEM, messageClientType.getDescription(),
                Converts.onJackson().toJsonString(dynamicProducerDTO)
        );

        return integrationFlowContext.getRegistrationById(dynamicFlowId);
    }

    private List<ProducerConfig> getProducerDTOs(MessageClientType engineType) {
        return Annotations.getAnnotatedMethods(MessageClientProducer.class).stream()
                .filter(producerMethod -> Comparators.equals(engineType, producerMethod.getAnnotation(MessageClientProducer.class).config().engineType()))
                .map(producerMethod -> getProducerDTO(producerMethod, producerMethod.getAnnotation(MessageClientProducer.class)))
                .toList();
    }

    private Map<Method, ProducerConfig> getProducerRouter(List<ProducerConfig> producerDTOs) {
        return producerDTOs.stream()
                .map(producerDTO -> Collections.ofPair(producerDTO.getClientDTO().getExecuteMethod(), producerDTO))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    private void registerForwardProducerRouter(Method executeMethod, MessageClientConfigDTO.ProducerDTO producerDTO) {
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

    private List<ConsumerConfig> getConsumerDTOs(MessageClientType engineType) {
        return Annotations.getAnnotatedMethods(MessageClientConsumer.class)
                .stream()
                .filter(consumerMethod -> Comparators.equals(engineType, consumerMethod.getAnnotation(MessageClientConsumer.class).config().engineType()))
                .map(consumerMethod -> {
                    MessageClientConsumer consumerAnnotation = consumerMethod.getAnnotation(MessageClientConsumer.class);
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
    private void verifyConfig(MessageClientType engineType, Config configDTO) {
        MessageClientVerificationConfigDTO verificationConfigDTO = getVerificationConfigDTO(configDTO);
        if (Nil.isEmpty(configDTO.getBrokerDTO().getServerUrls())) {
            verificationConfigDTO.getBrokerFailedReason().put("invalid server urls", STR."invalid server urls, you must provide them in the config file, see [\{getPropertyType().getName()}].");
        }

        Map<String, MessageClientVerificationConfigDTO.ProducerDTO> producerMethodPointCache = Converts.toMap(verificationConfigDTO.getProducerFailedReasons(), MessageClientVerificationConfigDTO.ProducerDTO::getMethodPoint);
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

                    MessageClientVerificationConfigDTO.ProducerDTO producerFailedReasonDTO = producerMethodPointCache.computeIfAbsent(
                            producerDTO.getClientDTO().getFlowId(),
                            flowId -> MessageClientVerificationConfigDTO.ProducerDTO.builder()
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

        Map<String, MessageClientVerificationConfigDTO.ConsumerDTO> consumerMethodPointCache = Converts.toMap(verificationConfigDTO.getConsumerFailedReasons(), MessageClientVerificationConfigDTO.ConsumerDTO::getMethodPoint);
        verificationConfigDTO.setConsumerFailedReasons(configDTO.getConsumerDTOs()
                .stream()
                .map(consumerDTO -> {
                    Map<String, String> consumerFailedReasons = Collections.newHashMap();
                    if (Nil.isEmpty(consumerDTO.getTopics())) {
                        consumerFailedReasons.put("topics not found", "could not find topics to consume, please check!");
                    }
                    if (Nil.isNotZeroValue(consumerDTO.getTopics().stream().filter(Nil::isBlank).count())) {
                        consumerFailedReasons.put("invalid topic names", STR."found blank topic name in \{consumerDTO.getTopics()}, please check!");
                    }
                    Class<?> consumerDeclaringClass = consumerDTO.getClientDTO().getExecuteMethod().getDeclaringClass();
                    if (Springs.notExistBean(consumerDeclaringClass)) {
                        consumerFailedReasons.put("consumer instance not found", STR."could not find the consumer [\{consumerDeclaringClass.getName()}] instance in spring ioc, please add it into spring ioc!");
                    }

                    MessageClientVerificationConfigDTO.ConsumerDTO consumerFailedReasonDTO = consumerMethodPointCache.computeIfAbsent(
                            consumerDTO.getClientDTO().getFlowId(),
                            flowId -> MessageClientVerificationConfigDTO.ConsumerDTO.builder()
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

        Assert.of().setMessage("{}message engine {} client initialize failed, the failed reason as following: \n{}", ModuleView.MESSAGE_ENGINE_CLIENT_SYSTEM, engineType.getDescription(), Converts.onJackson().toJsonString(verificationConfigDTO))
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfFalse(verificationConfigDTO.isVerifyPassed());
    }

}
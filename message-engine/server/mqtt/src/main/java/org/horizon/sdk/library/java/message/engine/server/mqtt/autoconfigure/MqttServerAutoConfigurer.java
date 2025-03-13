package org.horizon.sdk.library.java.message.engine.server.mqtt.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.horizon.sdk.library.java.message.engine.server.mqtt.context.MqttServerContext;
import org.horizon.sdk.library.java.message.engine.server.mqtt.handler.*;
import org.horizon.sdk.library.java.message.engine.server.mqtt.model.property.MqttServerCommonProperty;
import org.horizon.sdk.library.java.message.engine.server.mqtt.model.property.MqttServerNonSslProperty;
import org.horizon.sdk.library.java.message.engine.server.mqtt.model.property.MqttServerSslProperty;
import org.horizon.sdk.library.java.message.engine.server.mqtt.strategy.*;
import org.horizon.sdk.library.java.tool.enums.autoconfigure.EnableEnumAutowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import static org.horizon.sdk.library.java.message.engine.contract.autoconfigure.MessageEngineBasePackagePathAutoConfigurer.BASE_PACKAGE_PATH;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Message Server MQTT
 *
 * @author wjm
 * @since 2024-12-29 17:23
 */
@Slf4j
@AutoConfiguration
@ConditionalOnBean(MessageMqttServerRegistrar.class)
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableEnumAutowired(scanPackagePaths = BASE_PACKAGE_PATH)
@EnableConfigurationProperties({MqttServerCommonProperty.class, MqttServerNonSslProperty.class, MqttServerSslProperty.class})
public class MqttServerAutoConfigurer {

    @Bean
    public MqttServerLifecycleManager mqttServerLifecycleManager() {
        return new MqttServerLifecycleManager();
    }

    @Bean
    public MqttServerContext mqttServerContext() {
        return new MqttServerContext();
    }

    @Bean
    public MqttMessageConnectStrategy mqttMessageConnectStrategy() {
        return new MqttMessageConnectStrategy();
    }

    @Bean
    public MqttMessagePublishStrategy mqttMessagePublishStrategy() {
        return new MqttMessagePublishStrategy();
    }

    @Bean
    public MqttMessagePublishAckOnQualityOfService1Strategy mqttMessagePublishAckOnQualityOfService1Strategy() {
        return new MqttMessagePublishAckOnQualityOfService1Strategy();
    }

    @Bean
    public MqttMessageSubscribeStrategy mqttMessageSubscribeStrategy() {
        return new MqttMessageSubscribeStrategy();
    }

    @Bean
    public MqttMessageUnsubscribeStrategy mqttMessageUnsubscribeStrategy() {
        return new MqttMessageUnsubscribeStrategy();
    }

    @Bean
    @ConditionalOnMissingBean(ClientConnectAuthHandler.class)
    public ClientConnectAuthNonHandler clientConnectAuthNonHandler() {
        return new ClientConnectAuthNonHandler();
    }

    @Bean
    @ConditionalOnMissingBean(ClientPublishHandler.class)
    public ClientPublishNonHandler clientPublishNonHandler() {
        return new ClientPublishNonHandler();
    }

    @Bean
    @ConditionalOnMissingBean(ClientPublishAckOnQualityOfService1Handler.class)
    public ClientPublishAckOnQualityOfService1NonHandler clientPublishAckOnQualityOfService1NonHandler() {
        return new ClientPublishAckOnQualityOfService1NonHandler();
    }

    @Bean
    @ConditionalOnMissingBean(ClientSubscribeHandler.class)
    public ClientSubscribeNonHandler clientSubscribeNonHandler() {
        return new ClientSubscribeNonHandler();
    }

    @Bean
    @ConditionalOnMissingBean(ClientUnsubscribeHandler.class)
    public ClientUnsubscribeNonHandler clientUnsubscribeNonHandler() {
        return new ClientUnsubscribeNonHandler();
    }

}
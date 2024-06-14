// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.mqtt.v5;

import cn.srd.library.java.message.engine.contract.model.enums.ClientIdGenerateType;
import cn.srd.library.java.message.engine.contract.model.enums.MessageQosType;
import cn.srd.library.java.message.engine.mqtt.contract.support.MqttDefaultConfigConstant;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wjm
 * @since 2024-05-30 15:36
 */
@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface MqttV5Config {

    MqttV5Config.ClientConfig clientConfig() default @MqttV5Config.ClientConfig();

    MqttV5Config.ProducerConfig producerConfig() default @MqttV5Config.ProducerConfig();

    MqttV5Config.ConsumerConfig consumerConfig() default @MqttV5Config.ConsumerConfig();

    @interface ClientConfig {

        // TODO wjm 此处实现不够好，与 snowflake id 强绑定，客户端不一定需要用到 snowflake id，目前客户端必须提供正确的 redis 配置，否则项目启动报错
        ClientIdGenerateType idGenerateType() default ClientIdGenerateType.SNOWFLAKE;

        MessageQosType qosType() default MessageQosType.AT_MOST_ONCE;

        String completionTimeout() default MqttDefaultConfigConstant.Client.COMPLETION_TIMEOUT;

        String disconnectCompletionTimeout() default MqttDefaultConfigConstant.Client.DISCONNECT_COMPLETION_TIMEOUT;

    }

    @interface ProducerConfig {

        boolean needToSendAsync() default MqttDefaultConfigConstant.Producer.NEED_TO_SEND_ASYNC;

    }

    @interface ConsumerConfig {

    }

}
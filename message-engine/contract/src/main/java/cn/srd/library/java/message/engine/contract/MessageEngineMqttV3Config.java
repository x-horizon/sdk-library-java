// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.contract;

import cn.srd.library.java.message.engine.contract.strategy.MessageQosType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wjm
 * @since 2024-05-30 15:36
 */
@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface MessageEngineMqttV3Config {

    MessageQosType qos() default MessageQosType.AT_MOST_ONCE;

    long completionTimeout() default 30000L;

    long disconnectCompletionTimeout() default 5000L;

    ProducerConfig producerConfig() default @ProducerConfig();

    ConsumerConfig consumerConfig() default @ConsumerConfig();

    @interface ProducerConfig {

        boolean needToSendAsync() default false;

    }

    @interface ConsumerConfig {

    }

}
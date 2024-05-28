// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.kafka.foo;

import cn.srd.library.java.message.engine.contract.MessageProducer;
import cn.srd.library.java.message.engine.contract.support.strategy.MessageEngineType;
import cn.srd.library.java.message.engine.contract.support.strategy.MessageQosType;
import org.springframework.stereotype.Component;

/**
 * @author wjm
 * @since 2024-05-27 14:50
 */
@Component
public class FooProducer {

    @MessageProducer(engine = MessageEngineType.MQTT_V3, topic = FooTopicConstant.TOPIC_TEST1, qos = MessageQosType.EXACTLY_ONCE)
    public String send1() {
        return "send1";
    }

    @MessageProducer(engine = MessageEngineType.MQTT_V3, topic = FooTopicConstant.TOPIC_TEST2, qos = MessageQosType.EXACTLY_ONCE)
    public String send2() {
        return "send2";
    }

}
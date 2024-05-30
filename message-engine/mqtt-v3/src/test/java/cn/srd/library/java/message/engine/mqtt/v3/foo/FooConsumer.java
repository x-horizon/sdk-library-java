// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.mqtt.v3.foo;

import cn.srd.library.java.message.engine.contract.MessageConsumer;
import cn.srd.library.java.message.engine.contract.strategy.MessageEngineType;
import cn.srd.library.java.message.engine.contract.strategy.MessageQosType;
import cn.srd.library.java.tool.lang.time.Times;
import org.springframework.stereotype.Component;

/**
 * @author wjm
 * @since 2024-05-26 15:08
 */
@Component
public class FooConsumer {

    @MessageConsumer(engine = MessageEngineType.MQTT_V3, topic = {FooTopicConstant.TOPIC_TEST1, FooTopicConstant.TOPIC_TEST2}, qos = MessageQosType.EXACTLY_ONCE)
    public void receive(String message) {
        System.out.println("消费者1 -------- " + Times.getCurrentDateTime() + "-receive-" + message);
    }

}
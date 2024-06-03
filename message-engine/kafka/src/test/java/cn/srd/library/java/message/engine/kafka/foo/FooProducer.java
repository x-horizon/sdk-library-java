// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.kafka.foo;

import cn.srd.library.java.message.engine.contract.MessageConfig;
import cn.srd.library.java.message.engine.contract.MessageProducer;
import cn.srd.library.java.message.engine.contract.model.enums.MessageEngineType;
import cn.srd.library.java.message.engine.kafka.MessageKafkaConfig;
import cn.srd.library.java.tool.lang.time.Times;
import org.springframework.stereotype.Component;

/**
 * @author wjm
 * @since 2024-05-27 14:50
 */
@Component
public class FooProducer {

    @MessageProducer(
            config = @MessageConfig(engineType = MessageEngineType.KAFKA, kafka = @MessageKafkaConfig),
            topic = FooTopicConstant.TOPIC_TEST1
    )
    public String send1() {
        System.out.println("生产者1 -------- " + Times.getCurrentDateTime() + "-producer-" + "send1");
        return "send1";
    }

    @MessageProducer(
            config = @MessageConfig(engineType = MessageEngineType.KAFKA, kafka = @MessageKafkaConfig),
            topic = FooTopicConstant.TOPIC_TEST2
    )
    public String send2() {
        System.out.println("生产者2 -------- " + Times.getCurrentDateTime() + "-producer-" + "send2");
        return "send2";
    }

}
// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.kafka.foo;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
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
@SuppressWarnings(SuppressWarningConstant.PREVIEW)
@Component
public class FooProducer {

    @MessageProducer(
            topic = FooTopicConstant.TOPIC_TEST1,
            config = @MessageConfig(engineType = MessageEngineType.KAFKA, kafka = @MessageKafkaConfig)
    )
    public String send1() {
        System.out.println(STR."生产者1 -------- \{Times.getCurrentDateTime()}-producer-send1");
        return "send1";
    }

    @MessageProducer(
            topic = FooTopicConstant.TOPIC_TEST2,
            config = @MessageConfig(engineType = MessageEngineType.KAFKA, kafka = @MessageKafkaConfig)
    )
    public String send2() {
        System.out.println(STR."生产者2 -------- \{Times.getCurrentDateTime()}-producer-send2");
        return "send2";
    }

}
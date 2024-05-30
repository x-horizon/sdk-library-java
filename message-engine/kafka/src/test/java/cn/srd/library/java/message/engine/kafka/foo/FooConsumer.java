// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.kafka.foo;

import cn.srd.library.java.message.engine.contract.MessageConsumer;
import cn.srd.library.java.message.engine.contract.MessageEngineConfig;
import cn.srd.library.java.message.engine.contract.MessageEngineKafkaConfig;
import cn.srd.library.java.message.engine.contract.strategy.MessageEngineType;
import cn.srd.library.java.tool.lang.time.Times;
import org.springframework.stereotype.Component;

/**
 * @author wjm
 * @since 2024-05-26 15:08
 */
@Component
public class FooConsumer {

    @MessageConsumer(
            engineType = MessageEngineType.KAFKA,
            engineConfig = @MessageEngineConfig(kafka = @MessageEngineKafkaConfig),
            topic = {FooTopicConstant.TOPIC_TEST1, FooTopicConstant.TOPIC_TEST2}
    )
    public void receive(String message) {
        System.out.println(Times.getCurrentDateTime() + "-receive-" + message);
    }

}
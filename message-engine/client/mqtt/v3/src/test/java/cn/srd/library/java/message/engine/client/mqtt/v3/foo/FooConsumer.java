// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.client.mqtt.v3.foo;

import cn.srd.library.java.contract.constant.suppress.SuppressWarningConstant;
import cn.srd.library.java.message.engine.client.contract.MessageConfig;
import cn.srd.library.java.message.engine.client.contract.MessageConsumer;
import cn.srd.library.java.message.engine.client.contract.model.enums.MessageEngineType;
import cn.srd.library.java.message.engine.client.contract.model.enums.MessageQosType;
import cn.srd.library.java.message.engine.client.mqtt.v3.MqttV3Config;
import cn.srd.library.java.tool.lang.time.Times;
import org.springframework.stereotype.Component;

/**
 * @author wjm
 * @since 2024-05-26 15:08
 */
@SuppressWarnings(SuppressWarningConstant.PREVIEW)
@Component
public class FooConsumer {

    @MessageConsumer(
            topics = {FooTopicConstant.TOPIC_TEST1, FooTopicConstant.TOPIC_TEST2},
            config = @MessageConfig(engineType = MessageEngineType.MQTT_V3, mqttV3 = @MqttV3Config(
                    clientConfig = @MqttV3Config.ClientConfig(qosType = MessageQosType.EXACTLY_ONCE)
            ))
    )
    public void receive(String message) {
        System.out.println(STR."消费者1 -------- \{Times.getCurrentDateTime()}-receive-\{message}");
    }

}
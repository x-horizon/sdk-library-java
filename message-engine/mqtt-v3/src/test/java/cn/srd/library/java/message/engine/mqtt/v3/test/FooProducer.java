// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.mqtt.v3.test;

import cn.srd.library.java.message.engine.contract.MessageEngineType;
import cn.srd.library.java.message.engine.contract.MessageProducer;
import org.springframework.stereotype.Component;

/**
 * @author wjm
 * @since 2024-05-27 14:50
 */
@Component
public class FooProducer {

    @MessageProducer(type = MessageEngineType.MQTT_V3, topic = TopicConstant.TOPIC_TEST1)
    public String send1(String a, String b) {
        return "send1";
    }

    @MessageProducer(type = MessageEngineType.MQTT_V3, topic = TopicConstant.TOPIC_TEST2)
    public String send2(String a, String b) {
        return "send2";
    }

}
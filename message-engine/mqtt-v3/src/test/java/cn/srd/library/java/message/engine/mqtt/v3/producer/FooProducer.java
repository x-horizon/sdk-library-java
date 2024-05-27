// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.mqtt.v3.producer;

import cn.srd.library.java.message.engine.contract.MessageEngineType;
import cn.srd.library.java.message.engine.contract.MessageSend;
import org.springframework.stereotype.Component;

/**
 * @author wjm
 * @since 2024-05-27 14:50
 */
@Component
public class FooProducer {

    @MessageSend(type = MessageEngineType.MQTT_V3)
    public String send(String a, String b) {
        return "foo";
    }

}
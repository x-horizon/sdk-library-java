// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.mqtt.v3.test;

import cn.srd.library.java.message.engine.mqtt.v3.autoconfigure.EnableMessageEngineMqttV3;
import cn.srd.library.java.message.engine.mqtt.v3.foo.FooProducer;
import cn.srd.library.java.tool.enums.autowired.EnableEnumAutowired;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.test.context.SpringIntegrationTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.TimeUnit;

/**
 * mqtt test
 *
 * @author wjm
 * @since 2024-05-21 21:55
 */
@EnableEnumAutowired
@EnableMessageEngineMqttV3
@ExtendWith(SpringExtension.class)
@SpringBootTest
@SpringIntegrationTest
class MqttTest {

    @Autowired private FooProducer fooProducer;

    @SneakyThrows
    @Test
    void test() {
        while (true) {
            // fooProducer.send1();
            // fooProducer.send2();
            TimeUnit.SECONDS.sleep(1);
        }
    }

}
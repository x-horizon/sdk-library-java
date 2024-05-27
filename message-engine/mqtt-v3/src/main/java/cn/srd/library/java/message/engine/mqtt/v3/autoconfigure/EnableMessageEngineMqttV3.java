// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.mqtt.v3.autoconfigure;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author wjm
 * @see MessageEngineMqttV3Switcher
 * @see MessageEngineMqttV3AutoConfigurer#mqttClientFactory()
 * @since 2024-05-24 16:54
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MessageEngineMqttV3Switcher.class)
public @interface EnableMessageEngineMqttV3 {

}
// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.mqtt.v3.autoconfigure;

import cn.srd.library.java.message.engine.contract.strategy.ClientIdGenerateType;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * provide an annotation to enable message engine mqtt-v3 system.
 *
 * @author wjm
 * @see MessageEngineMqttV3Switcher
 * @see MessageEngineMqttV3AutoConfigurer#messageEngineMqttV3Customizer()
 * @since 2024-05-24 16:54
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MessageEngineMqttV3Switcher.class)
public @interface EnableMessageEngineMqttV3 {

    ClientIdGenerateType clientIdGenerateType() default ClientIdGenerateType.UUID; // TODO wjm 此处实现不够好，与 snowflake id 强绑定，客户端不一定需要用到 snowflake id，目前客户端必须提供正确的 redis 配置，否则项目启动报错

}
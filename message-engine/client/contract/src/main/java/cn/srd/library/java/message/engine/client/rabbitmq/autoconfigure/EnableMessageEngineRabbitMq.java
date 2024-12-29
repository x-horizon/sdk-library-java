// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.client.rabbitmq.autoconfigure;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * provide an annotation to enable message engine rabbitmq system.
 *
 * @author wjm
 * @see MessageEngineRabbitMqRegistrar
 * @since 2024-05-24 16:54
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MessageEngineRabbitMqRegistrar.class)
public @interface EnableMessageEngineRabbitMq {

}
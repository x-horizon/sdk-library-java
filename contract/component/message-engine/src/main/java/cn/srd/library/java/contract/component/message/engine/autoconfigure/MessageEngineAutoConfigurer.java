// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.contract.component.message.engine.autoconfigure;

import cn.srd.library.java.contract.component.message.engine.MessageSendAspect;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Message Engine
 *
 * @author wjm
 * @since 2024-05-24 16:56
 */
@AutoConfiguration
public class MessageEngineAutoConfigurer {

    @Bean
    public MessageSendAspect messageSendAspect() {
        return new MessageSendAspect();
    }

}
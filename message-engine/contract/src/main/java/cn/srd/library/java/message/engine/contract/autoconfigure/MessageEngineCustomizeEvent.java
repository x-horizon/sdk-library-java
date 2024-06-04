// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.contract.autoconfigure;

import cn.srd.library.java.message.engine.contract.model.enums.MessageEngineType;
import cn.srd.library.java.tool.spring.contract.Springs;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;

import java.util.Arrays;

/**
 * @author wjm
 * @since 2024-06-04 15:32
 */
public class MessageEngineCustomizeEvent implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(@NonNull ApplicationReadyEvent event) {
        Arrays.stream(MessageEngineType.values())
                .filter(messageEngineType -> Springs.existBean(messageEngineType.getSystemSwitcher()))
                .forEach(messageEngineType -> messageEngineType.getConfigStrategy().customize());
    }

}
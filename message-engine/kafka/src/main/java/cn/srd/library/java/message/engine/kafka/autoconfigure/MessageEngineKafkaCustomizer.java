// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.kafka.autoconfigure;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.message.engine.contract.strategy.UniqueClientIdGenerateType;
import cn.srd.library.java.tool.spring.contract.Annotations;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * the global message engine kafka customizer
 *
 * @author wjm
 * @since 2024-05-30 11:13
 */
@Slf4j
@Getter
public class MessageEngineKafkaCustomizer {

    private UniqueClientIdGenerateType uniqueClientIdGenerateType;

    @PostConstruct
    public void initialize() {
        EnableMessageEngineKafka kafkaCustomizer = Annotations.getAnnotation(EnableMessageEngineKafka.class);

        log.debug("{}message engine kafka customizer is enabled, starting initializing...", ModuleView.MESSAGE_ENGINE_SYSTEM);

        this.uniqueClientIdGenerateType = kafkaCustomizer.uniqueClientIdGenerateType();

        log.debug(""" 
                        {}message engine kafka customizer has loaded the following configurations:
                        --------------------------------------------------------------------------------------------------------------------------------
                        Unique Client Id Generate Config:
                           generateType                            = [{}]
                        --------------------------------------------------------------------------------------------------------------------------------""",
                ModuleView.MESSAGE_ENGINE_SYSTEM,
                kafkaCustomizer.uniqueClientIdGenerateType().name()
        );

        log.debug("{}message engine kafka customizer initialized.", ModuleView.MESSAGE_ENGINE_SYSTEM);
    }

}
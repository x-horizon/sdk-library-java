// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.kafka.properties;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * properties for message engine mqtt
 *
 * @author wjm
 * @since 2024-05-25 16:35
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "library.java.message-engine.kafka")
public class MessageEngineKafkaProperties {

    @Getter private static MessageEngineKafkaProperties instance = null;

    @PostConstruct
    public void initialize() {
        instance = this;
    }

    private String username;

    private String password;

    private List<String> serverURLs;

}
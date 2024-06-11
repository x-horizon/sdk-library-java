// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.mqtt.contract.support;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author wjm
 * @since 2024-06-11 16:38
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageMqttDefaultConfigConstant {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Client {

        public static final String COMPLETION_TIMEOUT = "30s";

        public static final String DISCONNECT_COMPLETION_TIMEOUT = "5s";

    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Producer {

        public static final boolean NEED_TO_SEND_ASYNC = false;

    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Consumer {

    }

}
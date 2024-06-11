// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.kafka.support;

import cn.srd.library.java.contract.constant.text.SymbolConstant;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author wjm
 * @since 2024-06-11 16:38
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageKafkaDefaultConfigConstant {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Client {

    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Producer {

    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Consumer {

        public static final String GROUP_ID = SymbolConstant.EMPTY;

        public static final boolean ALLOW_TO_AUTO_CREATE_TOPIC = true;

        public static final String AUTO_COMMIT_OFFSET_INTERVAL = "5s";

    }

}
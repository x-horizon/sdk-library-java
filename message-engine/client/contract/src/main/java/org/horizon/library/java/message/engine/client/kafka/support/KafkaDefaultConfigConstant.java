package org.horizon.library.java.message.engine.client.kafka.support;

import org.horizon.library.java.contract.constant.text.SymbolConstant;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author wjm
 * @since 2024-06-11 16:38
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KafkaDefaultConfigConstant {

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
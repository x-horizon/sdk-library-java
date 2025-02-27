package org.horizon.sdk.library.java.message.engine.server.mqtt.constant;

import io.netty.util.AttributeKey;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.net.InetSocketAddress;

/**
 * @author wjm
 * @since 2025-01-05 21:15
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NettyMqttConstant {

    public static final AttributeKey<InetSocketAddress> SOURCE_ADDRESS = AttributeKey.newInstance("SRC_ADDRESS");

}
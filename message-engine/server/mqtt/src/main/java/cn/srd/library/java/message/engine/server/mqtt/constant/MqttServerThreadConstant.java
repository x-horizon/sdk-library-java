package cn.srd.library.java.message.engine.server.mqtt.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author wjm
 * @since 2025-01-06 22:39
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MqttServerThreadConstant {

    public static final String SINGLE_THREAD_SCHEDULER = "mqtt-server-single-thread-scheduler-";

    public static final String MESSAGE_CALLBACK_EXECUTOR = "message-callback-executor-";

}
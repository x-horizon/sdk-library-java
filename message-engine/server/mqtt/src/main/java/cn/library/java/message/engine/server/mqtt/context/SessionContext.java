package cn.library.java.message.engine.server.mqtt.context;

import java.util.UUID;

/**
 * @author wjm
 * @since 2025-01-05 22:14
 */
public interface SessionContext {

    UUID getSessionId();

}
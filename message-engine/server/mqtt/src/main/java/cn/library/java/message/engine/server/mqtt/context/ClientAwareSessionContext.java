package cn.library.java.message.engine.server.mqtt.context;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

/**
 * @author wjm
 * @since 2025-01-05 22:15
 */
@RequiredArgsConstructor
public abstract class ClientAwareSessionContext implements SessionContext {

    @Getter private final UUID sessionId;

    private volatile boolean isConnected;

    public void connect() {
        this.isConnected = true;
    }

    public void disconnect() {
        if (this.isConnected) {
            this.isConnected = false;
        }
    }

    public boolean isConnected() {
        return isConnected;
    }

    public boolean isDisconnected() {
        return !isConnected();
    }

}
package org.horizon.sdk.library.java.message.engine.server.mqtt.handler;

import org.horizon.sdk.library.java.message.engine.server.mqtt.model.dto.ClientConnectAuthRequestDTO;
import org.horizon.sdk.library.java.message.engine.server.mqtt.model.dto.ClientConnectAuthResponseDTO;

/**
 * @author wjm
 * @since 2025-01-06 22:18
 */
public interface ClientConnectAuthHandler {

    ClientConnectAuthResponseDTO process(ClientConnectAuthRequestDTO requestDTO);

}
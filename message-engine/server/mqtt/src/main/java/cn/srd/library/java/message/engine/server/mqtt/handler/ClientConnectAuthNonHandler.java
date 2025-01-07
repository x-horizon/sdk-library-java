package cn.srd.library.java.message.engine.server.mqtt.handler;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.message.engine.server.mqtt.model.dto.ClientConnectAuthRequestDTO;
import cn.srd.library.java.message.engine.server.mqtt.model.dto.ClientConnectAuthResponseDTO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wjm
 * @since 2025-01-06 22:19
 */
@Slf4j
public class ClientConnectAuthNonHandler implements ClientConnectAuthHandler {

    @Override
    public ClientConnectAuthResponseDTO process(ClientConnectAuthRequestDTO requestDTO) {
        log.trace("{}[clientId:{}] - [username:{}] - [password:{}] - non auth client connect message and success directly.", ModuleView.MESSAGE_ENGINE_MQTT_SERVER_SYSTEM, requestDTO.getClientId(), requestDTO.getUsername(), requestDTO.getPassword());
        return new ClientConnectAuthResponseDTO();
    }

}
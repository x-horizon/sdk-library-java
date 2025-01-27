package cn.library.java.message.engine.server.mqtt.manager.handler;

import cn.library.java.contract.constant.module.ModuleView;
import cn.library.java.message.engine.server.mqtt.handler.ClientConnectAuthHandler;
import cn.library.java.message.engine.server.mqtt.model.dto.ClientConnectAuthRequestDTO;
import cn.library.java.message.engine.server.mqtt.model.dto.ClientConnectAuthResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author wjm
 * @since 2025-01-07 18:45
 */
@Slf4j
@Component
public class DeviceConnectAuthByTokenHandler implements ClientConnectAuthHandler {

    @Override
    public ClientConnectAuthResponseDTO process(ClientConnectAuthRequestDTO requestDTO) {
        log.trace("{}[clientId:{}] - [username:{}] - [password:{}] - token auth client connect message and success directly.", ModuleView.MESSAGE_ENGINE_MQTT_SERVER_SYSTEM, requestDTO.getClientId(), requestDTO.getUsername(), requestDTO.getPassword());
        return new ClientConnectAuthResponseDTO();
    }

}
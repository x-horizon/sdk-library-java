package cn.srd.library.java.message.engine.server.mqtt.strategy;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.message.engine.server.mqtt.model.dto.ClientConnectAuthRequestDTO;
import cn.srd.library.java.message.engine.server.mqtt.model.dto.ClientConnectAuthResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author wjm
 * @since 2025-01-07 18:45
 */
@Slf4j
@Component
public class ClientConnectTokenAuthStrategy implements ClientConnectAuthStrategy {

    @Override
    public ClientConnectAuthResponseDTO process(ClientConnectAuthRequestDTO requestDTO) {
        log.trace("{}[clientId:{}] - [username:{}] - [password:{}] - token auth and success directly.", ModuleView.MESSAGE_ENGINE_MQTT_SERVER_SYSTEM, requestDTO.getClientId(), requestDTO.getUsername(), requestDTO.getPassword());
        return new ClientConnectAuthResponseDTO();
    }

}
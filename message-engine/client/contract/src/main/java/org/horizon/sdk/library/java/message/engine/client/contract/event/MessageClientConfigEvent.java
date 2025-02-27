package org.horizon.sdk.library.java.message.engine.client.contract.event;

import org.horizon.sdk.library.java.message.engine.client.contract.model.enums.MessageClientType;
import org.horizon.sdk.library.java.tool.spring.contract.support.Springs;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.List;

/**
 * @author wjm
 * @since 2024-06-04 15:32
 */
public class MessageClientConfigEvent implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(@NonNull ApplicationReadyEvent event) {
        List<MessageClientType> enableEngineTypes = Arrays.stream(MessageClientType.values())
                .filter(messageEngineType -> Springs.existBean(messageEngineType.getSystemSwitcher()))
                .toList();
        enableEngineTypes.forEach(enableEngineType -> enableEngineType.getConfigStrategy().initialize(enableEngineType));
        enableEngineTypes.forEach(enableEngineType -> enableEngineType.getConfigStrategy().onInitializeComplete(enableEngineType));
    }

}
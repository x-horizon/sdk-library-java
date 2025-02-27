package org.horizon.sdk.library.java.message.engine.client.contract.model.property;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author wjm
 * @since 2024-05-29 22:36
 */
@Getter
@Setter
public class MessageClientProperty {

    private List<String> serverUrls;

}
package org.horizon.sdk.library.java.message.engine.client.contract.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wjm
 * @since 2024-05-25 16:02
 */
@Getter
@AllArgsConstructor
public enum MessageQualityOfServiceType {

    AT_MOST_ONCE(0),
    AT_LEAST_ONCE(1),
    EXACTLY_ONCE(2),

    ;

    private final int code;

}
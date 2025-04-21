package org.horizon.sdk.library.java.tool.lang.validation.violation;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wjm
 * @since 2025-04-19 01:40
 */
@Getter
@AllArgsConstructor
public class Violation {

    private String fieldName;

    private String message;

}
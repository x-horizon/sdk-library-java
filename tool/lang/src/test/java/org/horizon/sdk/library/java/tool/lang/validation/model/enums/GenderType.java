package org.horizon.sdk.library.java.tool.lang.validation.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GenderType {

    MAN(1, "男"),
    WOMAN(2, "女"),
    UNKNOWN(3, "未知"),

    ;

    private final int code;

    private final String description;

}
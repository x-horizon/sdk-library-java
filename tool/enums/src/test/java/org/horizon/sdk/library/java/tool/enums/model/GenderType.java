package org.horizon.sdk.library.java.tool.enums.model;

import lombok.Getter;
import org.horizon.sdk.library.java.tool.enums.EnumAutowired;

@Getter
@EnumAutowired(rootClasses = GenderStrategy.class)
public enum GenderType {

    WOMAN(1, "woman"),
    MAN(2, "man"),
    UNKNOWN(3, "unknown"),

    ;

    GenderType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    private final int code;

    private final String description;

    private GenderStrategy strategy;

}
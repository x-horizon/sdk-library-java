package org.horizon.library.java.tool.enums.model;

import org.horizon.library.java.tool.enums.EnumAutowired;
import lombok.Getter;

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
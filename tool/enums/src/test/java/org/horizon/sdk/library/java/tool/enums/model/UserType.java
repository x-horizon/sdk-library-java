package org.horizon.sdk.library.java.tool.enums.model;

import lombok.Getter;
import org.horizon.sdk.library.java.tool.enums.EnumAutowired;

@Getter
@EnumAutowired(rootClasses = UserStrategy.class)
public enum UserType {

    STUDENT(1, "student"),
    TEACHER(2, "teacher"),

    ;

    UserType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    private final int code;

    private final String description;

    private UserStrategy strategy;

}
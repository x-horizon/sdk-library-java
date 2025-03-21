package org.horizon.sdk.library.java.tool.convert.jackson.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FoolType {

    FOOL1(1, 10000000000000L, "fool_1"),
    FOOL2(2, 10000000000001L, "fool_2"),
    FOOL3(3, 10000000000002L, "fool_3"),

    ;

    private final int intCode;

    private final Long longCode;

    private final String stringCode;

}
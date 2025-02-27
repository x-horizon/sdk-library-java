package org.horizon.sdk.library.java.contract.constant.storage;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wjm
 * @since 2024-07-19 15:45
 */
@Getter
@AllArgsConstructor
public enum StorageUnitType {

    BIT("Bit"),
    BYTE("Byte"),
    KB("KB"),
    MB("MB"),
    GB("GB"),
    TB("TB"),

    ;

    private final String value;

}
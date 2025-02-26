package org.horizon.library.java.contract.constant.time;

import lombok.Getter;

/**
 * time zone offset type
 *
 * @author wjm
 * @since 2024-07-09 10:02
 */
@Getter
public enum TimeZoneOffsetType {

    EAST_EIGHTH("+08:00"),

    ;

    TimeZoneOffsetType(String name) {
        this.value = name;
    }

    private final String value;

}
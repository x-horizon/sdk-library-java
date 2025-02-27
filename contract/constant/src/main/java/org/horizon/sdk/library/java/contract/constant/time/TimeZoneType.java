package org.horizon.sdk.library.java.contract.constant.time;

import lombok.Getter;

/**
 * time zone type
 *
 * @author wjm
 * @since 2022-11-14 21:16
 */
@Getter
public enum TimeZoneType {

    SHANG_HAI("Asia/Shanghai"),
    UTC("UTC"),

    ;

    TimeZoneType(String name) {
        this.value = name;
    }

    private final String value;

}
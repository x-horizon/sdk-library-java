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

    BIT(8, "Bit"),
    BYTE(1, "Byte"),
    KB(1024, "KB"),
    MB(1024 * 1024, "MB"),
    GB(1024 * 1024 * 1024, "GB"),
    TB(1024L * 1024 * 1024 * 1024, "TB"),

    ;

    private final long byteFactor;

    private final String description;

    public double toSize(long bytes) {
        return BIT == this ? bytes * this.byteFactor : (double) bytes / this.byteFactor;
    }

}
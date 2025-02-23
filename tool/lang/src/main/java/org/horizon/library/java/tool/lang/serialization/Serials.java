package org.horizon.library.java.tool.lang.serialization;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.ObjectStreamClass;

/**
 * toolkit for serial
 *
 * @author wjm
 * @since 2023-11-04 01:36
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Serials {

    /**
     * get serial version uid in a class implement {@link java.io.Serializable Serializable}
     *
     * @param input the class implement {@link java.io.Serializable Serializable}
     * @return serial version uid
     */
    public static long getSerialVersionUID(Class<?> input) {
        return ObjectStreamClass.lookup(input).getSerialVersionUID();
    }

}
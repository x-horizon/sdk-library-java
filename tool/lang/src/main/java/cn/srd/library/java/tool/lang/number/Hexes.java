package cn.srd.library.java.tool.lang.number;

import cn.hutool.core.util.HexUtil;
import cn.srd.library.java.tool.lang.collection.Collections;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * toolkit for hex
 *
 * @author wjm
 * @since 2022-07-06 11:28
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hexes {

    /**
     * convert byte array to hex string
     *
     * @param inputs the input elements
     * @return hex string
     */
    public static String toString(Byte[] inputs) {
        return toString(Collections.unWrap(inputs));
    }

    /**
     * convert byte array to hex string
     *
     * @param inputs the input elements
     * @return hex string
     */
    public static String toString(byte[] inputs) {
        return toString(inputs, false);
    }

    /**
     * convert byte array to hex string
     *
     * @param inputs        the input elements
     * @param needLowercase need convert to lowercase
     * @return hex string
     */
    public static String toString(Byte[] inputs, boolean needLowercase) {
        return toString(Collections.unWrap(inputs), needLowercase);
    }

    /**
     * convert byte array to hex string
     *
     * @param inputs        the input elements
     * @param needLowercase need convert to lowercase
     * @return hex string
     */
    public static String toString(byte[] inputs, boolean needLowercase) {
        return HexUtil.encodeHexStr(inputs, needLowercase);
    }

}
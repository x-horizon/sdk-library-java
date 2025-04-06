package org.horizon.sdk.library.java.tool.lang.convert;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * @author wjm
 * @since 2025-04-03 23:36
 */
public class ConvertsTest {

    @Getter
    @AllArgsConstructor
    public enum GenderType1 {
        MAN(1, "man"),
        WOMAN(2, "woman"),
        UNKNOWN(3, "unknown");

        private final int code;

        private final String description;

        public static void main(String[] args) {
            Converts.toEnumByValue("woman", GenderType1.class);
            Converts.toEnumByValue("WOman", GenderType1.class);
            Converts.toEnumByValue(2, GenderType1.class);
            Converts.toEnumByValue("2", GenderType1.class);
            Converts.toEnumByValueMostSimilar("man", GenderType1.class);
            Converts.toEnumByValueMostSimilar("Man", GenderType1.class);
            Converts.toEnumByValueMostSimilar("woman", GenderType1.class);
            Converts.toEnumByValueMostSimilar("WOman", GenderType1.class);
            Converts.toEnumByValueMostSimilar("2", GenderType1.class);
            String a = "";
        }
    }

    @Getter
    public enum GenderType2 {
        MAN("man"),
        WOMAN("woman", "Woman", "WOMAN"),
        UNKNOWN("unknown", "Unknown");

        GenderType2(String... names) {
            this.names = names;
        }

        private final String[] names;

        public static void main(String[] args) {
            Converts.toEnumByValue("woman", GenderType2.class);
            Converts.toEnumByValue("Woman", GenderType2.class);
            Converts.toEnumByValue("WOMAN", GenderType2.class);
            Converts.toEnumByValue("WOman", GenderType2.class);
            Converts.toEnumByValueMostSimilar("woman", GenderType2.class);
            Converts.toEnumByValueMostSimilar("Woman", GenderType2.class);
            Converts.toEnumByValueMostSimilar("WOMAN", GenderType2.class);
            Converts.toEnumByValueMostSimilar("WOman", GenderType2.class);
            String a = "";
        }
    }

    @Getter
    public enum GenderType3 {
        MAN(1, List.of(4, 5, 6), List.of("m"), "man"),
        WOMAN(2, List.of(7, 8, 9), List.of("wo", "Wo", "WO"), "woman", "Woman", "WOMAN"),
        UNKNOWN(3, List.of(10, 11, 12), List.of("un", "Un"), "unknown", "Unknown");

        GenderType3(int code, List<Integer> codes, List<String> names1, String... names2) {
            this.code = code;
            this.codes = codes;
            this.names1 = names1;
            this.names2 = names2;
        }

        private final int code;

        private final List<Integer> codes;

        private final List<String> names1;

        private final String[] names2;

        public static void main(String[] args) {
            Converts.toEnumByValue("woman", GenderType3.class);
            Converts.toEnumByValue("Woman", GenderType3.class);
            Converts.toEnumByValue("WOMAN", GenderType3.class);
            Converts.toEnumByValue("WOman", GenderType3.class);
            Converts.toEnumByValue("m", GenderType3.class);
            Converts.toEnumByValue("Wo", GenderType3.class);
            Converts.toEnumByValue("Un", GenderType3.class);
            Converts.toEnumByValue("UM", GenderType3.class);
            Converts.toEnumByValue(2, GenderType3.class);
            Converts.toEnumByValue(11, GenderType3.class);
            Converts.toEnumByValue("2", GenderType3.class);
            Converts.toEnumByValueMostSimilar("woman", GenderType3.class);
            Converts.toEnumByValueMostSimilar("Woman", GenderType3.class);
            Converts.toEnumByValueMostSimilar("WOMAN", GenderType3.class);
            Converts.toEnumByValueMostSimilar("WOman", GenderType3.class);
            Converts.toEnumByValueMostSimilar("2", GenderType3.class);
            String a = "";
        }
    }

    public enum GenderType4 {
        MAN,
        WOMAN,
        UNKNOWN;

        public static void main(String[] args) {
            Converts.toEnumByValue("WOMAN", GenderType4.class);
            Converts.toEnumByValueMostSimilar("WOMAN", GenderType4.class);
            String a = "";
        }
    }

    public static void main(String[] args) {

    }

}
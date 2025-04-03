package org.horizon.sdk.library.java.tool.lang;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.horizon.sdk.library.java.tool.lang.convert.Converts;

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
            Converts.toEnumByValue("2", GenderType1.class);
            Converts.toEnumByValueContainIgnoreCase("man", GenderType1.class);
            Converts.toEnumByValueContainIgnoreCase("Man", GenderType1.class);
            Converts.toEnumByValueContainIgnoreCase("woman", GenderType1.class);
            Converts.toEnumByValueContainIgnoreCase("WOman", GenderType1.class);
            Converts.toEnumByValueContainIgnoreCase("2", GenderType1.class);
            String a = "";
        }
    }

    @Getter
    @AllArgsConstructor
    public enum GenderType2 {
        MAN(1, "man", "Man"),
        WOMAN(2, "woman", "Woman"),
        UNKNOWN(3, "unknown", "Unknown");

        private final int code;

        private final String description1;

        private final String description2;

        public static void main(String[] args) {
            Converts.toEnumByValue("woman", GenderType2.class);
            Converts.toEnumByValue("Woman", GenderType2.class);
            Converts.toEnumByValue("WOman", GenderType2.class);
            Converts.toEnumByValue("2", GenderType1.class);
            Converts.toEnumByValueContainIgnoreCase("woman", GenderType2.class);
            Converts.toEnumByValueContainIgnoreCase("Woman", GenderType2.class);
            Converts.toEnumByValueContainIgnoreCase("WOman", GenderType2.class);
            Converts.toEnumByValueContainIgnoreCase("2", GenderType1.class);
            String a = "";
        }
    }

    @Getter
    public enum GenderType3 {
        MAN("man"),
        WOMAN("woman", "Woman", "WOMAN"),
        UNKNOWN("unknown", "Unknown");

        GenderType3(String... names) {
            this.names = names;
        }

        private final String[] names;

        public static void main(String[] args) {
            Converts.toEnumByValue("woman", GenderType3.class);
            Converts.toEnumByValue("Woman", GenderType3.class);
            Converts.toEnumByValue("WOMAN", GenderType3.class);
            Converts.toEnumByValue("WOman", GenderType3.class);
            Converts.toEnumByValueContainIgnoreCase("woman", GenderType3.class);
            Converts.toEnumByValueContainIgnoreCase("Woman", GenderType3.class);
            Converts.toEnumByValueContainIgnoreCase("WOMAN", GenderType3.class);
            Converts.toEnumByValueContainIgnoreCase("WOman", GenderType3.class);
            String a = "";
        }
    }

    public enum GenderType4 {
        MAN,
        WOMAN,
        UNKNOWN;

        public static void main(String[] args) {
            Converts.toEnumByValue("WOMAN", GenderType4.class);
            Converts.toEnumByValueContainIgnoreCase("WOMAN", GenderType4.class);
            String a = "";
        }
    }

    public static void main(String[] args) {

    }

}
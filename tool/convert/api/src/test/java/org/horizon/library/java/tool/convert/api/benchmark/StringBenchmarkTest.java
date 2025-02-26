package org.horizon.library.java.tool.convert.api.benchmark;

import org.horizon.library.java.contract.constant.text.SymbolConstant;
import org.horizon.library.java.tool.lang.text.Strings;
import org.dromara.hutool.core.text.StrJoiner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class StringBenchmarkTest {

    private static final int DATA_SIZE = 10000000;

    private static final String CONCAT_STR_1 = "concatStr1";

    private static final String CONCAT_STR_2 = "concatStr2";

    /**
     * <pre>
     * 字符串拼接考虑三种方式：
     * 1、使用 + 拼接，编码不友好，性能最快；
     * 2、使用 {@link Strings#format(CharSequence, Object...)} 拼接，编码友好，性能最慢，不适合此处；
     * 3、使用 {@link StrJoiner#append(char)} 拼接，编码较友好，性能略逊于第一种方式；
     * </pre>
     */

    @Test
    void test() {
        // 预热
        for (int i = 0; i < DATA_SIZE; i++) {
            String b = CONCAT_STR_1 + SymbolConstant.SLASH + CONCAT_STR_2;
        }

        // 开始
        Instant now1 = Instant.now();
        for (int i = 0; i < DATA_SIZE; i++) {
            String b = Strings.format("{}/{}", CONCAT_STR_1, CONCAT_STR_2);
        }
        System.out.println("Strings.format, " + DATA_SIZE + "条数据，耗时：" + ChronoUnit.MILLIS.between(now1, Instant.now()) + "ms");

        Instant now2 = Instant.now();
        for (int i = 0; i < DATA_SIZE; i++) {
            String b = CONCAT_STR_1 + SymbolConstant.SLASH + CONCAT_STR_2;
        }
        System.out.println("+, " + DATA_SIZE + "条数据，耗时：" + ChronoUnit.MILLIS.between(now2, Instant.now()) + "ms");

        Instant now3 = Instant.now();
        for (int i = 0; i < DATA_SIZE; i++) {
            String b = new StrJoiner(null).append(CONCAT_STR_1).append("/").append(CONCAT_STR_2).toString();
        }
        System.out.println("StrBuilder.append, " + DATA_SIZE + "条数据，耗时：" + ChronoUnit.MILLIS.between(now3, Instant.now()) + "ms");

        Instant now4 = Instant.now();
        for (int i = 0; i < DATA_SIZE; i++) {
            String b = CONCAT_STR_1.concat("/").concat(CONCAT_STR_2);
        }
        System.out.println("String.concat, " + DATA_SIZE + "条数据，耗时：" + ChronoUnit.MILLIS.between(now4, Instant.now()) + "ms");
    }

}
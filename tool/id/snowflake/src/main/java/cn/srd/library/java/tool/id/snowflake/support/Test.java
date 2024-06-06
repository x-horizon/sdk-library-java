// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.id.snowflake.support;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author wjm
 * @since 2024-06-06 20:21
 */
public class Test {

    interface TestInterface {

    }

    @Data
    @Accessors(chain = true)
    static class TestImpl1 implements TestInterface {

        public int a = 1;

    }

    @Data
    @Accessors(chain = true)
    static class TestImpl2 implements TestInterface {

        public int b = 2;

        private TestInterface my;

    }

    public static void test(TestInterface myTest) {
        System.out.println(myTest);
    }

    public static void main(String[] args) {
        test(new TestImpl1());
        test(new TestImpl2().setMy(new TestImpl1().setA(3)));
    }

}
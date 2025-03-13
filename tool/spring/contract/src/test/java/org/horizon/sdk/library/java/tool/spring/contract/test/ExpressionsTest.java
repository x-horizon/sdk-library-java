package org.horizon.sdk.library.java.tool.spring.contract.test;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.horizon.sdk.library.java.tool.lang.reflect.Reflects;
import org.horizon.sdk.library.java.tool.spring.contract.support.Expressions;
import org.horizon.sdk.library.java.tool.spring.contract.support.StandardEvaluationContextBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ExpressionsTest {

    @Data
    @SuperBuilder(toBuilder = true)
    @Accessors(chain = true)
    static class User {

        private Integer id;

        private String name;

        private String email;

        private Integer age;

        private Boolean adminIs;

    }

    @Data
    @SuperBuilder(toBuilder = true)
    @Accessors(chain = true)
    static class Class {

        private Integer id;

        private String name;

        private Boolean activeIs;

    }

    private static final User USER = User.builder()
            .id(1)
            .name("testUser")
            .email("testUser@library.cn")
            .age(18)
            .adminIs(false)
            .build();

    private static final Class CLASS = Class.builder()
            .id(1)
            .name("testClass")
            .activeIs(true)
            .build();

    private static final StandardEvaluationContext CONTEXT1 = StandardEvaluationContextBuilder.builder()
            .setVariable("user", USER)
            .setVariable("class", CLASS)
            .build();

    static class CustomFunction {

        public static boolean isValid(String url) {
            return true;
        }

    }

    private static final StandardEvaluationContext CONTEXT2 = StandardEvaluationContextBuilder.builder()
            .registerFunction("isUrlValid", Reflects.getMethod(CustomFunction.class, "isValid", String.class))
            .build();

    @Test
    void testExpression() {
        // 文本操作
        String result1 = Expressions.getInstance().parse("'hello world'", String.class);
        String result2 = Expressions.getInstance().parse("'hello world'.concat('!')", String.class);
        String result3 = Expressions.getInstance().parse("'hello world'.split(' ')[0]", String.class);
        Integer result4 = Expressions.getInstance().parse("'hello world'.length()", Integer.class);

        // 对象操作
        String result5 = Expressions.getInstance().parse(USER, "name", String.class);
        Integer result6 = Expressions.getInstance().parse(USER, "age", Integer.class);
        Boolean result7 = Expressions.getInstance().parse(USER, "adminIs", Boolean.class);
        Boolean result8 = Expressions.getInstance().parse(USER, "getAdminIs()", Boolean.class);
        Boolean result9 = Expressions.getInstance().parse(USER, "adminIs==true", Boolean.class);
        Boolean result10 = Expressions.getInstance().parse(USER, "getAdminIs()==true", Boolean.class);
        String result11 = Expressions.getInstance().parse(USER, "email.split('@')[0]", String.class);

        // 多对象操作
        Boolean result12 = Expressions.getInstance().parse(CONTEXT1, "#user.adminIs", Boolean.class);
        Boolean result13 = Expressions.getInstance().parse(CONTEXT1, "#user.getAdminIs()", Boolean.class);
        Boolean result14 = Expressions.getInstance().parse(CONTEXT1, "#class.activeIs", Boolean.class);

        // 关系比较操作：==, !=, <, <=, >, >=
        // 逻辑操作： and, or, not
        // 算术操作： +, -, /, *, %, ^
        Boolean result15 = Expressions.getInstance().parse(CONTEXT1, "#user.age == 18", Boolean.class);
        Boolean result16 = Expressions.getInstance().parse(CONTEXT1, "#user.age >= 18", Boolean.class);
        Boolean result17 = Expressions.getInstance().parse(CONTEXT1, "#user.age < 18", Boolean.class);
        Boolean result18 = Expressions.getInstance().parse(CONTEXT1, "#user.getAdminIs() and #class.getActiveIs()", Boolean.class);

        // 自定义函数操作
        Boolean result19 = Expressions.getInstance().parse(CONTEXT2, "#isUrlValid('http://google.com')", Boolean.class);
    }

}
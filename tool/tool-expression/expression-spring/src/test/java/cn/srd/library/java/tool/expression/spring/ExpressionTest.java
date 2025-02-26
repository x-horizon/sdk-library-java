package cn.srd.library.java.tool.expression.spring;

import cn.srd.library.java.tool.expression.spring.core.SpringExpressions;
import cn.srd.library.java.tool.expression.spring.core.StandardEvaluationContextBuilder;
import cn.srd.library.java.tool.lang.core.ClassesUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExpressionTest {

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
            .email("testUser@srd.cn")
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
            .registerFunction("isUrlValid", ClassesUtil.getDeclaredMethod(CustomFunction.class, "isValid", String.class))
            .build();

    @Test
    public void testExpression() {
        // 文本操作
        String result1 = SpringExpressions.getInstance().parse("'hello world'", String.class);
        String result2 = SpringExpressions.getInstance().parse("'hello world'.concat('!')", String.class);
        String result3 = SpringExpressions.getInstance().parse("'hello world'.split(' ')[0]", String.class);
        Integer result4 = SpringExpressions.getInstance().parse("'hello world'.length()", Integer.class);

        // 对象操作
        String result5 = SpringExpressions.getInstance().parse(USER, "name", String.class);
        Integer result6 = SpringExpressions.getInstance().parse(USER, "age", Integer.class);
        Boolean result7 = SpringExpressions.getInstance().parse(USER, "adminIs", Boolean.class);
        Boolean result8 = SpringExpressions.getInstance().parse(USER, "getAdminIs()", Boolean.class);
        Boolean result9 = SpringExpressions.getInstance().parse(USER, "adminIs==true", Boolean.class);
        Boolean result10 = SpringExpressions.getInstance().parse(USER, "getAdminIs()==true", Boolean.class);
        String result11 = SpringExpressions.getInstance().parse(USER, "email.split('@')[0]", String.class);

        // 多对象操作
        Boolean result12 = SpringExpressions.getInstance().parse(CONTEXT1, "#user.adminIs", Boolean.class);
        Boolean result13 = SpringExpressions.getInstance().parse(CONTEXT1, "#user.getAdminIs()", Boolean.class);
        Boolean result14 = SpringExpressions.getInstance().parse(CONTEXT1, "#class.activeIs", Boolean.class);

        // 关系比较操作：==, !=, <, <=, >, >=
        // 逻辑操作： and, or, not
        // 算术操作： +, -, /, *, %, ^
        Boolean result15 = SpringExpressions.getInstance().parse(CONTEXT1, "#user.age == 18", Boolean.class);
        Boolean result16 = SpringExpressions.getInstance().parse(CONTEXT1, "#user.age >= 18", Boolean.class);
        Boolean result17 = SpringExpressions.getInstance().parse(CONTEXT1, "#user.age < 18", Boolean.class);
        Boolean result18 = SpringExpressions.getInstance().parse(CONTEXT1, "#user.getAdminIs() and #class.getActiveIs()", Boolean.class);

        // 自定义函数操作
        Boolean result19 = SpringExpressions.getInstance().parse(CONTEXT2, "#isUrlValid('http://google.com')", Boolean.class);
    }

}

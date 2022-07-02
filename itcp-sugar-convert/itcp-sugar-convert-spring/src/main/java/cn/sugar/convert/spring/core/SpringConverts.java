package cn.sugar.convert.spring.core;

/**
 * Spring 转换器
 *
 * @author wjm
 * @date 2021/5/1 14:13
 */
public class SpringConverts {

    private SpringConverts() {
    }

    private static final SpringConverts INSTANCE = new SpringConverts();

    public static SpringConverts getInstance() {
        return INSTANCE;
    }

}


package cn.srd.itcp.sugar.convert.spring.core;

/**
 * Spring 转换器
 *
 * @author wjm
 * @since 2021/5/1 14:13
 */
public class SpringConverts {

    private SpringConverts() {
    }

    private static final SpringConverts INSTANCE = new SpringConverts();

    public static SpringConverts getInstance() {
        return INSTANCE;
    }

}


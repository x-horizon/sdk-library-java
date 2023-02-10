package cn.srd.itcp.sugar.component.convert.spring.core;

/**
 * Spring 转换器
 *
 * @author wjm
 * @since 2021/5/1 14:13
 */
public class SpringConverts {

    /**
     * private block constructor
     */
    private SpringConverts() {
    }

    /**
     * hungry signal ton pattern
     */
    private static final SpringConverts INSTANCE = new SpringConverts();

    /**
     * 获取实例
     *
     * @return 实例
     */
    public static SpringConverts getInstance() {
        return INSTANCE;
    }

}


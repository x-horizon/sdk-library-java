package cn.srd.itcp.sugar.component.convert.fastjson.core;

/**
 * FastJson 转换器
 *
 * @author wjm
 * @since 2021/5/1 14:13
 */
public class FastJsonConverts {

    /**
     * private block constructor
     */
    private FastJsonConverts() {
    }

    /**
     * singleton pattern
     */
    private static final FastJsonConverts INSTANCE = new FastJsonConverts();

    /**
     * get singleton instance
     *
     * @return instance
     */
    public static FastJsonConverts getInstance() {
        return INSTANCE;
    }

}


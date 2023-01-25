package cn.srd.itcp.sugar.convert.fastjson.core;

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
     * hungry signal ton pattern
     */
    private static final FastJsonConverts INSTANCE = new FastJsonConverts();

    /**
     * 获取实例
     *
     * @return 实例
     */
    public static FastJsonConverts getInstance() {
        return INSTANCE;
    }

}


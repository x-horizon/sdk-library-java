package cn.sugar.convert.fastjson.core;

/**
 * FastJson 转换器
 *
 * @author wjm
 * @date 2021/5/1 14:13
 */
public class FastJsonConverts {

    private FastJsonConverts() {
    }

    private static final FastJsonConverts INSTANCE = new FastJsonConverts();

    public static FastJsonConverts getInstance() {
        return INSTANCE;
    }

}


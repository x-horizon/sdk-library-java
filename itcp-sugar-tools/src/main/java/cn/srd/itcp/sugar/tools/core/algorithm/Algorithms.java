package cn.srd.itcp.sugar.tools.core.algorithm;

import cn.srd.itcp.sugar.tools.core.Objects;

import java.util.ArrayList;
import java.util.List;

/**
 * 算法工具类
 *
 * @author xiongjing
 * @since 2022-08-08 20:34:26
 */
public class Algorithms {

    private Algorithms() {
    }

    /**
     * 通过数字获取二进制位组成的数组
     *
     * @param num 任意整数
     * @return List<Integer> 元素为二进制数字的数组
     */
    public static List<Integer> parseHighestOneBit(Integer num) {
        List<Integer> list = new ArrayList<>();
        Objects.requireFalse(() -> "输入的组合数字不合法", num <= 0);
        while (num > 0) {
            int item = Integer.highestOneBit(num);
            list.add(item);
            num = num - item;
        }
        return list;
    }

}
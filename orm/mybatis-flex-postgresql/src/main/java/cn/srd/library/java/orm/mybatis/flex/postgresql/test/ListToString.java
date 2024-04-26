package cn.srd.library.java.orm.mybatis.flex.postgresql.test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListToString {

    public static void main(String[] args) {
        // List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> numbers = Arrays.asList();

        String result = numbers.stream()
                .map(Object::toString)  // 将每个元素转换为字符串
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            String last = list.remove(list.size() - 1);  // 移除并获取最后一个元素
                            return String.join("-", list) + (list.isEmpty() ? "" : "*") + last;
                        }
                ));

        System.out.println(result);
    }

}
package cn.srd.itcp.sugar.tools.core;

import cn.srd.itcp.sugar.tools.constant.StringPool;
import cn.hutool.core.util.StrUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * 字符串工具
 *
 * @author wjm
 * @date 2020/5/19 17:12
 */
public class StringsUtil extends StrUtil {

    private StringsUtil() {
    }

    /**
     * 替换字符串中的指定字符串
     *
     * @param str         被替换的字符串
     * @param replacement 要替换成什么字符串
     * @param searchStrs  这里的字符串全都替换成 replacement
     * @return
     */
    public static String replace(String str, String replacement, String... searchStrs) {
        for (CharSequence searchStr : searchStrs) {
            str = replace(str, 0, searchStr, replacement, false);
        }
        return str;
    }

    /**
     * 去除头尾双引号："\"yy\"" -> "yy"
     *
     * @param str
     * @return
     */
    public static String trimHeadAndTailDoubleQuotes(String str) {
        return subBetween(str, StringPool.DOUBLE_QUOTES);
    }

    /**
     * 大写
     *
     * @param str
     * @return
     */
    public static String upperCase(final String str) {
        return StringUtils.upperCase(str);
    }

    /**
     * 驼峰命名并大写首字母
     *
     * @param str
     * @return
     */
    public static String toUpperCamelCase(CharSequence str) {
        return upperFirst(toCamelCase(str));
    }

    /**
     * 是否以指定字符串集结尾，如果有一个字符串结尾集中的字符串匹配，返回true
     *
     * @param str      被监测字符串
     * @param suffixes 结尾字符串
     * @return 是否以指定字符串结尾
     */
    public static boolean endWith(CharSequence str, String... suffixes) {
        return endWith(str, Arrays.asList(suffixes));

    }

    /**
     * 是否以指定字符串集结尾，如果有一个字符串结尾集中的字符串匹配，返回true
     *
     * @param str      被监测字符串
     * @param suffixes 结尾字符串
     * @return 是否以指定字符串结尾
     * @return
     */
    public static boolean endWith(CharSequence str, List<String> suffixes) {
        for (String s : suffixes) {
            if (endWith(str, s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 若以 str 结尾，则移除 str
     *
     * @param specifiedStr 字符串
     * @param str          指定结尾字符串
     * @return 移除后的结果
     */
    public static String removeIfEndWith(@Nullable String specifiedStr, @Nullable final String str) {
        if (endWith(specifiedStr, str)) {
            return removeSuffix(specifiedStr, str);
        }
        return specifiedStr;
    }

    /**
     * 若以 str 开头，则移除 str
     *
     * @param specifiedStr 字符串
     * @param str          指定开头字符串
     * @return 移除后的结果
     */
    public static String removeIfStartWith(@Nullable String specifiedStr, @Nullable final String str) {
        if (startWith(specifiedStr, str)) {
            return removePrefix(specifiedStr, str);
        }
        return specifiedStr;
    }

    /**
     * 查找 specifiedStr 中 strToFind 出现的次数
     *
     * @param specifiedStr
     * @param strToFind
     * @return
     */
    public static int charsCount(String specifiedStr, final String strToFind) {
        if (Objects.isEmpty(specifiedStr, strToFind)) {
            return 0;
        }
        int num = 0;
        while (specifiedStr.contains(strToFind)) {
            specifiedStr = specifiedStr.substring(specifiedStr.indexOf(strToFind) + strToFind.length());
            num++;
        }
        return num;
    }

    /**
     * 统计 specifiedStr 中各个字符出现的次数
     *
     * @param specifiedStr
     * @return
     */
    public static Map<Character, Long> charsCount(final String specifiedStr) {
        if (Objects.isEmpty(specifiedStr)) {
            return new HashMap<>();
        }
        return specifiedStr.chars().mapToObj(c -> (char) c).collect(groupingBy(identity(), counting()));
    }

    /**
     * 查找 specifiedStr 中是否包含中文字符
     *
     * @param specifiedStr
     * @return
     */
    public static boolean isChineseIncluded(final String specifiedStr) {
        if (Objects.isEmpty(specifiedStr)) {
            return false;
        }
        for (char charStr : specifiedStr.toCharArray()) {
            //使用中文的编码区间来判断
            if (charStr >= 0x4E00 && charStr <= 0x9FA5) {
                // System.out.println("中文字符：" + charStr);
                return true;
            }
        }
        return false;
    }

    /**
     * 判断 specifiedStr 是否全部是中文
     *
     * @param specifiedStr
     * @return
     */
    public static boolean isAllChinese(final String specifiedStr) {
        if (Objects.isEmpty(specifiedStr)) {
            return false;
        }
        String rule = "[\\u4e00-\\u9fa5]+";
        return specifiedStr.matches(rule);
    }

    /**
     * 连接多个字符串为一个，如果是 null ，转为 "null"
     *
     * @param strs 字符串数组
     * @return 连接后的字符串
     */
    public static String concat(CharSequence... strs) {
        return concat(false, strs);
    }

    /**
     * 连接多个字符串为一个，如果是 null ，转为 ""，如 concatNullToEmpty(null, null, "xx", null) => "xx"
     *
     * @param strs 字符串数组
     * @return 连接后的字符串
     */
    public static String concatNullToEmpty(CharSequence... strs) {
        return concat(true, strs);
    }

    /**
     * 集合格式化为 String，如 ["a","b","c"].toString = [a,b,c]，该方法是再重新格式化为["a","b","c"]
     *
     * @param collection 需要格式化的集合
     * @return 格式化后的 String
     */
    public static String toPrettyString(Collection<String> collection) {
        List<String> prettyList = new ArrayList<>(collection.size());
        collection.forEach(str -> {
            if (Objects.isNotNull(str)) {
                prettyList.add("\"" + str + "\"");
            }
        });
        return prettyList.toString();
    }

    /**
     * 集合格式化为 String，如 ["a","b","c"] => "'a','b','c'"
     *
     * @param collection 需要格式化的集合
     * @return 格式化后的 String
     */
    public static String pretty(Collection<String> collection) {
        return pretty(collection, null);
    }

    /**
     * 集合格式化为 String
     *
     * @param collection 需要格式化的集合
     * @param tag        tag 为空：["a","b","c"] => "a,b,c";   tag 不为空，["a","b","c"] => "atagbtagctag";
     * @return 格式化后的 String
     */
    public static String pretty(Collection<String> collection, @Nullable String tag) {
        if (Objects.isEmpty(collection)) {
            return StringPool.EMPTY;
        }
        if (Objects.isNull(tag)) {
            return join(StringPool.COMMA, collection);
        }
        return join(tag, collection);
    }

    /**
     * 集合格式化为 String，忽略 null 值，如 ["a","b","c",null,null] => "'a','b','c'"
     *
     * @param collection 需要格式化的集合
     * @return 格式化后的 String
     */
    public static String prettyIgnoreNull(Collection<String> collection) {
        return prettyIgnoreNull(collection, null);
    }

    /**
     * 集合格式化为 String，忽略 null 值
     *
     * @param collection 需要格式化的集合
     * @param tag        tag 为空：["a","b","c",null,null] => "'a','b','c'";   tag 不为空，["a","b","c",null,null] => "'a'tag,'b'tag,'c'tag";
     * @return 格式化后的 String
     */
    public static String prettyIgnoreNull(Collection<String> collection, @Nullable String tag) {
        if (Objects.isEmpty(collection)) {
            return StringsUtil.EMPTY;
        }
        List<String> prettyList = new ArrayList<>(collection.size());
        collection.forEach(str -> {
            if (Objects.isNotNull(str)) {
                prettyList.add("'" + str + "'" + (Objects.isEmpty(tag) ? "" : tag));
            }
        });
        return String.join(",", prettyList);
    }

    /**
     * 集合格式化为 String，如 ["a","b","c"] => "a,b,c"
     *
     * @param collection 需要格式化的集合
     * @return 格式化后的 String
     */
    public static String toWhole(Collection<String> collection) {
        return toWhole(collection, null);
    }

    /**
     * 集合格式化为 String
     *
     * @param collection 需要格式化的集合
     * @param tag        tag 为空：["a","b","c"] => "a,b,c";   tag 不为空，["a","b","c"] => "atagbtagc";
     * @return 格式化后的 String
     */
    public static String toWhole(Collection<String> collection, @Nullable String tag) {
        tag = Objects.isNull(tag) ? COMMA : tag;
        return join(tag, collection);
    }

    /**
     * 集合格式化为 String，忽略 null 值，如 ["a","b","c",null,null] => "a,b,c"
     *
     * @param collection 需要格式化的集合
     * @return 格式化后的 String
     */
    public static String toWholeIgnoreNull(Collection<String> collection) {
        return toWholeIgnoreNull(collection, null);
    }

    /**
     * 集合格式化为 String，忽略 null 值
     *
     * @param collection 需要格式化的集合
     * @param tag        tag 为空：["a","b","c",null,null] => "a,b,c";   tag 不为空，["a","b","c",null,null] => "atagbtagc";
     * @return 格式化后的 String
     */
    public static String toWholeIgnoreNull(Collection<String> collection, @Nullable String tag) {
        tag = Objects.isNull(tag) ? COMMA : tag;
        return join(tag, collection.stream().filter(Objects::isNotNull).collect(Collectors.toList()));
    }

}

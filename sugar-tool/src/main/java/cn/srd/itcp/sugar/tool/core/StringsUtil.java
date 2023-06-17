package cn.srd.itcp.sugar.tool.core;

import cn.hutool.core.util.StrUtil;
import cn.srd.itcp.sugar.tool.constant.StringPool;
import cn.srd.itcp.sugar.tool.core.object.Objects;
import cn.srd.itcp.sugar.tool.core.validation.Nullable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * 字符串工具
 *
 * @author wjm
 * @since 2020/5/19 17:12
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringsUtil extends StrUtil {

    /**
     * see {@link #contains(CharSequence, CharSequence)}
     *
     * @param str       字符串
     * @param searchStr 被查找的字符串
     * @return 是否不包含
     */
    public static boolean notContains(CharSequence str, CharSequence searchStr) {
        return !contains(str, searchStr);
    }

    /**
     * 替换字符串中的指定字符串
     *
     * @param str         被替换的字符串
     * @param replacement 要替换成什么字符串
     * @param searchStrs  这里的字符串全都替换成 replacement
     * @return 被改变后的字符串
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
     * @param str 原字符串
     * @return 被改变后的字符串
     */
    public static String trimHeadAndTailDoubleQuotes(String str) {
        return subBetween(str, StringPool.DOUBLE_QUOTES);
    }

    /**
     * 大写
     *
     * @param str 原字符串
     * @return 被改变后的字符串
     */
    public static String upperCase(final String str) {
        return StringUtils.upperCase(str);
    }

    /**
     * 小写
     *
     * @param str 原字符串
     * @return 被改变后的字符串
     */
    public static String lowerCase(final String str) {
        return StringUtils.lowerCase(str);
    }

    /**
     * 驼峰命名并大写首字母
     *
     * @param str 原字符串
     * @return 被改变后的字符串
     */
    public static String toUpperCamelCase(CharSequence str) {
        return upperFirst(toCamelCase(str));
    }

    /**
     * 是否以指定的字符开头与结尾
     *
     * @param from  输入参数
     * @param match 指定的字符
     * @return 是否以指定的字符开头与结尾
     */
    public static boolean startAndEndWith(String from, char match) {
        if (Objects.isBlank(from)) {
            return false;
        }

        CharSequence str = from.trim();
        return str.charAt(0) == match && str.charAt(str.length() - 1) == match;
    }

    /**
     * 是否不以指定的字符开头与结尾
     *
     * @param from  输入参数
     * @param match 指定的字符
     * @return 是否不以指定的字符开头与结尾
     */
    public static boolean startAndEndNotWith(String from, char match) {
        return !startAndEndWith(from, match);
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
     * 移除字符串中所有数字
     *
     * @param input 字符串
     * @return 移除数字后的字符串
     */
    public static String removeAllDigit(@Nullable String input) {
        if (Objects.isNull(input)) {
            return StringPool.EMPTY;
        }
        int length = input.length();
        StringBuilder output = builder(length);
        char inputChar;
        for (int index = 0; index < length; ++index) {
            inputChar = input.charAt(index);
            if (isNotDigit(inputChar)) {
                output.append(inputChar);
            }
        }
        return output.toString();
    }

    /**
     * 查找 specifiedStr 中 strToFind 出现的次数
     *
     * @param specifiedStr 指定的字符串
     * @param strToFind    要搜索的字符串
     * @return 出现次数
     */
    public static int charsCount(String specifiedStr, final String strToFind) {
        if (cn.srd.itcp.sugar.tool.core.object.Objects.isEmpty(specifiedStr, strToFind)) {
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
     * @param specifiedStr 指定的字符串
     * @return 出现次数
     */
    public static Map<Character, Long> charsCount(final String specifiedStr) {
        if (Objects.isEmpty(specifiedStr)) {
            return new HashMap<>();
        }
        return specifiedStr.chars().mapToObj(c -> (char) c).collect(groupingBy(identity(), counting()));
    }

    /**
     * 字符是否为数字
     *
     * @param input 字符
     * @return 是否为数字
     */
    public static boolean isDigit(char input) {
        return Character.isDigit(input);
    }

    /**
     * 字符是否不为数字
     *
     * @param input 字符
     * @return 是否不为数字
     */
    public static boolean isNotDigit(char input) {
        return !isDigit(input);
    }

    /**
     * 查找 specifiedStr 中是否包含中文字符
     *
     * @param specifiedStr 指定的字符串
     * @return 是否包含中文字符
     */
    public static boolean isChineseIncluded(final String specifiedStr) {
        if (Objects.isEmpty(specifiedStr)) {
            return false;
        }
        for (char charStr : specifiedStr.toCharArray()) {
            // 使用中文的编码区间来判断
            if (charStr >= 0x4E00 && charStr <= 0x9FA5) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断 specifiedStr 是否全部是中文
     *
     * @param specifiedStr 指定的字符串
     * @return 是否全部是中文
     */
    public static boolean isAllChinese(final String specifiedStr) {
        if (Objects.isEmpty(specifiedStr)) {
            return false;
        }
        String rule = "[\\u4e00-\\u9fa5]+";
        return specifiedStr.matches(rule);
    }

    /**
     * see {@link #getMostSimilar(String, List)}
     *
     * @param originalInput original string
     * @param compareInputs the strings to compare
     * @return the most similar string
     */
    public static String getMostSimilar(String originalInput, String... compareInputs) {
        return getMostSimilar(originalInput, Arrays.stream(compareInputs).toList());
    }

    /**
     * <pre>
     * get the most similar string compare with original string
     * similar define:
     *   the length of the longest common substring as the similarity,
     *   the longer the public substring, the higher the similarity.
     *
     * note: ignore case.
     * see {@link #getLongestCommonSubstringLength(String, String)}
     * </pre>
     *
     * @param originalInput original string
     * @param compareInputs the strings to compare
     * @return the most similar string
     */
    public static String getMostSimilar(String originalInput, List<String> compareInputs) {
        Map<Integer, String> similarityMappingStringMap = new HashMap<>(compareInputs.size());
        List<Integer> similarities = new ArrayList<>(compareInputs.size());
        compareInputs.forEach(compareInput -> {
            int similarity = getLongestCommonSubstringLength(originalInput, compareInput);
            similarities.add(similarity);
            similarityMappingStringMap.put(similarity, compareInput);
        });
        return similarityMappingStringMap.get(CollectionsUtil.max(similarities));
    }

    /**
     * get the longest common substring length in two string, see {@link #getLongestCommonSubstring(String, String)}
     *
     * @param input1 the string to compare
     * @param input2 the string to compare
     * @return the longest common substring length in two string
     */
    public static int getLongestCommonSubstringLength(String input1, String input2) {
        return getLongestCommonSubstring(input1, input2).length();
    }

    /**
     * <pre>
     * get the longest common substring in two string.
     * implement by dynamic programming algorithm.
     * the time complexity is O(mn).
     *
     * note:
     * 1. ignore case.
     * 2. if there are multiple longest common substrings, will return the last one.
     * </pre>
     *
     * @param input1 the string to compare
     * @param input2 the string to compare
     * @return the longest common substring in two string
     */
    public static String getLongestCommonSubstring(String input1, String input2) {
        if (Objects.isEmpty(input1, input2)) {
            return StringPool.EMPTY;
        }
        String lowerCaseInput1 = input1.toLowerCase();
        String lowerCaseInput2 = input2.toLowerCase();

        int[][] dp = new int[lowerCaseInput1.length() + 1][lowerCaseInput2.length() + 1];
        int maxLength = 0;
        int endIndex = 0;

        for (int i = 1; i <= lowerCaseInput1.length(); i++) {
            for (int j = 1; j <= lowerCaseInput2.length(); j++) {
                if (lowerCaseInput1.charAt(i - 1) == lowerCaseInput2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > maxLength) {
                        maxLength = dp[i][j];
                        endIndex = i;
                    }
                }
            }
        }

        return input1.substring(endIndex - maxLength, endIndex);
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
     * 连接多个字符串为一个，如果是 null ，转为 ""，如 concatNullToEmpty(null, null, "xx", null) =&gt; "xx"
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
     * 集合格式化为 String，如 ["a","b","c"] =&gt; "'a','b','c'"
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
     * @param tag        tag 为空：["a","b","c"] =&gt; "a,b,c";   tag 不为空，["a","b","c"] =&gt; "atagbtagctag";
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
     * 集合格式化为 String，忽略 null 值，如 ["a","b","c",null,null] =&gt; "'a','b','c'"
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
     * @param tag        tag 为空：["a","b","c",null,null] =&gt; "'a','b','c'";   tag 不为空，["a","b","c",null,null] =&gt; "'a'tag,'b'tag,'c'tag";
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
     * 集合格式化为 String，如 ["a","b","c"] =&gt; "a,b,c"
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
     * @param tag        tag 为空：["a","b","c"] =&gt; "a,b,c";   tag 不为空，["a","b","c"] =&gt; "atagbtagc";
     * @return 格式化后的 String
     */
    public static String toWhole(Collection<String> collection, @Nullable String tag) {
        tag = Objects.isNull(tag) ? COMMA : tag;
        return join(tag, collection);
    }

    /**
     * 集合格式化为 String，忽略 null 值，如 ["a","b","c",null,null] =&gt; "a,b,c"
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
     * @param tag        tag 为空：["a","b","c",null,null] =&gt; "a,b,c";   tag 不为空，["a","b","c",null,null] =&gt; "atagbtagc";
     * @return 格式化后的 String
     */
    public static String toWholeIgnoreNull(Collection<String> collection, @Nullable String tag) {
        tag = Objects.isNull(tag) ? COMMA : tag;
        return join(tag, collection.stream().filter(Objects::isNotNull).toList());
    }

    /**
     * 将枚举中 {@link EnumInternalType} 类型的字段提取出来，使用 {@link StringPool#COMMA} 作为分隔符，转为 String
     * <pre>
     *     例如有如下枚举：
     *
     *     &#064;Getter
     *     &#064;AllArgsConstructor
     *     public enum SexEnum {
     *          MAN(1, "男"),
     *          WOMAN(2, "女"),
     *          UNKNOWN(3, "未知");
     *          private final int code;
     *          private final String description;
     *     }
     *
     *     例如有如下集合：
     *     List&lt;SexEnum&gt; sexEnums = new ArrayList(){{
     *         add(SexEnum.MAN);
     *         add(SexEnum.WOMAN);
     *         add(SexEnum.UNKNOWN);
     *     }};
     *
     *     使用该函数进行转换：
     *     {@link StringsUtil}.{@link StringsUtil#split(List, Class)}(sexEnums, Integer.class);  结果为 ===&gt; "1,2,3"
     * </pre>
     *
     * @param input              输入集合
     * @param enumInternalType   要提取的枚举类
     * @param <EnumInternalType> 要提取的枚举类类型
     * @param <E>                集合元素类型
     * @return 分隔后的字符串
     */
    public static <EnumInternalType, E extends Enum<E>> String split(List<E> input, Class<EnumInternalType> enumInternalType) {
        return split(input, enumInternalType, StringPool.COMMA);
    }

    /**
     * 将枚举中 {@link EnumInternalType} 类型的字段提取出来，使用 joinTag 作为分隔符，转为 String，参考：{@link #split(List, Class)}
     *
     * @param input              输入集合
     * @param enumInternalType   要提取的枚举类
     * @param joinTag            分隔符
     * @param <EnumInternalType> 要提取的枚举类类型
     * @param <E>                集合元素类型
     * @return 分隔后的字符串
     */
    public static <EnumInternalType, E extends Enum<E>> String split(List<E> input, Class<EnumInternalType> enumInternalType, String joinTag) {
        List<EnumInternalType> enumIntegerValues = new ArrayList<>();
        for (E item : input) {
            enumIntegerValues.add(EnumsUtil.getEnumValue(item, enumInternalType));
        }
        return StringsUtil.join(joinTag, enumIntegerValues);
    }

    /**
     * 将数字字符串通过 {@link StringPool#COMMA} 分隔为数字后再转换为数字集合
     *
     * @param input 输入对象
     * @return 转换后集合
     */
    public static List<Integer> splitToListInteger(String input) {
        return splitToListInteger(input, StringPool.COMMA);
    }

    /**
     * 将数字字符串通过 指定分隔符 分隔为数字后再转换为数字集合
     *
     * @param input     输入对象
     * @param separator 分隔符
     * @return 转换后集合
     */
    public static List<Integer> splitToListInteger(String input, String separator) {
        return Objects.isNotBlank(input) ? CollectionsUtil.toList(input, separator).stream().map(Integer::valueOf).toList() : new ArrayList<>();
    }

    /**
     * 将枚举字符串通过 {@link StringPool#COMMA} 分隔为枚举后再转换为枚举集合
     *
     * @param input     输入对象
     * @param enumClass 枚举类
     * @param <E>       枚举类型
     * @return 转换后集合
     */
    public static <E extends Enum<E>> List<E> splitToListEnum(String input, Class<E> enumClass) {
        return splitToListEnum(input, StringPool.COMMA, enumClass);
    }

    /**
     * 将枚举字符串通过 指定分隔符 分隔为枚举后再转换为枚举集合
     *
     * @param input     输入对象
     * @param separator 输入对象
     * @param enumClass 枚举类
     * @param <E>       分隔符
     * @return 转换后集合
     */
    public static <E extends Enum<E>> List<E> splitToListEnum(String input, String separator, Class<E> enumClass) {
        List<E> result = new ArrayList<>();
        if (CollectionsUtil.isBlankOrEmptyJsonArray(input)) {
            return result;
        }
        StringsUtil.splitToListInteger(input, separator).forEach(item -> result.add(EnumsUtil.capableToEnum(item, enumClass)));
        return result;
    }

}

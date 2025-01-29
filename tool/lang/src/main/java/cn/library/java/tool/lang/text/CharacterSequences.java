package cn.library.java.tool.lang.text;

import cn.library.java.contract.constant.regex.RegexConstant;
import cn.library.java.contract.constant.text.SymbolConstant;
import cn.library.java.tool.lang.collection.Collections;
import cn.library.java.tool.lang.object.Nil;
import io.vavr.control.Try;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.dromara.hutool.core.text.CharSequenceUtil;
import org.dromara.hutool.core.text.StrUtil;
import org.dromara.hutool.core.text.split.SplitUtil;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * toolkit for char sequence
 *
 * @author wjm
 * @since 2020-05-19 17:12
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CharacterSequences extends Characters {

    /**
     * see {@link CharSequenceUtil#isUpperCase(CharSequence)}
     * <p>
     *
     * @param input the checked element
     * @return return true if all letters in the checked element are upper case
     */
    public static boolean isUpperCase(CharSequence input) {
        return CharSequenceUtil.isUpperCase(input);
    }

    /**
     * see {@link CharSequenceUtil#isLowerCase(CharSequence)}
     *
     * @param input the checked element
     * @return return true if all letters in the checked element are lower case
     */
    public static boolean isLowerCase(CharSequence input) {
        return CharSequenceUtil.isLowerCase(input);
    }

    /**
     * reverse {@link #isUpperCase(CharSequence)}
     *
     * @param input the checked element
     * @return return true if any letter in the checked element is lower case
     */
    public static boolean isNotUpperCase(CharSequence input) {
        return !isUpperCase(input);
    }

    /**
     * reverse {@link #isLowerCase(CharSequence)}
     *
     * @param input the checked element
     * @return return true if any letter in the checked element is upper case
     */
    public static boolean isNotLowerCase(CharSequence input) {
        return !isLowerCase(input);
    }

    /**
     * see {@link CharSequenceUtil#containsAny(CharSequence, CharSequence...)}
     *
     * @param input            the checked element
     * @param searchedElements the searched elements
     * @return return true if the checked element contains any searched element
     */
    public static boolean containsAny(CharSequence input, CharSequence... searchedElements) {
        return CharSequenceUtil.containsAny(input, searchedElements);
    }

    /**
     * see {@link CharSequenceUtil#containsAll(CharSequence, CharSequence...)}
     *
     * @param input            the checked element
     * @param searchedElements the searched elements
     * @return return true if the checked element contains all searched elements
     */
    public static boolean containsAll(CharSequence input, CharSequence... searchedElements) {
        return CharSequenceUtil.containsAll(input, searchedElements);
    }

    /**
     * see {@link CharSequenceUtil#containsAnyIgnoreCase(CharSequence, CharSequence...)}
     *
     * @param input            the checked element
     * @param searchedElements the searched elements
     * @return return true if the checked element contains any searched element ignore case
     */
    public static boolean containsIgnoreCase(CharSequence input, CharSequence... searchedElements) {
        return CharSequenceUtil.containsAnyIgnoreCase(input, searchedElements);
    }

    /**
     * see {@link CharSequenceUtil#containsBlank(CharSequence)}
     *
     * @param input the checked element
     * @return return true if the checked element contains blank character
     */
    public static boolean containsBlank(CharSequence input) {
        return CharSequenceUtil.containsBlank(input);
    }

    /**
     * reverse {@link #containsAny(CharSequence, CharSequence...)}
     *
     * @param input            the checked element
     * @param searchedElements the searched elements
     * @return return true if the checked element not contains all searched elements
     */
    public static boolean notContains(CharSequence input, CharSequence... searchedElements) {
        return !containsAny(input, searchedElements);
    }

    /**
     * reverse {@link #containsIgnoreCase(CharSequence, CharSequence...)}
     *
     * @param input            the checked element
     * @param searchedElements the searched elements
     * @return return true if the checked element not contains all searched elements ignore case
     */
    public static boolean notContainsIgnoreCase(CharSequence input, CharSequence... searchedElements) {
        return !containsIgnoreCase(input, searchedElements);
    }

    /**
     * see {@link CharSequenceUtil#startWithAny(CharSequence, CharSequence...)}
     *
     * @param input    the checked element
     * @param prefixes the prefixes
     * @return return true if the checked element start with any prefix
     */
    public static boolean startWith(CharSequence input, CharSequence... prefixes) {
        return CharSequenceUtil.startWithAny(input, prefixes);
    }

    /**
     * see {@link CharSequenceUtil#startWithAnyIgnoreCase(CharSequence, CharSequence...)}
     *
     * @param input    the checked element
     * @param prefixes the prefixes
     * @return return true if the checked element start with any prefix ignore case
     */
    public static boolean startWithIgnoreCase(CharSequence input, CharSequence... prefixes) {
        return CharSequenceUtil.startWithAnyIgnoreCase(input, prefixes);
    }

    /**
     * reverse {@link #startWith(CharSequence, CharSequence...)}
     *
     * @param input    the checked element
     * @param prefixes the prefixes
     * @return return true if the checked element not start with all prefixes
     */
    public static boolean notStartWith(CharSequence input, CharSequence... prefixes) {
        return !startWith(input, prefixes);
    }

    /**
     * reverse {@link #startWithIgnoreCase(CharSequence, CharSequence...)}
     *
     * @param input    the checked element
     * @param prefixes the prefixes
     * @return return true if the checked element not start with all prefixes ignore case
     */
    public static boolean notStartWithIgnoreCase(CharSequence input, CharSequence... prefixes) {
        return !startWithIgnoreCase(input, prefixes);
    }

    /**
     * see {@link CharSequenceUtil#endWithAny(CharSequence, CharSequence...)}
     *
     * @param input    the checked element
     * @param suffixes the suffixes
     * @return return true if the checked element end with any suffix
     */
    public static boolean endWith(CharSequence input, CharSequence... suffixes) {
        return CharSequenceUtil.endWithAny(input, suffixes);
    }

    /**
     * see {@link CharSequenceUtil#endWithAnyIgnoreCase(CharSequence, CharSequence...)}
     *
     * @param input    the checked element
     * @param suffixes the suffixes
     * @return return true if the checked element end with any suffix
     */
    public static boolean endWithIgnoreCase(CharSequence input, CharSequence... suffixes) {
        return CharSequenceUtil.endWithAnyIgnoreCase(input, suffixes);
    }

    /**
     * reverse {@link #endWith(CharSequence, CharSequence...)}
     *
     * @param input    the checked element
     * @param suffixes the suffixes
     * @return return true if the checked element not end with all suffixes
     */
    public static boolean notEndWith(CharSequence input, CharSequence... suffixes) {
        return !endWith(input, suffixes);
    }

    /**
     * reverse {@link #endWithIgnoreCase(CharSequence, CharSequence...)}
     *
     * @param input    the checked element
     * @param suffixes the suffixes
     * @return return true if the checked element not end with all suffixes ignore case
     */
    public static boolean notEndWithIgnoreCase(CharSequence input, CharSequence... suffixes) {
        return !endWithIgnoreCase(input, suffixes);
    }

    /**
     * get the char value at the specified index
     *
     * @param input the input element
     * @param index the specified index
     * @return the char value
     */
    public static Optional<Character> get(CharSequence input, int index) {
        return Optional.ofNullable(Try.of(() -> input.charAt(index)).getOrNull());
    }

    /**
     * see {@link CharSequenceUtil#length(CharSequence)}
     *
     * @param input the input element
     * @return length of input element
     */
    public static int getLength(CharSequence input) {
        return CharSequenceUtil.length(input);
    }

    /**
     * connect multi strings into one string, example: "12", null, "34" => "1234".
     *
     * @param inputs the input elements
     * @return after concat
     * @see CharSequenceUtil#concat(boolean, CharSequence...)
     */
    public static String concat(CharSequence... inputs) {
        return CharSequenceUtil.concat(true, inputs);
    }

    /**
     * see {@link CharSequenceUtil#format(CharSequence, Object...)}
     *
     * @param template the format template
     * @param params   the params
     * @return after format
     */
    public static String format(CharSequence template, Object... params) {
        return CharSequenceUtil.format(template, params);
    }

    /**
     * <pre>
     * convert collection to strings using {@link SymbolConstant#LF} as a separator.
     *
     * example:
     *   the inputs are:       ["1", "2", "345"]
     *   after join:           "1
     *                          2
     *                          345"
     * </pre>
     *
     * @param inputs the input elements
     * @param <T>    the input element type
     * @return after join
     */
    public static <T> String joinWithLF(Iterable<T> inputs) {
        return join(inputs, SymbolConstant.LF);
    }

    /**
     * see {@link #joinWithComma(Iterable)}
     *
     * @param inputs the input elements
     * @param <T>    the input element type
     * @return after join
     */
    @SafeVarargs
    public static <T> String joinWithComma(T... inputs) {
        return joinWithComma(Collections.ofArrayList(inputs));
    }

    /**
     * <pre>
     * convert collection to strings using {@link SymbolConstant#COMMA} as a separator.
     *
     * example:
     *   the inputs are:       ["1", "2", "345"]
     *   after join:           "1,2,345"
     * </pre>
     *
     * @param inputs the input elements
     * @param <T>    the input element type
     * @return after join
     */
    public static <T> String joinWithComma(Iterable<T> inputs) {
        return join(inputs, SymbolConstant.COMMA);
    }

    /**
     * <pre>
     * convert collection to strings using {@link SymbolConstant#COMMA_CHINESE} as a separator.
     *
     * example:
     *   the inputs are:       ["1", "2", "345"]
     *   after join:           "1、2、345"
     * </pre>
     *
     * @param inputs the input elements
     * @param <T>    the input element type
     * @return after join
     */
    public static <T> String joinWithChineseComma(Iterable<T> inputs) {
        return join(inputs, SymbolConstant.COMMA_CHINESE);
    }

    /**
     * see {@link #joinWithCommaAndSpace(Iterable)}
     *
     * @param inputs the input elements
     * @param <T>    the input element type
     * @return after join
     */
    @SafeVarargs
    public static <T> String joinWithCommaAndSpace(T... inputs) {
        return joinWithCommaAndSpace(Collections.ofArrayList(inputs));
    }

    /**
     * <pre>
     * convert collection to strings using {@link SymbolConstant#COMMA} and {@link SymbolConstant#SPACE} as a separator.
     *
     * example:
     *   the inputs are:       ["1", "2", "345"]
     *   after join:           "1, 2, 345"
     * </pre>
     *
     * @param inputs the input elements
     * @param <T>    the input element type
     * @return after join
     */
    public static <T> String joinWithCommaAndSpace(Iterable<T> inputs) {
        return join(inputs, SymbolConstant.COMMA + SymbolConstant.SPACE);
    }

    /**
     * see {@link #joinWithSingleQuoteAndComma(Iterable)}
     *
     * @param inputs the input elements
     * @param <T>    the input element type
     * @return after join
     */
    @SafeVarargs
    public static <T> String joinWithSingleQuoteAndComma(T... inputs) {
        return joinWithSingleQuoteAndComma(Collections.ofArrayList(inputs));
    }

    /**
     * <pre>
     * convert collection to strings using {@link SymbolConstant#SINGLE_QUOTE} and {@link SymbolConstant#COMMA} as a separator.
     *
     * example:
     *   the inputs are:       ["1", "2", "345"]
     *   after join:           "'1','2','345'"
     * </pre>
     *
     * @param inputs the input elements
     * @param <T>    the input element type
     * @return after join
     */
    public static <T> String joinWithSingleQuoteAndComma(Iterable<T> inputs) {
        return join(Collections.ofUnknownSizeStream(inputs).map(input -> Strings.format("'{}'", input)).toList(), SymbolConstant.COMMA);
    }

    /**
     * see {@link #joinWithDoubleQuoteAndComma(Iterable)}
     *
     * @param inputs the input elements
     * @param <T>    the input element type
     * @return after join
     */
    @SafeVarargs
    public static <T> String joinWithDoubleQuoteAndComma(T... inputs) {
        return joinWithDoubleQuoteAndComma(Collections.ofArrayList(inputs));
    }

    /**
     * <pre>
     * convert collection to strings using {@link SymbolConstant#DOUBLE_QUOTE} and {@link SymbolConstant#COMMA} as a separator.
     *
     * example:
     *   the inputs are:       ["1", "2", "345"]
     *   after join:           ""1","2","345""
     * </pre>
     *
     * @param inputs the input elements
     * @param <T>    the input element type
     * @return after join
     */
    public static <T> String joinWithDoubleQuoteAndComma(Iterable<T> inputs) {
        return join(Collections.ofUnknownSizeStream(inputs).map(input -> Strings.format("\"{}\"", input)).toList(), SymbolConstant.COMMA);
    }

    /**
     * see {@link #join(Iterable, CharSequence)}
     *
     * @param inputs      the input elements
     * @param conjunction the conjunction
     * @param <T>         the input element type
     * @return after join
     */
    public static <T> String join(T[] inputs, CharSequence conjunction) {
        return join(Collections.ofArrayList(inputs), conjunction);
    }

    /**
     * <pre>
     * convert collection to strings using conjunction as a separator.
     *
     * example:
     *   the inputs are:       ["1", "2", "345"]
     *   the conjunction is:   ","
     *   after join:           "1,2,345"
     * </pre>
     *
     * @param inputs      the input elements
     * @param conjunction the conjunction
     * @param <T>         the input element type
     * @return after join
     */
    public static <T> String join(Iterable<T> inputs, CharSequence conjunction) {
        return CharSequenceUtil.join(conjunction, inputs);
    }

    /**
     * see {@link SplitUtil#split(CharSequence, CharSequence)}
     *
     * @param input the input element
     * @return after split
     */
    public static List<String> splitByComma(CharSequence input) {
        return split(input, SymbolConstant.COMMA);
    }

    /**
     * see {@link SplitUtil#split(CharSequence, CharSequence)}
     *
     * @param input     the input element
     * @param separator the separator
     * @return after split
     */
    public static List<String> split(CharSequence input, CharSequence separator) {
        return SplitUtil.split(input, separator);
    }

    /**
     * see {@link CharSequenceUtil#subBefore(CharSequence, CharSequence, boolean)}, using the last separator.
     *
     * @param input     the input element
     * @param separator the separator
     * @return after sub
     */
    public static String subBefore(CharSequence input, CharSequence separator) {
        return CharSequenceUtil.subBefore(input, separator, true);
    }

    /**
     * see {@link CharSequenceUtil#subAfter(CharSequence, CharSequence, boolean)}, using the last separator.
     *
     * @param input     the input element
     * @param separator the separator
     * @return after sub
     */
    public static String subAfter(CharSequence input, CharSequence separator) {
        return CharSequenceUtil.subAfter(input, separator, true);
    }

    /**
     * see {@link CharSequenceUtil#subBetween(CharSequence, CharSequence)}
     *
     * @param input                   the input element
     * @param beforeAndAfterSeparator the before and after separator
     * @return after sub
     */
    public static String subBetween(CharSequence input, CharSequence beforeAndAfterSeparator) {
        return subBetween(input, beforeAndAfterSeparator, beforeAndAfterSeparator);
    }

    /**
     * see {@link CharSequenceUtil#subBetween(CharSequence, CharSequence, CharSequence)}
     *
     * @param input           the input element
     * @param beforeSeparator the before separator
     * @param afterSeparator  the after separator
     * @return after sub
     */
    public static String subBetween(CharSequence input, CharSequence beforeSeparator, CharSequence afterSeparator) {
        return CharSequenceUtil.subBetween(input, beforeSeparator, afterSeparator);
    }

    /**
     * see {@link CharSequenceUtil#removeAll(CharSequence, CharSequence...)}
     *
     * @param input           the input element
     * @param removedElements the elements to be removed
     * @return after remove
     */
    public static String removeAll(CharSequence input, CharSequence... removedElements) {
        return CharSequenceUtil.removeAll(input, removedElements);
    }

    /**
     * see {@link CharSequenceUtil#removePrefix(CharSequence, CharSequence)}
     *
     * @param input  the input element
     * @param prefix the prefix
     * @return after remove
     */
    public static String removeIfStartWith(CharSequence input, CharSequence prefix) {
        return CharSequenceUtil.removePrefix(input, prefix);
    }

    /**
     * see {@link CharSequenceUtil#removeSuffix(CharSequence, CharSequence)}
     *
     * @param input  the input element
     * @param suffix the suffix
     * @return after remove
     */
    public static String removeIfEndWith(CharSequence input, CharSequence suffix) {
        return CharSequenceUtil.removeSuffix(input, suffix);
    }

    /**
     * combine {@link #removeIfStartWith(CharSequence, CharSequence)} and {@link #removeIfEndWith(CharSequence, CharSequence)}
     *
     * @param input  the input element
     * @param prefix the prefix
     * @param suffix the suffix
     * @return after remove
     */
    public static String removeIfStartAndEndWith(CharSequence input, CharSequence prefix, CharSequence suffix) {
        return removeIfEndWith(removeIfStartWith(input, prefix), suffix);
    }

    /**
     * remove all numbers from the string
     *
     * @param input the input element
     * @return after remove
     */
    public static String removeNumber(CharSequence input) {
        if (Nil.isNull(input)) {
            return SymbolConstant.EMPTY;
        }
        int length = input.length();
        StringBuilder output = StrUtil.builder(length);
        char inputChar;
        for (int index = 0; index < length; ++index) {
            inputChar = input.charAt(index);
            if (isNotNumber(inputChar)) {
                output.append(inputChar);
            }
        }
        return output.toString();
    }

    /**
     * see {@link CharSequenceUtil#cleanBlank(CharSequence)}
     *
     * @param input the input element
     * @return after remove
     */
    public static String removeBlank(CharSequence input) {
        return CharSequenceUtil.cleanBlank(input);
    }

    /**
     * <pre>
     * remove head slash
     *
     * example:
     *   the input is: "/test1, test2, test3/"
     *   after remove: "test1, test2, test3/"
     * </pre>
     *
     * @param input the input element
     * @return after remove
     */
    public static String removeHeadSlash(CharSequence input) {
        return removeIfStartWith(input, SymbolConstant.SLASH);
    }

    /**
     * see {@link CharSequenceUtil#trim(CharSequence)}
     *
     * @param input the input element
     * @return after remove
     */
    public static String removeHeadTailBlank(CharSequence input) {
        return CharSequenceUtil.trim(input);
    }

    /**
     * <pre>
     * remove head tail double quotes.
     *
     * example:
     *   the input is: "\"yy\""
     *   after remove: "yy"
     *
     * warn:
     *   this example will not take effect: \"{\"key\":\"value\"}\"
     * </pre>
     *
     * @param input the input element
     * @return after remove
     */
    public static String removeHeadTailDoubleQuote(CharSequence input) {
        return removeIfStartAndEndWith(input, SymbolConstant.DOUBLE_QUOTE, SymbolConstant.DOUBLE_QUOTE);
    }

    /**
     * <pre>
     * remove head tail slash
     *
     * example:
     *   the input is: "/test1, test2, test3/"
     *   after remove: "test1, test2, test3"
     * </pre>
     *
     * @param input the input element
     * @return after remove
     */
    public static String removeHeadTailSlash(CharSequence input) {
        return removeIfStartAndEndWith(input, SymbolConstant.SLASH, SymbolConstant.SLASH);
    }

    /**
     * <pre>
     * remove head tail double quotes.
     *
     * example:
     *   the input is: "[test1, test2, test3]"
     *   after remove: "test1, test2, test3"
     * </pre>
     *
     * @param input the input element
     * @return after remove
     */
    public static String removeHeadTailBracket(CharSequence input) {
        return removeIfStartAndEndWith(input, SymbolConstant.BRACKET_START, SymbolConstant.BRACKET_END);
    }

    /**
     * see {@link CharSequenceUtil#removeAllLineBreaks(CharSequence)}
     *
     * @param input the input element
     * @return after remove
     */
    public static String removeLineBreak(CharSequence input) {
        return CharSequenceUtil.removeAllLineBreaks(input);
    }

    /**
     * see {@link CharSequenceUtil#addPrefixIfNot(CharSequence, CharSequence)}
     *
     * @param input  the input element
     * @param prefix the prefix to add
     * @return add prefix if the input element not start with prefix
     */
    public static String addPrefixIfNotStartWith(CharSequence input, CharSequence prefix) {
        return CharSequenceUtil.addPrefixIfNot(input, prefix);
    }

    /**
     * see {@link CharSequenceUtil#addSuffixIfNot(CharSequence, CharSequence)}
     *
     * @param input  the input element
     * @param suffix the suffix to add
     * @return add suffix if the input element not end with suffix
     */
    public static String addSuffixIfNotEndWith(CharSequence input, CharSequence suffix) {
        return CharSequenceUtil.addSuffixIfNot(input, suffix);
    }

    /**
     * see {@link CharSequenceUtil#replace(CharSequence, CharSequence, CharSequence)}
     *
     * @param input the input element
     * @return after replace
     */
    public static String replace(CharSequence input, CharSequence searchedElement, CharSequence replacedElement) {
        return CharSequenceUtil.replace(input, searchedElement, replacedElement);
    }

    /**
     * see {@link CharSequenceUtil#replaceIgnoreCase(CharSequence, CharSequence, CharSequence)}
     *
     * @param input the input element
     * @return after replace
     */
    public static String replaceIgnoreCase(CharSequence input, CharSequence searchedElement, CharSequence replacedElement) {
        return CharSequenceUtil.replaceIgnoreCase(input, searchedElement, replacedElement);
    }

    /**
     * see {@link RegexConstant#THE_LAST_DOUBLE_QUOTE}
     *
     * @param input the input element
     * @return the search result, the {@link SymbolConstant#EMPTY} will be return if no content to be found.
     */
    public static String getTheLastDoubleQuoteContent(CharSequence input) {
        return getByRegex(input, RegexConstant.THE_LAST_DOUBLE_QUOTE_PATTERN);
    }

    /**
     * search specified content by regex
     *
     * @param input the input element
     * @return the search result, the {@link SymbolConstant#EMPTY} will be return if no content to be found.
     */
    public static String getByRegex(CharSequence input, String regex) {
        return getByRegex(input, Pattern.compile(regex));
    }

    /**
     * search specified content by regex
     *
     * @param input the input element
     * @return the search result, the {@link SymbolConstant#EMPTY} will be return if no content to be found.
     */
    public static String getByRegex(CharSequence input, Pattern regexPattern) {
        Matcher matcher = regexPattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    /**
     * see {@link CharSequenceUtil#upperFirst(CharSequence)}
     *
     * @param input the input element
     * @return after upper first
     */
    public static String upperFirst(CharSequence input) {
        return CharSequenceUtil.upperFirst(input);
    }

    /**
     * see {@link CharSequenceUtil#lowerFirst(CharSequence)}
     *
     * @param input the input element
     * @return after lower first
     */
    public static String lowerFirst(CharSequence input) {
        return CharSequenceUtil.lowerFirst(input);
    }

    /**
     * see {@link CharSequenceUtil#toUnderlineCase(CharSequence)}
     *
     * @param input the input element
     * @return after underline case
     */
    public static String underlineCase(CharSequence input) {
        return CharSequenceUtil.toUnderlineCase(input);
    }

    /**
     * see {@link CharSequenceUtil#toSymbolCase(CharSequence, char)}
     *
     * @param input  the input element
     * @param symbol the symbol
     * @return after symbol case
     */
    public static String symbolCase(CharSequence input, char symbol) {
        return CharSequenceUtil.toSymbolCase(input, symbol);
    }

    /**
     * see {@link CharSequenceUtil#toCamelCase(CharSequence)}
     *
     * @param input the input element
     * @return after camel case
     */
    public static String camelCase(CharSequence input) {
        return CharSequenceUtil.toCamelCase(input);
    }

}
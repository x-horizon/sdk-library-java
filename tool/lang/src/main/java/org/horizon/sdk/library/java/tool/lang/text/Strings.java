package org.horizon.sdk.library.java.tool.lang.text;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.horizon.sdk.library.java.tool.lang.collection.Collections;
import org.horizon.sdk.library.java.tool.lang.functional.Action;
import org.horizon.sdk.library.java.tool.lang.functional.Functional;
import org.horizon.sdk.library.java.tool.lang.object.Nil;

import java.util.*;

/**
 * toolkit for string
 *
 * @author wjm
 * @since 2020-05-19 17:12
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Strings extends CharacterSequences {

    /**
     * return true if the input element contains chinese
     *
     * @param input the input element
     * @return return true if the input element contains chinese
     */
    public static boolean containsChinese(String input) {
        if (Nil.isEmpty(input)) {
            return false;
        }
        for (char character : input.toCharArray()) {
            if (character >= 0x4E00 && character <= 0x9FA5) {
                return true;
            }
        }
        return false;
    }

    /**
     * return true if the input element not contains chinese
     *
     * @param input the input element
     * @return return true if the input element not contains chinese
     */
    public static boolean notContainsChinese(String input) {
        return !containsChinese(input);
    }

    /**
     * see {@link #getMostSimilar(String, Collection)}
     *
     * @param input            original string
     * @param comparedElements the string values to compare
     * @return the most similar string
     */
    public static String getMostSimilar(String input, String... comparedElements) {
        return getMostSimilar(input, Arrays.stream(comparedElements).toList());
    }

    /**
     * <pre>
     * get the most similar string compare with original string ignore case.
     * similar define:
     *   the length of the longest common substring as the similarity,
     *   the longer the public substring, the higher the similarity.
     *
     * see {@link #getLongestCommonSubstringLength(String, String)}
     * </pre>
     *
     * @param input            original string
     * @param comparedElements the string values to compare
     * @return the most similar string
     */
    public static String getMostSimilar(String input, Collection<String> comparedElements) {
        return Action.<String>infer(Nil.isEmpty(input) || Nil.isEmpty(comparedElements))
                .then(Functional.emptyStringSupplier())
                .otherwise(() -> {
                    Map<Integer, String> similarityMappingStringMap = Collections.newHashMap(comparedElements.size());
                    List<Integer> similarities = new ArrayList<>(comparedElements.size());
                    comparedElements.forEach(compareInput -> {
                        int similarity = getLongestCommonSubstringLength(input, compareInput);
                        similarities.add(similarity);
                        similarityMappingStringMap.put(similarity, compareInput);
                    });
                    return similarityMappingStringMap.get(Collections.getMax(similarities).orElseThrow());
                })
                .get();
    }

    /**
     * get the longest common substring length in two string, see {@link #getLongestCommonSubstring(String, String)}
     *
     * @param input           the string to compare
     * @param comparedElement the string to compare
     * @return the longest common substring length in two string
     */
    public static int getLongestCommonSubstringLength(String input, String comparedElement) {
        return getLongestCommonSubstring(input, comparedElement).length();
    }

    /**
     * <pre>
     * get the longest common substring in two string.
     * implement by dynamic programming algorithm.
     * the time complexity is O(mn).
     *
     * note:
     * 1. ignore case.
     * 2. if there are multiple longest common substring values, will return the last one.
     * </pre>
     *
     * @param input           the string to compare
     * @param comparedElement the string to compare
     * @return the longest common substring in two string
     */
    public static String getLongestCommonSubstring(String input, String comparedElement) {
        return Action.<String>ifAnyEmpty(input, comparedElement)
                .then(Functional.emptyStringSupplier())
                .otherwise(() -> {
                    String lowerCaseInput = input.toLowerCase();
                    String lowerCaseComparedElement = comparedElement.toLowerCase();

                    int[][] dp = new int[lowerCaseInput.length() + 1][lowerCaseComparedElement.length() + 1];
                    int maxLength = 0;
                    int endIndex = 0;

                    for (int i = 1; i <= lowerCaseInput.length(); i++) {
                        for (int j = 1; j <= lowerCaseComparedElement.length(); j++) {
                            if (lowerCaseInput.charAt(i - 1) == lowerCaseComparedElement.charAt(j - 1)) {
                                dp[i][j] = dp[i - 1][j - 1] + 1;
                                if (dp[i][j] > maxLength) {
                                    maxLength = dp[i][j];
                                    endIndex = i;
                                }
                            }
                        }
                    }

                    return input.substring(endIndex - maxLength, endIndex);
                })
                .get();
    }

    /**
     * see {@link StringUtils#stripEnd(String, String)}
     *
     * @param input          the input element
     * @param removedElement the elements to be removed
     * @return after remove
     */
    public static String removeEnd(String input, String removedElement) {
        return StringUtils.stripEnd(input, removedElement);
    }

    /**
     * see {@link StringUtils#upperCase(String)}
     *
     * @param input the input element
     * @return after upper case
     */
    public static String upperCase(String input) {
        return StringUtils.upperCase(input);
    }

    /**
     * see {@link StringUtils#lowerCase(String)}
     *
     * @param input the input element
     * @return after lower case
     */
    public static String lowerCase(String input) {
        return StringUtils.lowerCase(input);
    }

    public static String insertFirst(String originalContent, String toFindContent, String toInsertContent) {
        int index = originalContent.indexOf(toFindContent);
        if (index == -1) {
            return originalContent;
        }
        int insertPosition = index + toFindContent.length();
        return originalContent.substring(0, insertPosition) + toInsertContent + originalContent.substring(insertPosition);
    }

}
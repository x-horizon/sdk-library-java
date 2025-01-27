package cn.library.java.tool.lang.functional;

import cn.library.java.tool.lang.booleans.Booleans;
import cn.library.java.tool.lang.compare.Comparators;
import cn.library.java.tool.lang.number.Numbers;
import cn.library.java.tool.lang.object.Nil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

/**
 * if lambda collector
 *
 * @author wjm
 * @since 2023-11-24 17:43
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class If {

    /**
     * return true if the checked element is null
     *
     * @param input the checked element
     * @return return true if the checked element is null
     * @see Nil#isNull(Object)
     */
    public static boolean isNull(Object input) {
        return Nil.isNull(input);
    }

    /**
     * return true if the checked element is zero value, the zero value is {@code null} or {@code false}
     *
     * @param input the checked element
     * @return return true if the checked element is zero value, the zero value is {@code null} or {@code false}
     * @see Nil#isZeroValue(Boolean)
     */
    public static boolean zeroValue(Boolean input) {
        return Nil.isZeroValue(input);
    }

    /**
     * return true if the checked element is zero value, the zero value is {@code null} or {@code 0}
     *
     * @param input the checked element
     * @return return true if the checked element is zero value, the zero value is {@code null} or {@code 0}
     * @see Nil#isZeroValue(Number)
     */
    public static boolean zeroValue(Number input) {
        return Nil.isZeroValue(input);
    }

    /**
     * return true if the checked element is zero value, the zero value is {@code null} or {@code ""}
     *
     * @param input the checked element
     * @return return true if the checked element is zero value, the zero value is {@code null} or {@code ""}
     * @see Nil#isZeroValue(CharSequence)
     */
    public static boolean zeroValue(CharSequence input) {
        return Nil.isZeroValue(input);
    }

    /**
     * return true if the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @return return true if the checked element is null or zero size
     * @see Nil#isEmpty(byte[])
     */
    public static boolean empty(byte[] inputs) {
        return Nil.isEmpty(inputs);
    }

    /**
     * return true if the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @return return true if the checked element is null or zero size
     * @see Nil#isEmpty(short[])
     */
    public static boolean empty(short[] inputs) {
        return Nil.isEmpty(inputs);
    }

    /**
     * return true if the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @return return true if the checked element is null or zero size
     * @see Nil#isEmpty(int[])
     */
    public static boolean empty(int[] inputs) {
        return Nil.isEmpty(inputs);
    }

    /**
     * return true if the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @return return true if the checked element is null or zero size
     * @see Nil#isEmpty(long[])
     */
    public static boolean empty(long[] inputs) {
        return Nil.isEmpty(inputs);
    }

    /**
     * return true if the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @return return true if the checked element is null or zero size
     * @see Nil#isEmpty(float[])
     */
    public static boolean empty(float[] inputs) {
        return Nil.isEmpty(inputs);
    }

    /**
     * return true if the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @return return true if the checked element is null or zero size
     * @see Nil#isEmpty(double[])
     */
    public static boolean empty(double[] inputs) {
        return Nil.isEmpty(inputs);
    }

    /**
     * return true if the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @return return true if the checked element is null or zero size
     * @see Nil#isEmpty(boolean[])
     */
    public static boolean empty(boolean[] inputs) {
        return Nil.isEmpty(inputs);
    }

    /**
     * return true if the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @return return true if the checked element is null or zero size
     * @see Nil#isEmpty(char[])
     */
    public static boolean empty(char[] inputs) {
        return Nil.isEmpty(inputs);
    }

    /**
     * return true if the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @param <T>    the checked element type
     * @return return true if the checked element is null or zero size
     * @see Nil#isEmpty(Object[])
     */
    public static <T> boolean empty(T[] inputs) {
        return Nil.isEmpty(inputs);
    }

    /**
     * return true if the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @return return true if the checked element is null or zero size
     * @see Nil#isEmpty(Iterator)
     */
    public static boolean empty(Iterator<?> inputs) {
        return Nil.isEmpty(inputs);
    }

    /**
     * return true if the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @return return true if the checked element is null or zero size
     * @see Nil#isEmpty(Iterable)
     */
    public static boolean empty(Iterable<?> inputs) {
        return Nil.isEmpty(inputs);
    }

    /**
     * return true if the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @return return true if the checked element is null or zero size
     * @see Nil#isEmpty(Enumeration)
     */
    public static boolean empty(Enumeration<?> inputs) {
        return Nil.isEmpty(inputs);
    }

    /**
     * return true if the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @return return true if the checked element is null or zero size
     * @see Nil#isEmpty(Map)
     */
    public static boolean empty(Map<?, ?> inputs) {
        return Nil.isEmpty(inputs);
    }

    /**
     * return true if the checked element is empty string
     *
     * @param input the checked element
     * @return return true if the checked element is empty string
     * @see Nil#isEmpty(CharSequence)
     */
    public static boolean empty(CharSequence input) {
        return Nil.isEmpty(input);
    }

    /**
     * return true if the checked element is blank string
     *
     * @param input the checked element
     * @return return true if the checked element is blank string
     * @see Nil#isBlank(CharSequence)
     */
    public static boolean blank(CharSequence input) {
        return Nil.isBlank(input);
    }

    /**
     * return true if the checked element is true
     *
     * @param input the checked element
     * @return return true if the checked element is true
     * @see Booleans#isTrue(Boolean)
     */
    public static boolean isTrue(Boolean input) {
        return Booleans.isTrue(input);
    }

    /**
     * return true if the checked element is false
     *
     * @param input the checked element
     * @return return true if the checked element is false
     * @see Booleans#isFalse(Boolean)
     */
    public static boolean isFalse(Boolean input) {
        return Booleans.isFalse(input);
    }

    /**
     * return true if the checked element is not {@code null} and {@code > 0}
     *
     * @param input the checked element
     * @return return true if the checked element is not {@code null} and {@code > 0}
     * @see Numbers#isPositive(Number)
     */
    public static boolean positive(Number input) {
        return Numbers.isPositive(input);
    }

    /**
     * return true if any checked element is null
     *
     * @param inputs the checked elements
     * @return return true if any checked element is null
     * @see Nil#isAnyNull(Number...)
     */
    public static boolean anyNull(Number... inputs) {
        return Nil.isAnyNull(inputs);
    }

    /**
     * return true if any checked element is null
     *
     * @param inputs the checked elements
     * @return return true if any checked element is null
     * @see Nil#isAnyNull(Enum[])
     */
    public static boolean anyNull(Enum<?>... inputs) {
        return Nil.isAnyNull(inputs);
    }

    /**
     * return true if any checked element is null
     *
     * @param inputs the checked elements
     * @return return true if any checked element is null
     * @see Nil#isAnyNull(Boolean...)
     */
    public static boolean anyNull(Boolean... inputs) {
        return Nil.isAnyNull(inputs);
    }

    /**
     * return true if any checked element is null
     *
     * @param inputs the checked elements
     * @return return true if any checked element is null
     * @see Nil#isAnyNull(Object...)
     */
    public static boolean anyNull(Object... inputs) {
        return Nil.isAnyNull(inputs);
    }

    /**
     * return true if any checked element is null or zero size
     *
     * @param inputs the checked elements
     * @return return true if any checked element is null or zero size
     * @see Nil#isAnyEmpty(Iterator[])
     */
    public static boolean anyEmpty(Iterator<?>... inputs) {
        return Nil.isAnyEmpty(inputs);
    }

    /**
     * return true if any checked element is null or zero size
     *
     * @param inputs the checked elements
     * @return return true if any checked element is null or zero size
     * @see Nil#isAnyEmpty(Iterable[])
     */
    public static boolean anyEmpty(Iterable<?>... inputs) {
        return Nil.isAnyEmpty(inputs);
    }

    /**
     * return true if any checked element is null or zero size
     *
     * @param inputs the checked elements
     * @return return true if any checked element is null or zero size
     * @see Nil#isAnyEmpty(Enumeration[])
     */
    public static boolean anyEmpty(Enumeration<?>... inputs) {
        return Nil.isAnyEmpty(inputs);
    }

    /**
     * return true if any checked element is null or zero size
     *
     * @param inputs the checked elements
     * @return return true if any checked element is null or zero size
     * @see Nil#isAnyEmpty(Map[])
     */
    public static boolean anyEmpty(Map<?, ?>... inputs) {
        return Nil.isAnyEmpty(inputs);
    }

    /**
     * return true if any checked element is empty string
     *
     * @param inputs the checked elements
     * @return return true if any checked element is empty string
     * @see Nil#isAnyEmpty(CharSequence...)
     */
    public static boolean anyEmpty(CharSequence... inputs) {
        return Nil.isAnyEmpty(inputs);
    }

    /**
     * return true if any checked element is blank string
     *
     * @param inputs the checked elements
     * @return return true if any checked element is blank string
     * @see Nil#isAnyBlank(CharSequence...)
     */
    public static boolean anyBlank(CharSequence... inputs) {
        return Nil.isAnyBlank(inputs);
    }

    /**
     * return true if any checked element is true
     *
     * @param inputs the checked elements
     * @return return true if any checked element is true
     * @see Booleans#isAnyTrue(Boolean...)
     */
    public static boolean anyTrue(Boolean... inputs) {
        return Booleans.isAnyTrue(inputs);
    }

    /**
     * return true if any checked element is false
     *
     * @param inputs the checked elements
     * @return return true if any checked element is false
     * @see Booleans#isAnyFalse(Boolean...)
     */
    public static boolean anyFalse(Boolean... inputs) {
        return Booleans.isAnyFalse(inputs);
    }

    /**
     * return true if any checked element is not {@code null} and {@code > 0}
     *
     * @param inputs the checked elements
     * @return return true if any checked element is not {@code null} and {@code > 0}
     * @see Numbers#isAnyPositive(Number...)
     */
    public static boolean anyPositive(Number... inputs) {
        return Numbers.isAnyPositive(inputs);
    }

    /**
     * return true if all checked elements are null
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are null
     * @see Nil#isAllNull(Number...)
     */
    public static boolean allNull(Number... inputs) {
        return Nil.isAllNull(inputs);
    }

    /**
     * return true if all checked elements are null
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are null
     * @see Nil#isAllNull(Enum[])
     */
    public static boolean allNull(Enum<?>... inputs) {
        return Nil.isAllNull(inputs);
    }

    /**
     * return true if all checked elements are null
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are null
     * @see Nil#isAllNull(Boolean...)
     */
    public static boolean allNull(Boolean... inputs) {
        return Nil.isAllNull(inputs);
    }

    /**
     * return true if all checked elements are null
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are null
     * @see Nil#isAllNull(Object...)
     */
    public static boolean allNull(Object... inputs) {
        return Nil.isAllNull(inputs);
    }

    /**
     * return true if all checked elements are null or zero size
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are null or zero size
     * @see Nil#isAllEmpty(Iterator[])
     */
    public static boolean allEmpty(Iterator<?>... inputs) {
        return Nil.isAllEmpty(inputs);
    }

    /**
     * return true if all checked elements are null or zero size
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are null or zero size
     * @see Nil#isAllEmpty(Iterable[])
     */
    public static boolean allEmpty(Iterable<?>... inputs) {
        return Nil.isAllEmpty(inputs);
    }

    /**
     * return true if all checked elements are null or zero size
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are null or zero size
     * @see Nil#isAllEmpty(Enumeration[])
     */
    public static boolean allEmpty(Enumeration<?>... inputs) {
        return Nil.isAllEmpty(inputs);
    }

    /**
     * return true if all checked elements are null or zero size
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are null or zero size
     * @see Nil#isAllEmpty(Map[])
     */
    public static boolean allEmpty(Map<?, ?>... inputs) {
        return Nil.isAllEmpty(inputs);
    }

    /**
     * return true if all checked elements are all empty string
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are all empty string
     * @see Nil#isAllEmpty(CharSequence...)
     */
    public static boolean allEmpty(CharSequence... inputs) {
        return Nil.isAllEmpty(inputs);
    }

    /**
     * return true if all checked elements are all blank string
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are all blank string
     * @see Nil#isAllBlank(CharSequence...)
     */
    public static boolean allBlank(CharSequence... inputs) {
        return Nil.isAllBlank(inputs);
    }

    /**
     * return true if all checked elements are true
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are true
     * @see Booleans#isAllTrue(Boolean...)
     */
    public static boolean allTrue(Boolean... inputs) {
        return Booleans.isAllTrue(inputs);
    }

    /**
     * return true if all checked elements are false
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are false
     * @see Booleans#isAllFalse(Boolean...)
     */
    public static boolean allFalse(Boolean... inputs) {
        return Booleans.isAllFalse(inputs);
    }

    /**
     * return true if all checked elements are not {@code null} and {@code > 0}
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are not {@code null} and {@code > 0}
     * @see Numbers#isAllPositive(Number...)
     */
    public static boolean allPositive(Number... inputs) {
        return Numbers.isAllPositive(inputs);
    }

    /**
     * return true if the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @return return true if the checked element equals compared element
     * @see Comparators#equals(long, long)
     */
    public static boolean equals(long input, long comparedElement) {
        return Comparators.equals(input, comparedElement);
    }

    /**
     * return true if the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @return return true if the checked element equals compared element
     * @see Comparators#equals(float, float)
     */
    public static boolean equals(float input, float comparedElement) {
        return Comparators.equals(input, comparedElement);
    }

    /**
     * return true if the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @return return true if the checked element equals compared element
     * @see Comparators#equals(double, double)
     */
    public static boolean equals(double input, double comparedElement) {
        return Comparators.equals(input, comparedElement);
    }

    /**
     * return true if the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @return return true if the checked element equals compared element
     * @see Comparators#equals(BigDecimal, BigDecimal)
     */
    public static boolean equals(BigDecimal input, BigDecimal comparedElement) {
        return Comparators.equals(input, comparedElement);
    }

    /**
     * return true if the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @return return true if the checked element equals compared element
     * @see Comparators#equals(Number, Number)
     */
    public static boolean equals(Number input, Number comparedElement) {
        return Comparators.equals(input, comparedElement);
    }

    /**
     * return true if the checked element equals any compared element
     *
     * @param input            the checked element
     * @param comparedElements the compared elements
     * @return return true if the checked element equals any compared element
     * @see Comparators#equals(CharSequence, CharSequence...)
     */
    public static boolean equals(CharSequence input, CharSequence... comparedElements) {
        return Comparators.equals(input, comparedElements);
    }

    /**
     * return true if the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared elements
     * @return return true if the checked element equals compared element
     * @see Comparators#equals(byte[], byte[])
     */
    public static boolean equals(byte[] input, byte[] comparedElement) {
        return Comparators.equals(input, comparedElement);
    }

    /**
     * return true if the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared elements
     * @return return true if the checked element equals compared element
     * @see Comparators#equals(short[], short[])
     */
    public static boolean equals(short[] input, short[] comparedElement) {
        return Comparators.equals(input, comparedElement);
    }

    /**
     * return true if the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared elements
     * @return return true if the checked element equals compared element
     * @see Comparators#equals(int[], int[])
     */
    public static boolean equals(int[] input, int[] comparedElement) {
        return Comparators.equals(input, comparedElement);
    }

    /**
     * return true if the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared elements
     * @return return true if the checked element equals compared element
     * @see Comparators#equals(long[], long[])
     */
    public static boolean equals(long[] input, long[] comparedElement) {
        return Comparators.equals(input, comparedElement);
    }

    /**
     * return true if the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared elements
     * @return return true if the checked element equals compared element
     * @see Comparators#equals(float[], float[])
     */
    public static boolean equals(float[] input, float[] comparedElement) {
        return Comparators.equals(input, comparedElement);
    }

    /**
     * return true if the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared elements
     * @return return true if the checked element equals compared element
     * @see Comparators#equals(double[], double[])
     */
    public static boolean equals(double[] input, double[] comparedElement) {
        return Comparators.equals(input, comparedElement);
    }

    /**
     * return true if the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared elements
     * @return return true if the checked element equals compared element
     * @see Comparators#equals(boolean[], boolean[])
     */
    public static boolean equals(boolean[] input, boolean[] comparedElement) {
        return Comparators.equals(input, comparedElement);
    }

    /**
     * return true if the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared elements
     * @return return true if the checked element equals compared element
     * @see Comparators#equals(char[], char[])
     */
    public static boolean equals(char[] input, char[] comparedElement) {
        return Comparators.equals(input, comparedElement);
    }

    /**
     * return true if the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared elements
     * @return return true if the checked element equals compared element
     * @see Comparators#equals(Object[], Object[])
     */
    public static boolean equals(Object[] input, Object[] comparedElement) {
        return Comparators.equals(input, comparedElement);
    }

    /**
     * return true if the checked element equals the compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @return return true if the checked element equals the compared element
     * @see Comparators#equals(Object, Object)
     */
    public static boolean equals(Object input, Object comparedElement) {
        return Comparators.equals(input, comparedElement);
    }

    /**
     * return true if the checked element equals any compared element
     *
     * @param input            the checked element
     * @param comparedElements the compared elements
     * @return return true if the checked element equals any compared element
     * @see Comparators#equalsAny(Object, Object...)
     */
    public static boolean equalsAny(Object input, Object... comparedElements) {
        return Comparators.equalsAny(input, comparedElements);
    }

    /**
     * return true if the checked element equals any compared element
     *
     * @param input            the checked element
     * @param comparedElements the compared elements
     * @return return true if the checked element equals any compared element
     * @see Comparators#equalsAll(Object, Object...)
     */
    public static boolean equalsAll(Object input, Object... comparedElements) {
        return Comparators.equalsAll(input, comparedElements);
    }

    /**
     * return true if the checked element equals any compared element ignore case
     *
     * @param input            the checked element
     * @param comparedElements the compared elements
     * @return return true if the checked element equals any compared element ignore case
     * @see Comparators#equalsIgnoreCase(CharSequence, CharSequence...)
     */
    public static boolean equalsIgnoreCase(CharSequence input, CharSequence... comparedElements) {
        return Comparators.equalsIgnoreCase(input, comparedElements);
    }

    /**
     * return true if the checked element is not null
     *
     * @param input the checked element
     * @return return true if the checked element is not null
     * @see Nil#isNotNull(Object)
     */
    public static boolean notNull(Object input) {
        return Nil.isNotNull(input);
    }

    /**
     * return true if the checked element is not zero value, the zero value is {@code null} or {@code false}
     *
     * @param input the checked element
     * @return return true if the checked element is not zero value, the zero value is {@code null} or {@code false}
     * @see Nil#isNotZeroValue(Boolean)
     */
    public static boolean notZeroValue(Boolean input) {
        return Nil.isNotZeroValue(input);
    }

    /**
     * return true if the checked element is not zero value, the zero value is {@code null} or {@code 0}
     *
     * @param input the checked element
     * @return return true if the checked element is not zero value, the zero value is {@code null} or {@code 0}
     * @see Nil#isNotZeroValue(Number)
     */
    public static boolean notZeroValue(Number input) {
        return Nil.isNotZeroValue(input);
    }

    /**
     * return true if the checked element is not zero value, the zero value is {@code null} or {@code ""}
     *
     * @param input the checked element
     * @return return true if the checked element is not zero value, the zero value is {@code null} or {@code ""}
     * @see Nil#isNotZeroValue(CharSequence)
     */
    public static boolean notZeroValue(CharSequence input) {
        return Nil.isNotZeroValue(input);
    }

    /**
     * return true if the checked element is not null and not zero size
     *
     * @param inputs the checked elements
     * @return return true if the checked element is not null and not zero size
     * @see Nil#isNotEmpty(byte[])
     */
    public static boolean notEmpty(byte[] inputs) {
        return Nil.isNotEmpty(inputs);
    }

    /**
     * return true if the checked element is not null and not zero size
     *
     * @param inputs the checked elements
     * @return return true if the checked element is not null and not zero size
     * @see Nil#isNotEmpty(short[])
     */
    public static boolean notEmpty(short[] inputs) {
        return Nil.isNotEmpty(inputs);
    }

    /**
     * return true if the checked element is not null and not zero size
     *
     * @param inputs the checked elements
     * @return return true if the checked element is not null and not zero size
     * @see Nil#isNotEmpty(int[])
     */
    public static boolean notEmpty(int[] inputs) {
        return Nil.isNotEmpty(inputs);
    }

    /**
     * return true if the checked element is not null and not zero size
     *
     * @param inputs the checked elements
     * @return return true if the checked element is not null and not zero size
     * @see Nil#isNotEmpty(long[])
     */
    public static boolean notEmpty(long[] inputs) {
        return Nil.isNotEmpty(inputs);
    }

    /**
     * return true if the checked element is not null and not zero size
     *
     * @param inputs the checked elements
     * @return return true if the checked element is not null and not zero size
     * @see Nil#isNotEmpty(float[])
     */
    public static boolean notEmpty(float[] inputs) {
        return Nil.isNotEmpty(inputs);
    }

    /**
     * return true if the checked element is not null and not zero size
     *
     * @param inputs the checked elements
     * @return return true if the checked element is not null and not zero size
     * @see Nil#isNotEmpty(double[])
     */
    public static boolean notEmpty(double[] inputs) {
        return Nil.isNotEmpty(inputs);
    }

    /**
     * return true if the checked element is not null and not zero size
     *
     * @param inputs the checked elements
     * @return return true if the checked element is not null and not zero size
     * @see Nil#isNotEmpty(boolean[])
     */
    public static boolean notEmpty(boolean[] inputs) {
        return Nil.isNotEmpty(inputs);
    }

    /**
     * return true if the checked element is not null and not zero size
     *
     * @param inputs the checked elements
     * @return return true if the checked element is not null and not zero size
     * @see Nil#isNotEmpty(char[])
     */
    public static boolean notEmpty(char[] inputs) {
        return Nil.isNotEmpty(inputs);
    }

    /**
     * return true if the checked element is not null and not zero size
     *
     * @param inputs the checked elements
     * @param <T>    the checked element type
     * @return return true if the checked element is not null and not zero size
     * @see Nil#isNotEmpty(Object[])
     */
    public static <T> boolean notEmpty(T[] inputs) {
        return Nil.isNotEmpty(inputs);
    }

    /**
     * return true if the checked element is not null and not zero size
     *
     * @param inputs the checked elements
     * @return return true if the checked element is not null and not zero size
     * @see Nil#isNotEmpty(Iterator)
     */
    public static boolean notEmpty(Iterator<?> inputs) {
        return Nil.isNotEmpty(inputs);
    }

    /**
     * return true if the checked element is not null and not zero size
     *
     * @param inputs the checked elements
     * @return return true if the checked element is not null and not zero size
     * @see Nil#isNotEmpty(Iterable)
     */
    public static boolean notEmpty(Iterable<?> inputs) {
        return Nil.isNotEmpty(inputs);
    }

    /**
     * return true if the checked element is not null and not zero size
     *
     * @param inputs the checked elements
     * @return return true if the checked element is not null and not zero size
     * @see Nil#isNotEmpty(Enumeration)
     */
    public static boolean notEmpty(Enumeration<?> inputs) {
        return Nil.isNotEmpty(inputs);
    }

    /**
     * return true if the checked element is not null and not zero size
     *
     * @param inputs the checked elements
     * @return return true if the checked element is not null and not zero size
     * @see Nil#isNotEmpty(Map)
     */
    public static boolean notEmpty(Map<?, ?> inputs) {
        return Nil.isNotEmpty(inputs);
    }

    /**
     * return true if the checked element is not empty string
     *
     * @param input the checked element
     * @return return true if the checked element is not empty string
     * @see Nil#isNotEmpty(CharSequence)
     */
    public static boolean notEmpty(CharSequence input) {
        return Nil.isNotEmpty(input);
    }

    /**
     * return true if the checked element is not blank string
     *
     * @param input the checked element
     * @return return true if the checked element is not blank string
     * @see Nil#isNotBlank(CharSequence)
     */
    public static boolean notBlank(CharSequence input) {
        return Nil.isNotBlank(input);
    }

    /**
     * return true if the checked element is not {@code null} and {@code <= 0}
     *
     * @param input the checked element
     * @return return true if the checked element is not {@code null} and {@code <= 0}
     * @see Numbers#isNotPositive(Number)
     */
    public static boolean notPositive(Number input) {
        return Numbers.isNotPositive(input);
    }

    /**
     * return true if all checked elements are not null
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are not null
     * @see Nil#isAllNotNull(Number...)
     */
    public static boolean allNotNull(Number... inputs) {
        return Nil.isAllNotNull(inputs);
    }

    /**
     * return true if all checked elements are not null
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are not null
     * @see Nil#isAllNotNull(Enum[])
     */
    public static boolean allNotNull(Enum<?>... inputs) {
        return Nil.isAllNotNull(inputs);
    }

    /**
     * return true if all checked elements are not null
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are not null
     * @see Nil#isAllNotNull(Boolean...)
     */
    public static boolean allNotNull(Boolean... inputs) {
        return Nil.isAllNotNull(inputs);
    }

    /**
     * return true if all checked elements are not null
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are not null
     * @see Nil#isAllNotNull(Object...)
     */
    public static boolean allNotNull(Object... inputs) {
        return Nil.isAllNotNull(inputs);
    }

    /**
     * return true if all checked elements are not null and not zero size
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are not null and not zero size
     * @see Nil#isAllNotEmpty(Iterator[])
     */
    public static boolean allNotEmpty(Iterator<?>... inputs) {
        return Nil.isAllNotEmpty(inputs);
    }

    /**
     * return true if all checked elements are not null and not zero size
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are not null and not zero size
     * @see Nil#isAllNotEmpty(Iterable[])
     */
    public static boolean allNotEmpty(Iterable<?>... inputs) {
        return Nil.isAllNotEmpty(inputs);
    }

    /**
     * return true if all checked elements are not null and not zero size
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are not null and not zero size
     * @see Nil#isAllNotEmpty(Enumeration[])
     */
    public static boolean allNotEmpty(Enumeration<?>... inputs) {
        return Nil.isAllNotEmpty(inputs);
    }

    /**
     * return true if all checked elements are not null and not zero size
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are not null and not zero size
     * @see Nil#isAllNotEmpty(Map[])
     */
    public static boolean allNotEmpty(Map<?, ?>... inputs) {
        return Nil.isAllNotEmpty(inputs);
    }

    /**
     * return true if all checked elements are not empty string
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are not empty string
     * @see Nil#isAllNotEmpty(CharSequence...)
     */
    public static boolean allNotEmpty(CharSequence... inputs) {
        return Nil.isAllNotEmpty(inputs);
    }

    /**
     * return true if all checked elements are not blank string
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are not blank string
     * @see Nil#isAllNotBlank(CharSequence...)
     */
    public static boolean allNotBlank(CharSequence... inputs) {
        return Nil.isAllNotBlank(inputs);
    }

    /**
     * return true if all checked elements are not {@code null} and {@code <= 0}
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are not {@code null} and {@code <= 0}
     * @see Numbers#isAllNotPositive(Number...)
     */
    public static boolean allNotPositive(Number... inputs) {
        return Numbers.isAllNotPositive(inputs);
    }

    /**
     * return true if the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @return return true if the checked element not equals compared element
     * @see Comparators#notEquals(long, long)
     */
    public static boolean notEquals(long input, long comparedElement) {
        return Comparators.notEquals(input, comparedElement);
    }

    /**
     * return true if the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @return return true if the checked element not equals compared element
     * @see Comparators#notEquals(float, float)
     */
    public static boolean notEquals(float input, float comparedElement) {
        return Comparators.notEquals(input, comparedElement);
    }

    /**
     * return true if the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @return return true if the checked element not equals compared element
     * @see Comparators#notEquals(double, double)
     */
    public static boolean notEquals(double input, double comparedElement) {
        return Comparators.notEquals(input, comparedElement);
    }

    /**
     * return true if the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @return return true if the checked element not equals compared element
     * @see Comparators#notEquals(BigDecimal, BigDecimal)
     */
    public static boolean notEquals(BigDecimal input, BigDecimal comparedElement) {
        return Comparators.notEquals(input, comparedElement);
    }

    /**
     * return true if the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @return return true if the checked element not equals compared element
     * @see Comparators#notEquals(Number, Number)
     */
    public static boolean notEquals(Number input, Number comparedElement) {
        return Comparators.notEquals(input, comparedElement);
    }

    /**
     * return true if the checked element not equals all compared elements
     *
     * @param input            the checked element
     * @param comparedElements the compared elements
     * @return return true if the checked element not equals all compared elements
     * @see Comparators#notEquals(CharSequence, CharSequence...)
     */
    public static boolean notEquals(CharSequence input, CharSequence... comparedElements) {
        return Comparators.notEquals(input, comparedElements);
    }

    /**
     * return true if the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @return return true if the checked element not equals compared element
     * @see Comparators#notEquals(byte[], byte[])
     */
    public static boolean notEquals(byte[] input, byte[] comparedElement) {
        return Comparators.notEquals(input, comparedElement);
    }

    /**
     * return true if the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @return return true if the checked element not equals compared element
     * @see Comparators#notEquals(short[], short[])
     */
    public static boolean notEquals(short[] input, short[] comparedElement) {
        return Comparators.notEquals(input, comparedElement);
    }

    /**
     * return true if the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @return return true if the checked element not equals compared element
     * @see Comparators#notEquals(int[], int[])
     */
    public static boolean notEquals(int[] input, int[] comparedElement) {
        return Comparators.notEquals(input, comparedElement);
    }

    /**
     * return true if the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @return return true if the checked element not equals compared element
     * @see Comparators#notEquals(long[], long[])
     */
    public static boolean notEquals(long[] input, long[] comparedElement) {
        return Comparators.notEquals(input, comparedElement);
    }

    /**
     * return true if the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @return return true if the checked element not equals compared element
     * @see Comparators#notEquals(float[], float[])
     */
    public static boolean notEquals(float[] input, float[] comparedElement) {
        return Comparators.notEquals(input, comparedElement);
    }

    /**
     * return true if the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @return return true if the checked element not equals compared element
     * @see Comparators#notEquals(double[], double[])
     */
    public static boolean notEquals(double[] input, double[] comparedElement) {
        return Comparators.notEquals(input, comparedElement);
    }

    /**
     * return true if the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @return return true if the checked element not equals compared element
     * @see Comparators#notEquals(boolean[], boolean[])
     */
    public static boolean notEquals(boolean[] input, boolean[] comparedElement) {
        return Comparators.notEquals(input, comparedElement);
    }

    /**
     * return true if the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @return return true if the checked element not equals compared element
     * @see Comparators#notEquals(char[], char[])
     */
    public static boolean notEquals(char[] input, char[] comparedElement) {
        return Comparators.notEquals(input, comparedElement);
    }

    /**
     * return true if the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @return return true if the checked element not equals compared element
     * @see Comparators#notEquals(Object[], Object[])
     */
    public static boolean notEquals(Object[] input, Object[] comparedElement) {
        return Comparators.notEquals(input, comparedElement);
    }

    /**
     * return true if the checked element not equals the compared elements
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @return return true if the checked element not equals the compared elements
     * @see Comparators#notEquals(Object, Object)
     */
    public static boolean notEquals(Object input, Object comparedElement) {
        return Comparators.notEquals(input, comparedElement);
    }

    /**
     * return true if the checked element not equals all compared elements
     *
     * @param input            the checked element
     * @param comparedElements the compared elements
     * @return return true if the checked element not equals all compared elements
     * @see Comparators#notEquals(Object, Object...)
     */
    public static boolean notEquals(Object input, Object... comparedElements) {
        return Comparators.notEquals(input, comparedElements);
    }

    /**
     * return true if the checked element not equals all compared elements ignore case
     *
     * @param input            the checked element
     * @param comparedElements the compared elements
     * @return return true if the checked element not equals all compared elements ignore case
     * @see Comparators#notEquals(CharSequence, CharSequence...)
     */
    public static boolean notEqualsIgnoreCase(CharSequence input, CharSequence... comparedElements) {
        return Comparators.notEqualsIgnoreCase(input, comparedElements);
    }

}
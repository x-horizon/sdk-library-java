package org.horizon.sdk.library.java.tool.lang.functional;

import org.horizon.sdk.library.java.contract.constant.throwable.AssertMessage;
import org.horizon.sdk.library.java.contract.model.throwable.ClientException;
import org.horizon.sdk.library.java.tool.lang.booleans.Booleans;
import org.horizon.sdk.library.java.tool.lang.compare.Comparators;
import org.horizon.sdk.library.java.tool.lang.number.Numbers;
import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.horizon.sdk.library.java.tool.lang.object.Objects;
import org.horizon.sdk.library.java.tool.lang.text.Strings;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

/**
 * <pre>
 * assert toolkit
 *
 * example code:
 *    {@code
 *      public static void main(String[] args) {
 *           Assert.of()
 *                   // default status when throws
 *                   .setStatus(HttpStatus.INTERNAL_ERROR.getStatus())
 *                   // default exception when throws
 *                   .setThrowable(RunningException.class)
 *                   .setMessage("throw because of null object!")
 *                   .throwsIfNull(null);
 *       }
 *    }
 * </pre>
 *
 * @author wjm
 * @since 2020-06-13 20:05
 */

@Accessors(chain = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Assert {

    /**
     * the message template of exception
     */
    private String messageTemplate;

    /**
     * the message template params of exception
     */
    private Object[] messageTemplateParams;

    /**
     * the exception extends {@link Throwable}
     */
    @Setter private Class<? extends Throwable> throwable;

    public Assert setMessage(String template, Object... params) {
        this.messageTemplate = template;
        this.messageTemplateParams = params;
        return this;
    }

    /**
     * return {@link Assert} instance
     *
     * @return {@link Assert} instance
     */
    public static Assert of() {
        return new Assert();
    }

    /**
     * return {@link Assert} instance
     *
     * @param throwableClass the exception extends {@link Throwable}
     * @return {@link Assert} instance
     */
    public static Assert of(Class<? extends Throwable> throwableClass) {
        return of().setThrowable(throwableClass);
    }

    /**
     * return {@link Assert} instance
     *
     * @param template the message template of exception
     * @param params   the message template params of exception
     * @return {@link Assert} instance
     */
    public static Assert of(String template, Object... params) {
        return of().setMessage(template, params);
    }

    /**
     * return {@link Assert} instance
     *
     * @param throwableClass the exception extends {@link Throwable}
     * @param template       the message template of exception
     * @param params         the message template params of exception
     * @return {@link Assert} instance
     */
    public static Assert of(Class<? extends Throwable> throwableClass, String template, Object... params) {
        return of().setMessage(template, params).setThrowable(throwableClass);
    }

    /**
     * throws if the checked element is null
     *
     * @param input the checked element
     * @see Nil#isNull(Object)
     */
    public void throwsIfNull(Object input) {
        Action.ifNull(input).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_NULL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is zero value, the zero value is {@code null} or {@code false}
     *
     * @param input the checked element
     * @see Nil#isZeroValue(Boolean)
     */
    public void throwsIfZeroValue(Boolean input) {
        Action.ifZeroValue(input).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_ZERO_VALUE_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is zero value, the zero value is {@code null} or {@code 0}
     *
     * @param input the checked element
     * @see Nil#isZeroValue(Number)
     */
    public void throwsIfZeroValue(Number input) {
        Action.ifZeroValue(input).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_ZERO_VALUE_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is zero value, the zero value is {@code null} or {@code ""}
     *
     * @param input the checked element
     * @see Nil#isZeroValue(CharSequence)
     */
    public void throwsIfZeroValue(CharSequence input) {
        Action.ifZeroValue(input).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_ZERO_VALUE_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @see Nil#isEmpty(byte[])
     */
    public void throwsIfEmpty(byte[] inputs) {
        Action.ifEmpty(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @see Nil#isEmpty(short[])
     */
    public void throwsIfEmpty(short[] inputs) {
        Action.ifEmpty(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @see Nil#isEmpty(int[])
     */
    public void throwsIfEmpty(int[] inputs) {
        Action.ifEmpty(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @see Nil#isEmpty(long[])
     */
    public void throwsIfEmpty(long[] inputs) {
        Action.ifEmpty(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @see Nil#isEmpty(float[])
     */
    public void throwsIfEmpty(float[] inputs) {
        Action.ifEmpty(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @see Nil#isEmpty(double[])
     */
    public void throwsIfEmpty(double[] inputs) {
        Action.ifEmpty(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @see Nil#isEmpty(boolean[])
     */
    public void throwsIfEmpty(boolean[] inputs) {
        Action.ifEmpty(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @see Nil#isEmpty(char[])
     */
    public void throwsIfEmpty(char[] inputs) {
        Action.ifEmpty(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @param <T>    the checked element type
     * @see Nil#isEmpty(Object[])
     */
    public <T> void throwsIfEmpty(T[] inputs) {
        Action.<Void>infer(Nil.isEmpty(inputs)).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @see Nil#isEmpty(Iterator)
     */
    public void throwsIfEmpty(Iterator<?> inputs) {
        Action.ifEmpty(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @see Nil#isEmpty(Iterable)
     */
    public void throwsIfEmpty(Iterable<?> inputs) {
        Action.ifEmpty(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @see Nil#isEmpty(Enumeration)
     */
    public void throwsIfEmpty(Enumeration<?> inputs) {
        Action.ifEmpty(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @see Nil#isEmpty(Map)
     */
    public void throwsIfEmpty(Map<?, ?> inputs) {
        Action.ifEmpty(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is empty string
     *
     * @param input the checked element
     * @see Nil#isEmpty(CharSequence)
     */
    public void throwsIfEmpty(CharSequence input) {
        Action.ifEmpty(input).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is blank string
     *
     * @param input the checked element
     * @see Nil#isBlank(CharSequence)
     */
    public void throwsIfBlank(CharSequence input) {
        Action.ifBlank(input).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_BLANK_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is true
     *
     * @param input the checked element
     * @see Booleans#isTrue(Boolean)
     */
    public void throwsIfTrue(Boolean input) {
        Action.ifTrue(input).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.FALSE_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is false
     *
     * @param input the checked element
     * @see Booleans#isFalse(Boolean)
     */
    public void throwsIfFalse(Boolean input) {
        Action.ifFalse(input).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.TRUE_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is not {@code null} and {@code > 0}
     *
     * @param input the checked element
     * @see Numbers#isPositive(Number)
     */
    public void throwsIfPositive(Number input) {
        Action.ifPositive(input).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_POSITIVE_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if any checked element is null
     *
     * @param inputs the checked elements
     * @see Nil#isAnyNull(Number...)
     */
    public void throwsIfAnyNull(Number... inputs) {
        Action.ifAnyNull(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_NULL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if any checked element is null
     *
     * @param inputs the checked elements
     * @see Nil#isAnyNull(Enum[])
     */
    public void throwsIfAnyNull(Enum<?>... inputs) {
        Action.ifAnyNull(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_NULL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if any checked element is null
     *
     * @param inputs the checked elements
     * @see Nil#isAnyNull(Boolean...)
     */
    public void throwsIfAnyNull(Boolean... inputs) {
        Action.ifAnyNull(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_NULL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if any checked element is null
     *
     * @param inputs the checked elements
     * @see Nil#isAnyNull(Object...)
     */
    public void throwsIfAnyNull(Object... inputs) {
        Action.ifAnyNull(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_NULL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if any checked element is null or zero size
     *
     * @param inputs the checked elements
     * @see Nil#isAnyEmpty(Iterator[])
     */
    public void throwsIfAnyEmpty(Iterator<?>... inputs) {
        Action.ifAnyEmpty(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if any checked element is null or zero size
     *
     * @param inputs the checked elements
     * @see Nil#isAnyEmpty(Iterable[])
     */
    public void throwsIfAnyEmpty(Iterable<?>... inputs) {
        Action.ifAnyEmpty(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if any checked element is null or zero size
     *
     * @param inputs the checked elements
     * @see Nil#isAnyEmpty(Enumeration[])
     */
    public void throwsIfAnyEmpty(Enumeration<?>... inputs) {
        Action.ifAnyEmpty(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if any checked element is null or zero size
     *
     * @param inputs the checked elements
     * @see Nil#isAnyEmpty(Map[])
     */
    public void throwsIfAnyEmpty(Map<?, ?>... inputs) {
        Action.ifAnyEmpty(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if any checked element is empty string
     *
     * @param inputs the checked elements
     * @see Nil#isAnyEmpty(CharSequence...)
     */
    public void throwsIfAnyEmpty(CharSequence... inputs) {
        Action.ifAnyEmpty(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if any checked element is blank string
     *
     * @param inputs the checked elements
     * @see Nil#isAnyBlank(CharSequence...)
     */
    public void throwsIfAnyBlank(CharSequence... inputs) {
        Action.ifAnyBlank(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_BLANK_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if any checked element is true
     *
     * @param inputs the checked elements
     * @see Booleans#isAnyTrue(Boolean...)
     */
    public void throwsIfAnyTrue(Boolean... inputs) {
        Action.ifAnyTrue(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.FALSE_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if any checked element is false
     *
     * @param inputs the checked elements
     * @see Booleans#isAnyFalse(Boolean...)
     */
    public void throwsIfAnyFalse(Boolean... inputs) {
        Action.ifAnyFalse(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.TRUE_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if any checked element is not {@code null} and {@code > 0}
     *
     * @param inputs the checked elements
     * @see Numbers#isAnyPositive(Number...)
     */
    public void throwsIfAnyPositive(Number... inputs) {
        Action.ifAnyPositive(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_POSITIVE_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are null
     *
     * @param inputs the checked elements
     * @see Nil#isAllNull(Number...)
     */
    public void throwsIfAllNull(Number... inputs) {
        Action.ifAllNull(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_NULL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are null
     *
     * @param inputs the checked elements
     * @see Nil#isAllNull(Enum[])
     */
    public void throwsIfAllNull(Enum<?>... inputs) {
        Action.ifAllNull(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_NULL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are null
     *
     * @param inputs the checked elements
     * @see Nil#isAllNull(Boolean...)
     */
    public void throwsIfAllNull(Boolean... inputs) {
        Action.ifAllNull(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_NULL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are null
     *
     * @param inputs the checked elements
     * @see Nil#isAllNull(Object...)
     */
    public void throwsIfAllNull(Object... inputs) {
        Action.ifAllNull(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_NULL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are null or zero size
     *
     * @param inputs the checked elements
     * @see Nil#isAllEmpty(Iterator[])
     */
    public void throwsIfAllEmpty(Iterator<?>... inputs) {
        Action.ifAllEmpty(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are null or zero size
     *
     * @param inputs the checked elements
     * @see Nil#isAllEmpty(Iterable[])
     */
    public void throwsIfAllEmpty(Iterable<?>... inputs) {
        Action.ifAllEmpty(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are null or zero size
     *
     * @param inputs the checked elements
     * @see Nil#isAllEmpty(Enumeration[])
     */
    public void throwsIfAllEmpty(Enumeration<?>... inputs) {
        Action.ifAllEmpty(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are null or zero size
     *
     * @param inputs the checked elements
     * @see Nil#isAllEmpty(Map[])
     */
    public void throwsIfAllEmpty(Map<?, ?>... inputs) {
        Action.ifAllEmpty(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are all empty string
     *
     * @param inputs the checked elements
     * @see Nil#isAllEmpty(CharSequence...)
     */
    public void throwsIfAllEmpty(CharSequence... inputs) {
        Action.ifAllEmpty(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are all blank string
     *
     * @param inputs the checked elements
     * @see Nil#isAllBlank(CharSequence...)
     */
    public void throwsIfAllBlank(CharSequence... inputs) {
        Action.ifAllBlank(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_BLANK_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are true
     *
     * @param inputs the checked elements
     * @see Booleans#isAllTrue(Boolean...)
     */
    public void throwsIfAllTrue(Boolean... inputs) {
        Action.ifAllTrue(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.FALSE_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are false
     *
     * @param inputs the checked elements
     * @see Booleans#isAllFalse(Boolean...)
     */
    public void throwsIfAllFalse(Boolean... inputs) {
        Action.ifAllFalse(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.TRUE_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are not {@code null} and {@code > 0}
     *
     * @param inputs the checked elements
     * @see Numbers#isAllPositive(Number...)
     */
    public void throwsIfAllPositive(Number... inputs) {
        Action.ifAllPositive(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_POSITIVE_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @see Comparators#equals(long, long)
     */
    public void throwsIfEquals(long input, long comparedElement) {
        Action.ifEquals(input, comparedElement).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @see Comparators#equals(float, float)
     */
    public void throwsIfEquals(float input, float comparedElement) {
        Action.ifEquals(input, comparedElement).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @see Comparators#equals(double, double)
     */
    public void throwsIfEquals(double input, double comparedElement) {
        Action.ifEquals(input, comparedElement).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @see Comparators#equals(BigDecimal, BigDecimal)
     */
    public void throwsIfEquals(BigDecimal input, BigDecimal comparedElement) {
        Action.ifEquals(input, comparedElement).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @see Comparators#equals(Number, Number)
     */
    public void throwsIfEquals(Number input, Number comparedElement) {
        Action.ifEquals(input, comparedElement).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element equals any compared element
     *
     * @param input            the checked element
     * @param comparedElements the compared elements
     * @see Comparators#equals(CharSequence, CharSequence...)
     */
    public void throwsIfEquals(CharSequence input, CharSequence... comparedElements) {
        Action.ifEquals(input, comparedElements).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared elements
     * @see Comparators#equals(byte[], byte[])
     */
    public void throwsIfEquals(byte[] input, byte[] comparedElement) {
        Action.ifEquals(input, comparedElement).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared elements
     * @see Comparators#equals(short[], short[])
     */
    public void throwsIfEquals(short[] input, short[] comparedElement) {
        Action.ifEquals(input, comparedElement).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared elements
     * @see Comparators#equals(int[], int[])
     */
    public void throwsIfEquals(int[] input, int[] comparedElement) {
        Action.ifEquals(input, comparedElement).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared elements
     * @see Comparators#equals(long[], long[])
     */
    public void throwsIfEquals(long[] input, long[] comparedElement) {
        Action.ifEquals(input, comparedElement).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared elements
     * @see Comparators#equals(float[], float[])
     */
    public void throwsIfEquals(float[] input, float[] comparedElement) {
        Action.ifEquals(input, comparedElement).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared elements
     * @see Comparators#equals(double[], double[])
     */
    public void throwsIfEquals(double[] input, double[] comparedElement) {
        Action.ifEquals(input, comparedElement).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared elements
     * @see Comparators#equals(boolean[], boolean[])
     */
    public void throwsIfEquals(boolean[] input, boolean[] comparedElement) {
        Action.ifEquals(input, comparedElement).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared elements
     * @see Comparators#equals(char[], char[])
     */
    public void throwsIfEquals(char[] input, char[] comparedElement) {
        Action.ifEquals(input, comparedElement).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared elements
     * @see Comparators#equals(Object[], Object[])
     */
    public void throwsIfEquals(Object[] input, Object[] comparedElement) {
        Action.ifEquals(input, comparedElement).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element equals the compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @see Comparators#equals(Object, Object)
     */
    public void throwsIfEquals(Object input, Object comparedElement) {
        Action.ifEquals(input, comparedElement).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element equals any compared element
     *
     * @param input            the checked element
     * @param comparedElements the compared elements
     * @see Comparators#equalsAny(Object, Object...)
     */
    public void throwsIfEqualsAny(Object input, Object... comparedElements) {
        Action.ifEqualsAny(input, comparedElements).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element equals any compared element
     *
     * @param input            the checked element
     * @param comparedElements the compared elements
     * @see Comparators#equalsAll(Object, Object...)
     */
    public void throwsIfEqualsAll(Object input, Object... comparedElements) {
        Action.ifEqualsAll(input, comparedElements).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element equals any compared element ignore case
     *
     * @param input            the checked element
     * @param comparedElements the compared elements
     * @see Comparators#equalsIgnoreCase(CharSequence, CharSequence...)
     */
    public void throwsIfEqualsIgnoreCase(CharSequence input, CharSequence... comparedElements) {
        Action.ifEqualsIgnoreCase(input, comparedElements).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NOT_EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is not null
     *
     * @param input the checked element
     * @see Nil#isNotNull(Object)
     */
    public void throwsIfNotNull(Object input) {
        Action.ifNotNull(input).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NULL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is not zero value, the zero value is {@code null} or {@code false}
     *
     * @param input the checked element
     * @see Nil#isNotZeroValue(Boolean)
     */
    public void throwsIfNotZeroValue(Boolean input) {
        Action.ifNotZeroValue(input).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.ZERO_VALUE_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is not zero value, the zero value is {@code null} or {@code 0}
     *
     * @param input the checked element
     * @see Nil#isNotZeroValue(Number)
     */
    public void throwsIfNotZeroValue(Number input) {
        Action.ifNotZeroValue(input).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.ZERO_VALUE_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is not zero value, the zero value is {@code null} or {@code ""}
     *
     * @param input the checked element
     * @see Nil#isNotZeroValue(CharSequence)
     */
    public void throwsIfNotZeroValue(CharSequence input) {
        Action.ifNotZeroValue(input).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.ZERO_VALUE_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is not null and not zero size
     *
     * @param inputs the checked elements
     * @see Nil#isNotEmpty(byte[])
     */
    public void throwsIfNotEmpty(byte[] inputs) {
        Action.ifNotEmpty(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is not null and not zero size
     *
     * @param inputs the checked elements
     * @see Nil#isNotEmpty(short[])
     */
    public void throwsIfNotEmpty(short[] inputs) {
        Action.ifNotEmpty(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is not null and not zero size
     *
     * @param inputs the checked elements
     * @see Nil#isNotEmpty(int[])
     */
    public void throwsIfNotEmpty(int[] inputs) {
        Action.ifNotEmpty(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is not null and not zero size
     *
     * @param inputs the checked elements
     * @see Nil#isNotEmpty(long[])
     */
    public void throwsIfNotEmpty(long[] inputs) {
        Action.ifNotEmpty(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is not null and not zero size
     *
     * @param inputs the checked elements
     * @see Nil#isNotEmpty(float[])
     */
    public void throwsIfNotEmpty(float[] inputs) {
        Action.ifNotEmpty(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is not null and not zero size
     *
     * @param inputs the checked elements
     * @see Nil#isNotEmpty(double[])
     */
    public void throwsIfNotEmpty(double[] inputs) {
        Action.ifNotEmpty(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is not null and not zero size
     *
     * @param inputs the checked elements
     * @see Nil#isNotEmpty(boolean[])
     */
    public void throwsIfNotEmpty(boolean[] inputs) {
        Action.ifNotEmpty(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is not null and not zero size
     *
     * @param inputs the checked elements
     * @see Nil#isNotEmpty(char[])
     */
    public void throwsIfNotEmpty(char[] inputs) {
        Action.ifNotEmpty(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is not null and not zero size
     *
     * @param inputs the checked elements
     * @param <T>    the checked element type
     * @see Nil#isNotEmpty(Object[])
     */
    public <T> void throwsIfNotEmpty(T[] inputs) {
        Action.<Void>infer(Nil.isNotEmpty(inputs)).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is not null and not zero size
     *
     * @param inputs the checked elements
     * @see Nil#isNotEmpty(Iterator)
     */
    public void throwsIfNotEmpty(Iterator<?> inputs) {
        Action.ifNotEmpty(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is not null and not zero size
     *
     * @param inputs the checked elements
     * @see Nil#isNotEmpty(Iterable)
     */
    public void throwsIfNotEmpty(Iterable<?> inputs) {
        Action.ifNotEmpty(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is not null and not zero size
     *
     * @param inputs the checked elements
     * @see Nil#isNotEmpty(Enumeration)
     */
    public void throwsIfNotEmpty(Enumeration<?> inputs) {
        Action.ifNotEmpty(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is not null and not zero size
     *
     * @param inputs the checked elements
     * @see Nil#isNotEmpty(Map)
     */
    public void throwsIfNotEmpty(Map<?, ?> inputs) {
        Action.ifNotEmpty(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is not empty string
     *
     * @param input the checked element
     * @see Nil#isNotEmpty(CharSequence)
     */
    public void throwsIfNotEmpty(CharSequence input) {
        Action.ifNotEmpty(input).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is not blank string
     *
     * @param input the checked element
     * @see Nil#isNotBlank(CharSequence)
     */
    public void throwsIfNotBlank(CharSequence input) {
        Action.ifNotBlank(input).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.BLANK_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is not {@code null} and {@code <= 0}
     *
     * @param input the checked element
     * @see Numbers#isNotPositive(Number)
     */
    public void throwsIfNotPositive(Number input) {
        Action.ifNotPositive(input).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.POSITIVE_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are not null
     *
     * @param inputs the checked elements
     * @see Nil#isAllNotNull(Number...)
     */
    public void throwsIfAllNotNull(Number... inputs) {
        Action.ifAllNotNull(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NULL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are not null
     *
     * @param inputs the checked elements
     * @see Nil#isAllNotNull(Enum[])
     */
    public void throwsIfAllNotNull(Enum<?>... inputs) {
        Action.ifAllNotNull(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NULL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are not null
     *
     * @param inputs the checked elements
     * @see Nil#isAllNotNull(Boolean...)
     */
    public void throwsIfAllNotNull(Boolean... inputs) {
        Action.ifAllNotNull(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NULL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are not null
     *
     * @param inputs the checked elements
     * @see Nil#isAllNotNull(Object...)
     */
    public void throwsIfAllNotNull(Object... inputs) {
        Action.ifAllNotNull(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.NULL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are not null and not zero size
     *
     * @param inputs the checked elements
     * @see Nil#isAllNotEmpty(Iterator[])
     */
    public void throwsIfAllNotEmpty(Iterator<?>... inputs) {
        Action.ifAllNotEmpty(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are not null and not zero size
     *
     * @param inputs the checked elements
     * @see Nil#isAllNotEmpty(Iterable[])
     */
    public void throwsIfAllNotEmpty(Iterable<?>... inputs) {
        Action.ifAllNotEmpty(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are not null and not zero size
     *
     * @param inputs the checked elements
     * @see Nil#isAllNotEmpty(Enumeration[])
     */
    public void throwsIfAllNotEmpty(Enumeration<?>... inputs) {
        Action.ifAllNotEmpty(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are not null and not zero size
     *
     * @param inputs the checked elements
     * @see Nil#isAllNotEmpty(Map[])
     */
    public void throwsIfAllNotEmpty(Map<?, ?>... inputs) {
        Action.ifAllNotEmpty(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are not empty string
     *
     * @param inputs the checked elements
     * @see Nil#isAllNotEmpty(CharSequence...)
     */
    public void throwsIfAllNotEmpty(CharSequence... inputs) {
        Action.ifAllNotEmpty(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are not blank string
     *
     * @param inputs the checked elements
     * @see Nil#isAllNotBlank(CharSequence...)
     */
    public void throwsIfAllNotBlank(CharSequence... inputs) {
        Action.ifAllNotBlank(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.BLANK_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are not {@code null} and {@code <= 0}
     *
     * @param inputs the checked elements
     * @see Numbers#isAllNotPositive(Number...)
     */
    public void throwsIfAllNotPositive(Number... inputs) {
        Action.ifAllNotPositive(inputs).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.POSITIVE_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @see Comparators#notEquals(long, long)
     */
    public void throwsIfNotEquals(long input, long comparedElement) {
        Action.ifNotEquals(input, comparedElement).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @see Comparators#notEquals(float, float)
     */
    public void throwsIfNotEquals(float input, float comparedElement) {
        Action.ifNotEquals(input, comparedElement).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @see Comparators#notEquals(double, double)
     */
    public void throwsIfNotEquals(double input, double comparedElement) {
        Action.ifNotEquals(input, comparedElement).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @see Comparators#notEquals(BigDecimal, BigDecimal)
     */
    public void throwsIfNotEquals(BigDecimal input, BigDecimal comparedElement) {
        Action.ifNotEquals(input, comparedElement).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @see Comparators#notEquals(Number, Number)
     */
    public void throwsIfNotEquals(Number input, Number comparedElement) {
        Action.ifNotEquals(input, comparedElement).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element not equals all compared elements
     *
     * @param input            the checked element
     * @param comparedElements the compared elements
     * @see Comparators#notEquals(CharSequence, CharSequence...)
     */
    public void throwsIfNotEquals(CharSequence input, CharSequence... comparedElements) {
        Action.ifNotEquals(input, comparedElements).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @see Comparators#notEquals(byte[], byte[])
     */
    public void throwsIfNotEquals(byte[] input, byte[] comparedElement) {
        Action.ifNotEquals(input, comparedElement).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @see Comparators#notEquals(short[], short[])
     */
    public void throwsIfNotEquals(short[] input, short[] comparedElement) {
        Action.ifNotEquals(input, comparedElement).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @see Comparators#notEquals(int[], int[])
     */
    public void throwsIfNotEquals(int[] input, int[] comparedElement) {
        Action.ifNotEquals(input, comparedElement).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @see Comparators#notEquals(long[], long[])
     */
    public void throwsIfNotEquals(long[] input, long[] comparedElement) {
        Action.ifNotEquals(input, comparedElement).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @see Comparators#notEquals(float[], float[])
     */
    public void throwsIfNotEquals(float[] input, float[] comparedElement) {
        Action.ifNotEquals(input, comparedElement).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @see Comparators#notEquals(double[], double[])
     */
    public void throwsIfNotEquals(double[] input, double[] comparedElement) {
        Action.ifNotEquals(input, comparedElement).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @see Comparators#notEquals(boolean[], boolean[])
     */
    public void throwsIfNotEquals(boolean[] input, boolean[] comparedElement) {
        Action.ifNotEquals(input, comparedElement).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @see Comparators#notEquals(char[], char[])
     */
    public void throwsIfNotEquals(char[] input, char[] comparedElement) {
        Action.ifNotEquals(input, comparedElement).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @see Comparators#notEquals(Object[], Object[])
     */
    public void throwsIfNotEquals(Object[] input, Object[] comparedElement) {
        Action.ifNotEquals(input, comparedElement).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element not equals the compared elements
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @see Comparators#notEquals(Object, Object)
     */
    public void throwsIfNotEquals(Object input, Object comparedElement) {
        Action.ifNotEquals(input, comparedElement).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element not equals all compared elements
     *
     * @param input            the checked element
     * @param comparedElements the compared elements
     * @see Comparators#notEquals(Object, Object...)
     */
    public void throwsIfNotEquals(Object input, Object... comparedElements) {
        Action.ifNotEquals(input, comparedElements).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element not equals all compared elements ignore case
     *
     * @param input            the checked element
     * @param comparedElements the compared elements
     * @see Comparators#notEquals(CharSequence, CharSequence...)
     */
    public void throwsIfNotEqualsIgnoreCase(CharSequence input, CharSequence... comparedElements) {
        Action.ifNotEqualsIgnoreCase(input, comparedElements).then(() -> {
            this.messageTemplate = Objects.setIfNull(this.messageTemplate, () -> AssertMessage.EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * collect exception info to throw
     *
     * @return ignore
     */
    @SneakyThrows
    @CanIgnoreReturnValue
    public Void doThrows() {
        throw Objects.setIfNull(this.throwable, () -> ClientException.class)
                .getConstructor(String.class)
                .newInstance(Nil.isNull(this.messageTemplateParams) ? this.messageTemplate : Strings.format(this.messageTemplate, this.messageTemplateParams));
    }

}
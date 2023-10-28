// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.lang.functional;

import cn.hutool.core.text.CharSequenceUtil;
import cn.srd.library.java.contract.constant.throwable.ExceptionMessage;
import cn.srd.library.java.contract.constant.web.HttpStatus;
import cn.srd.library.java.contract.model.throwable.AbstractRuntimeException;
import cn.srd.library.java.contract.model.throwable.RunningException;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.object.Objects;
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
     * the status of exception
     */
    @Setter private Integer status;

    /**
     * the message of exception
     */
    @Setter private String message;

    /**
     * the exception extends {@link AbstractRuntimeException}
     */
    @Setter private Class<? extends AbstractRuntimeException> throwable;

    /**
     * return {@link Assert} instance
     *
     * @return {@link Assert} instance
     */
    public static Assert of() {
        return new Assert();
    }

    /**
     * throws if the checked element is null
     *
     * @param input the checked element
     */
    public void throwsIfNull(Object input) {
        Action.ifNull(input).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_NULL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is null or zero size
     *
     * @param inputs the checked elements
     */
    public void throwsIfEmpty(byte[] inputs) {
        Action.ifEmpty(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is null or zero size
     *
     * @param inputs the checked elements
     */
    public void throwsIfEmpty(short[] inputs) {
        Action.ifEmpty(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is null or zero size
     *
     * @param inputs the checked elements
     */
    public void throwsIfEmpty(int[] inputs) {
        Action.ifEmpty(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is null or zero size
     *
     * @param inputs the checked elements
     */
    public void throwsIfEmpty(long[] inputs) {
        Action.ifEmpty(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is null or zero size
     *
     * @param inputs the checked elements
     */
    public void throwsIfEmpty(float[] inputs) {
        Action.ifEmpty(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is null or zero size
     *
     * @param inputs the checked elements
     */
    public void throwsIfEmpty(double[] inputs) {
        Action.ifEmpty(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is null or zero size
     *
     * @param inputs the checked elements
     */
    public void throwsIfEmpty(char[] inputs) {
        Action.ifEmpty(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is null or zero size
     *
     * @param inputs the checked elements
     */
    public void throwsIfEmpty(boolean[] inputs) {
        Action.ifEmpty(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @param <T>    the checked element type
     */
    public <T> void throwsIfEmpty(T[] inputs) {
        Action.<Void>infer(Nil.isEmpty(inputs)).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is null or zero size
     *
     * @param inputs the checked elements
     */
    public void throwsIfEmpty(Iterator<?> inputs) {
        Action.ifEmpty(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is null or zero size
     *
     * @param inputs the checked elements
     */
    public void throwsIfEmpty(Iterable<?> inputs) {
        Action.ifEmpty(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is null or zero size
     *
     * @param inputs the checked elements
     */
    public void throwsIfEmpty(Enumeration<?> inputs) {
        Action.ifEmpty(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is null or zero size
     *
     * @param inputs the checked elements
     */
    public void throwsIfEmpty(Map<?, ?> inputs) {
        Action.ifEmpty(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is {@link CharSequenceUtil#isEmpty(CharSequence)}
     *
     * @param input the checked element
     */
    public void throwsIfEmpty(CharSequence input) {
        Action.ifEmpty(input).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is {@link CharSequenceUtil#isBlank(CharSequence)}
     *
     * @param input the checked element
     */
    public void throwsIfBlank(CharSequence input) {
        Action.ifBlank(input).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_BLANK_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is true
     *
     * @param input the checked element
     */
    public void throwsIfTrue(Boolean input) {
        Action.ifTrue(input).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.FALSE_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is false
     *
     * @param input the checked element
     */
    public void throwsIfFalse(Boolean input) {
        Action.ifFalse(input).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.TRUE_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element > 0
     *
     * @param input the checked element
     */
    public void throwsIfPositive(Number input) {
        Action.ifPositive(input).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_POSITIVE_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if any checked element is null
     *
     * @param inputs the checked elements
     */
    public void throwsIfAnyNull(Number... inputs) {
        Action.ifAnyNull(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_NULL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if any checked element is null
     *
     * @param inputs the checked elements
     */
    public void throwsIfAnyNull(Enum<?>... inputs) {
        Action.ifAnyNull(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_NULL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if any checked element is null
     *
     * @param inputs the checked elements
     */
    public void throwsIfAnyNull(Boolean... inputs) {
        Action.ifAnyNull(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_NULL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if any checked element is null
     *
     * @param inputs the checked elements
     */
    public void throwsIfAnyNull(Object... inputs) {
        Action.ifAnyNull(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_NULL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if any checked element is null or zero size
     *
     * @param inputs the checked elements
     */
    public void throwsIfAnyEmpty(Iterator<?>... inputs) {
        Action.ifAnyEmpty(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if any checked element is null or zero size
     *
     * @param inputs the checked elements
     */
    public void throwsIfAnyEmpty(Iterable<?>... inputs) {
        Action.ifAnyEmpty(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if any checked element is null or zero size
     *
     * @param inputs the checked elements
     */
    public void throwsIfAnyEmpty(Enumeration<?>... inputs) {
        Action.ifAnyEmpty(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if any checked element is null or zero size
     *
     * @param inputs the checked elements
     */
    public void throwsIfAnyEmpty(Map<?, ?>... inputs) {
        Action.ifAnyEmpty(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if any checked element is {@link CharSequenceUtil#hasEmpty(CharSequence...)}
     *
     * @param inputs the checked elements
     */
    public void throwsIfAnyEmpty(CharSequence... inputs) {
        Action.ifAnyEmpty(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if any checked element is {@link CharSequenceUtil#hasBlank(CharSequence...)}
     *
     * @param inputs the checked elements
     */
    public void throwsIfAnyBlank(CharSequence... inputs) {
        Action.ifAnyBlank(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_BLANK_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if any checked element is true
     *
     * @param inputs the checked elements
     */
    public void throwsIfAnyTrue(Boolean... inputs) {
        Action.ifAnyTrue(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.FALSE_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if any checked element is false
     *
     * @param inputs the checked elements
     */
    public void throwsIfAnyFalse(Boolean... inputs) {
        Action.ifAnyFalse(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.TRUE_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if any checked element > 0
     *
     * @param inputs the checked elements
     */
    public void throwsIfAnyPositive(Number... inputs) {
        Action.ifAnyPositive(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_POSITIVE_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are null
     *
     * @param inputs the checked elements
     */
    public void throwsIfAllNull(Number... inputs) {
        Action.ifAllNull(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_NULL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are null
     *
     * @param inputs the checked elements
     */
    public void throwsIfAllNull(Enum<?>... inputs) {
        Action.ifAllNull(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_NULL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are null
     *
     * @param inputs the checked elements
     */
    public void throwsIfAllNull(Boolean... inputs) {
        Action.ifAllNull(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_NULL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are null
     *
     * @param inputs the checked elements
     */
    public void throwsIfAllNull(Object... inputs) {
        Action.ifAllNull(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_NULL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are null or zero size
     *
     * @param inputs the checked elements
     */
    public void throwsIfAllEmpty(Iterator<?>... inputs) {
        Action.ifAllEmpty(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are null or zero size
     *
     * @param inputs the checked elements
     */
    public void throwsIfAllEmpty(Iterable<?>... inputs) {
        Action.ifAllEmpty(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are null or zero size
     *
     * @param inputs the checked elements
     */
    public void throwsIfAllEmpty(Enumeration<?>... inputs) {
        Action.ifAllEmpty(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are null or zero size
     *
     * @param inputs the checked elements
     */
    public void throwsIfAllEmpty(Map<?, ?>... inputs) {
        Action.ifAllEmpty(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are {@link CharSequenceUtil#isAllEmpty(CharSequence...)}
     *
     * @param inputs the checked elements
     */
    public void throwsIfAllEmpty(CharSequence... inputs) {
        Action.ifAllEmpty(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are {@link CharSequenceUtil#isAllBlank(CharSequence...)}
     *
     * @param inputs the checked elements
     */
    public void throwsIfAllBlank(CharSequence... inputs) {
        Action.ifAllBlank(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_BLANK_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are true
     *
     * @param inputs the checked elements
     */
    public void throwsIfAllTrue(Boolean... inputs) {
        Action.ifAllTrue(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.FALSE_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are false
     *
     * @param inputs the checked elements
     */
    public void throwsIfAllFalse(Boolean... inputs) {
        Action.ifAllFalse(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.TRUE_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements > 0
     *
     * @param inputs the checked elements
     */
    public void throwsIfAllPositive(Number... inputs) {
        Action.ifAllPositive(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_POSITIVE_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     */
    public void throwsIfEquals(long input, long comparedElement) {
        Action.ifEquals(input, comparedElement).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     */
    public void throwsIfEquals(float input, float comparedElement) {
        Action.ifEquals(input, comparedElement).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     */
    public void throwsIfEquals(double input, double comparedElement) {
        Action.ifEquals(input, comparedElement).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     */
    public void throwsIfEquals(BigDecimal input, BigDecimal comparedElement) {
        Action.ifEquals(input, comparedElement).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     */
    public void throwsIfEquals(Number input, Number comparedElement) {
        Action.ifEquals(input, comparedElement).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element equals any compared element
     *
     * @param input            the checked element
     * @param comparedElements the compared elements
     */
    public void throwsIfEquals(CharSequence input, CharSequence... comparedElements) {
        Action.ifEquals(input, comparedElements).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element equals the compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     */
    public void throwsIfEquals(Object input, Object comparedElement) {
        Action.ifEquals(input, comparedElement).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element equals any compared element
     *
     * @param input            the checked element
     * @param comparedElements the compared elements
     */
    public void throwsIfEquals(Object input, Object... comparedElements) {
        Action.ifEquals(input, comparedElements).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element equals any compared element
     *
     * @param input            the checked element
     * @param comparedElements the compared elements
     */
    public void throwsIfEqualsAll(Object input, Object... comparedElements) {
        Action.ifEqualsAll(input, comparedElements).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element equals any compared element ignore case
     *
     * @param input            the checked element
     * @param comparedElements the compared elements
     */
    public void throwsIfEqualsIgnoreCase(CharSequence input, CharSequence... comparedElements) {
        Action.ifEqualsIgnoreCase(input, comparedElements).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NOT_EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is not null
     *
     * @param input the checked element
     */
    public void throwsIfNotNull(Object input) {
        Action.ifNotNull(input).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NULL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is not null and not zero size
     *
     * @param inputs the checked elements
     */
    public void throwsIfNotEmpty(byte[] inputs) {
        Action.ifNotEmpty(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is not null and not zero size
     *
     * @param inputs the checked elements
     */
    public void throwsIfNotEmpty(short[] inputs) {
        Action.ifNotEmpty(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is not null and not zero size
     *
     * @param inputs the checked elements
     */
    public void throwsIfNotEmpty(int[] inputs) {
        Action.ifNotEmpty(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is not null and not zero size
     *
     * @param inputs the checked elements
     */
    public void throwsIfNotEmpty(long[] inputs) {
        Action.ifNotEmpty(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is not null and not zero size
     *
     * @param inputs the checked elements
     */
    public void throwsIfNotEmpty(float[] inputs) {
        Action.ifNotEmpty(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is not null and not zero size
     *
     * @param inputs the checked elements
     */
    public void throwsIfNotEmpty(double[] inputs) {
        Action.ifNotEmpty(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is not null and not zero size
     *
     * @param inputs the checked elements
     */
    public void throwsIfNotEmpty(char[] inputs) {
        Action.ifNotEmpty(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is not null and not zero size
     *
     * @param inputs the checked elements
     */
    public void throwsIfNotEmpty(boolean[] inputs) {
        Action.ifNotEmpty(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is not null and not zero size
     *
     * @param inputs the checked elements
     * @param <T>    the checked element type
     */
    public <T> void throwsIfNotEmpty(T[] inputs) {
        Action.<Void>infer(Nil.isNotEmpty(inputs)).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is not null and not zero size
     *
     * @param inputs the checked elements
     */
    public void throwsIfNotEmpty(Iterator<?> inputs) {
        Action.ifNotEmpty(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is not null and not zero size
     *
     * @param inputs the checked elements
     */
    public void throwsIfNotEmpty(Iterable<?> inputs) {
        Action.ifNotEmpty(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is not null and not zero size
     *
     * @param inputs the checked elements
     */
    public void throwsIfNotEmpty(Enumeration<?> inputs) {
        Action.ifNotEmpty(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is not null and not zero size
     *
     * @param inputs the checked elements
     */
    public void throwsIfNotEmpty(Map<?, ?> inputs) {
        Action.ifNotEmpty(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is {@link CharSequenceUtil#isNotEmpty(CharSequence)}
     *
     * @param input the checked element
     */
    public void throwsIfNotEmpty(CharSequence input) {
        Action.ifNotEmpty(input).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element is {@link CharSequenceUtil#isNotBlank(CharSequence)}
     *
     * @param input the checked element
     */
    public void throwsIfNotBlank(CharSequence input) {
        Action.ifNotBlank(input).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.BLANK_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element <= 0
     *
     * @param input the checked element
     */
    public void throwsIfNotPositive(Number input) {
        Action.ifNotPositive(input).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.POSITIVE_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are not null
     *
     * @param inputs the checked elements
     */
    public void throwsIfAllNotNull(Number... inputs) {
        Action.ifAllNotNull(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NULL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are not null
     *
     * @param inputs the checked elements
     */
    public void throwsIfAllNotNull(Enum<?>... inputs) {
        Action.ifAllNotNull(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NULL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are not null
     *
     * @param inputs the checked elements
     */
    public void throwsIfAllNotNull(Boolean... inputs) {
        Action.ifAllNotNull(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NULL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are not null
     *
     * @param inputs the checked elements
     */
    public void throwsIfAllNotNull(Object... inputs) {
        Action.ifAllNotNull(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.NULL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are not null and not zero size
     *
     * @param inputs the checked elements
     */
    public void throwsIfAllNotEmpty(Iterator<?>... inputs) {
        Action.ifAllNotEmpty(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are not null and not zero size
     *
     * @param inputs the checked elements
     */
    public void throwsIfAllNotEmpty(Iterable<?>... inputs) {
        Action.ifAllNotEmpty(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are not null and not zero size
     *
     * @param inputs the checked elements
     */
    public void throwsIfAllNotEmpty(Enumeration<?>... inputs) {
        Action.ifAllNotEmpty(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are not null and not zero size
     *
     * @param inputs the checked elements
     */
    public void throwsIfAllNotEmpty(Map<?, ?>... inputs) {
        Action.ifAllNotEmpty(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are not {@link CharSequenceUtil#hasEmpty(CharSequence...)}
     *
     * @param inputs the checked elements
     */
    public void throwsIfAllNotEmpty(CharSequence... inputs) {
        Action.ifAllNotEmpty(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.EMPTY_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are not {@link CharSequenceUtil#hasBlank(CharSequence...)}
     *
     * @param inputs the checked elements
     */
    public void throwsIfAllNotBlank(CharSequence... inputs) {
        Action.ifAllNotBlank(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.BLANK_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if all checked elements are <= 0
     *
     * @param inputs the checked elements
     */
    public void throwsIfAllNotPositive(Number... inputs) {
        Action.ifAllNotPositive(inputs).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.POSITIVE_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     */
    public void throwsIfNotEquals(long input, long comparedElement) {
        Action.ifNotEquals(input, comparedElement).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     */
    public void throwsIfNotEquals(float input, float comparedElement) {
        Action.ifNotEquals(input, comparedElement).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     */
    public void throwsIfNotEquals(double input, double comparedElement) {
        Action.ifNotEquals(input, comparedElement).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     */
    public void throwsIfNotEquals(BigDecimal input, BigDecimal comparedElement) {
        Action.ifNotEquals(input, comparedElement).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     */
    public void throwsIfNotEquals(Number input, Number comparedElement) {
        Action.ifNotEquals(input, comparedElement).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element not equals all compared elements
     *
     * @param input            the checked element
     * @param comparedElements the compared elements
     */
    public void throwsIfNotEquals(CharSequence input, CharSequence... comparedElements) {
        Action.ifNotEquals(input, comparedElements).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element not equals the compared elements
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     */
    public void throwsIfNotEquals(Object input, Object comparedElement) {
        Action.ifNotEquals(input, comparedElement).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element not equals all compared elements
     *
     * @param input            the checked element
     * @param comparedElements the compared elements
     */
    public void throwsIfNotEquals(Object input, Object... comparedElements) {
        Action.ifNotEquals(input, comparedElements).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.EQUAL_CHECKED_MESSAGE);
            return this.doThrows();
        });
    }

    /**
     * throws if the checked element not equals all compared elements ignore case
     *
     * @param input            the checked element
     * @param comparedElements the compared elements
     */
    public void throwsIfNotEqualsIgnoreCase(CharSequence input, CharSequence... comparedElements) {
        Action.ifNotEqualsIgnoreCase(input, comparedElements).then(() -> {
            this.message = Objects.setIfNull(this.message, () -> ExceptionMessage.EQUAL_CHECKED_MESSAGE);
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
        this.status = Objects.setIfNull(this.status, HttpStatus.INTERNAL_ERROR::getStatus);
        this.throwable = Objects.setIfNull(this.throwable, () -> RunningException.class);
        throw this.throwable.getConstructor(String.class).newInstance(this.message).setStatus(this.status);
    }

}

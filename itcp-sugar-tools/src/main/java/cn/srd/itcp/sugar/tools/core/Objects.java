package cn.srd.itcp.sugar.tools.core;

import cn.hutool.core.util.ArrayUtil;
import cn.srd.itcp.sugar.tools.constant.CharPool;
import cn.srd.itcp.sugar.tools.core.asserts.Assert;
import cn.srd.itcp.sugar.tools.web.HttpStatus;
import org.springframework.lang.Nullable;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 通用工具
 *
 * @author wjm
 * @date 2020/7/8 16:11
 */
@SuppressWarnings("unchecked")
public class Objects {

    private Objects() {
    }

    //============================================= null、empty、blank、positive、boolean、equals start

    //============================================= null

    /**
     * Object 是否为 null
     *
     * @param object
     * @return
     */
    public static boolean isNull(Object object) {
        return null == object;
    }

    /**
     * Object 是否为 null，如果有一个为 null，返回 true
     *
     * @param objects
     * @return
     */
    public static boolean isNull(Object... objects) {
        if (null == objects) {
            return true;
        }
        for (Object object : objects) {
            if (isNull(object)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Object 是否全部为 null
     *
     * @param objects
     * @return
     */
    public static boolean isAllNull(Object... objects) {
        if (null == objects) {
            return true;
        }

        for (Object object : objects) {
            if (isNotNull(object)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Object 是否不为 null
     *
     * @param object
     * @return
     */
    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }

    /**
     * Object 是否全部不为 null
     *
     * @param objects
     * @return
     */
    public static boolean isNotNull(Object... objects) {
        return !isNull(objects);
    }

    //============================================= empty

    /**
     * CharSequence 是否为空，定义：
     * <pre>
     * 1、为 null
     * 2、为""
     * </pre>
     *
     * @param object
     * @return
     */
    public static boolean isEmpty(CharSequence object) {
        return null == object || object.length() == 0;
    }

    /**
     * CharSequence 是否为空，定义：
     * <pre>
     * 1、为 null
     * 2、为""
     * 如果有一个为空，则返回true
     * </pre>
     *
     * @param objects
     * @return
     */
    public static boolean isEmpty(CharSequence... objects) {
        if (null == objects) {
            return true;
        }
        for (CharSequence str : objects) {
            if (isEmpty(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Map 是否为空，定义：
     * <pre>
     * 1、为 null
     * 2、size为0
     * </pre>
     *
     * @param object
     * @return
     */
    public static boolean isEmpty(Map<?, ?> object) {
        return null == object || object.isEmpty();
    }

    /**
     * Map 是否为空，定义：
     * <pre>
     * 1、为 null
     * 2、size为0
     * 如果有一个为空，则返回true
     * </pre>
     *
     * @param objects
     * @return
     */
    public static boolean isEmpty(Map<?, ?>... objects) {
        if (null == objects) {
            return true;
        }
        for (Map<?, ?> map : objects) {
            if (isEmpty(map)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Iterable 是否为空，定义：
     * <pre>
     * 1、为 null
     * 2、size为0
     * </pre>
     *
     * @param object
     * @return
     */
    public static boolean isEmpty(Iterable<?> object) {
        return null == object || isEmpty(object.iterator());
    }

    /**
     * Iterable 是否为空，定义：
     * <pre>
     * 1、为 null
     * 2、size为0
     * 如果有一个为空，则返回true
     * </pre>
     *
     * @param objects
     * @return
     */
    public static boolean isEmpty(Iterable<?>... objects) {
        if (null == objects) {
            return true;
        }
        for (Iterable<?> iterable : objects) {
            if (isEmpty(iterable)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Iterator 是否为空，定义：
     * <pre>
     * 1、为 null
     * 2、size为0
     * </pre>
     *
     * @param object
     * @return
     */
    public static boolean isEmpty(Iterator<?> object) {
        return null == object || !object.hasNext();
    }

    /**
     * Iterator 是否为空，定义：
     * <pre>
     * 1、为 null
     * 2、size为0
     * 如果有一个为空，则返回true
     * </pre>
     *
     * @param objects
     * @return
     */
    public static boolean isEmpty(Iterator<?>... objects) {
        if (null == objects) {
            return true;
        }
        for (Iterator<?> iterator : objects) {
            if (isEmpty(iterator)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Object 是否为空：
     * <pre>
     * 1. CharSequence
     * 2. Map
     * 3. Iterable
     * 4. Iterator
     * 5. Array
     * 6. Object
     * </pre>
     *
     * @param object
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object object) {
        if (null == object) {
            return true;
        }

        if (object instanceof CharSequence) {
            return ((CharSequence) object).length() == 0;
        } else if (object instanceof Map) {
            return ((Map) object).isEmpty();
        } else if (object instanceof Iterable) {
            return !((Iterable) object).iterator().hasNext();
        } else if (object instanceof Iterator) {
            return !((Iterator) object).hasNext();
        } else if (object.getClass().isArray()) {
            return Array.getLength(object) == 0;
        }

        return false;
    }

    /**
     * Object 是否为空：
     * <pre>
     * 1. CharSequence
     * 2. Map
     * 3. Iterable
     * 4. Iterator
     * 5. Array
     * 6. Object
     *
     * 如果有一个为空，则返回true
     * </pre>
     *
     * @param objects
     * @return
     */
    public static boolean isEmpty(Object... objects) {
        if (null == objects) {
            return true;
        }

        for (Object object : objects) {
            if (isEmpty(object)) {
                return true;
            }
        }

        if (objects.getClass().isArray()) {
            return Array.getLength(objects) == 0;
        }

        return false;
    }

    /**
     * Array 是否为空，定义：
     * <pre>
     * 1、为 null
     * 2、长度为0
     * </pre>
     *
     * @param objects
     * @return
     */
    public static boolean isEmpty(byte[] objects) {
        return null == objects || objects.length == 0;
    }

    /**
     * Array 是否为空，定义：
     * <pre>
     * 1、为 null
     * 2、长度为0
     * </pre>
     *
     * @param objects
     * @return
     */
    public static boolean isEmpty(short[] objects) {
        return null == objects || objects.length == 0;
    }

    /**
     * Array 是否为空，定义：
     * <pre>
     * 1、为 null
     * 2、长度为0
     * </pre>
     *
     * @param objects
     * @return
     */
    public static boolean isEmpty(int[] objects) {
        return null == objects || objects.length == 0;
    }

    /**
     * Array 是否为空，定义：
     * <pre>
     * 1、为 null
     * 2、长度为0
     * </pre>
     *
     * @param objects
     * @return
     */
    public static boolean isEmpty(long[] objects) {
        return null == objects || objects.length == 0;
    }

    /**
     * Array 是否为空，定义：
     * <pre>
     * 1、为 null
     * 2、长度为0
     * </pre>
     *
     * @param objects
     * @return
     */
    public static boolean isEmpty(float[] objects) {
        return null == objects || objects.length == 0;
    }

    /**
     * Array 是否为空，定义：
     * <pre>
     * 1、为 null
     * 2、长度为0
     * </pre>
     *
     * @param objects
     * @return
     */
    public static boolean isEmpty(double[] objects) {
        return null == objects || objects.length == 0;
    }

    /**
     * Array 是否为空，定义：
     * <pre>
     * 1、为 null
     * 2、长度为0
     * </pre>
     *
     * @param objects
     * @return
     */
    public static boolean isEmpty(char[] objects) {
        return null == objects || objects.length == 0;
    }

    /**
     * Array 是否为空，定义：
     * <pre>
     * 1、为 null
     * 2、长度为0
     * </pre>
     *
     * @param objects
     * @return
     */
    public static boolean isEmpty(String[] objects) {
        return null == objects || objects.length == 0;
    }

    /**
     * Array 是否为空，定义：
     * <pre>
     * 1、为 null
     * 2、长度为0
     * </pre>
     *
     * @param objects
     * @return
     */
    public static boolean isEmpty(boolean[] objects) {
        return null == objects || objects.length == 0;
    }

    /**
     * CharSequence 是否全部为{@link #isEmpty(CharSequence)}
     *
     * @param objects
     * @return
     */
    public static boolean isAllEmpty(CharSequence... objects) {
        if (null == objects || objects.length == 0) {
            return true;
        }

        for (CharSequence object : objects) {
            if (isNotEmpty(object)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Map 是否全部为{@link Objects#isEmpty(Map)}
     *
     * @param objects
     * @return
     */
    public static boolean isAllEmpty(Map<?, ?>... objects) {
        if (null == objects || objects.length == 0) {
            return true;
        }

        for (Map<?, ?> object : objects) {
            if (isNotEmpty(object)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Iterable 是否全部为{@link #isEmpty(Iterable)}
     *
     * @param objects
     * @return
     */
    public static boolean isAllEmpty(Iterable<?>... objects) {
        if (null == objects || objects.length == 0) {
            return true;
        }

        for (Iterable<?> object : objects) {
            if (isNotEmpty(object)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Iterator 是否全部为{@link #isEmpty(Iterator)}
     *
     * @param objects
     * @return
     */
    public static boolean isAllEmpty(Iterator<?>... objects) {
        if (null == objects || objects.length == 0) {
            return true;
        }

        for (Iterator<?> object : objects) {
            if (isNotEmpty(object)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Object 是否全部为{@link #isEmpty(Object)}
     *
     * @param objects
     * @return
     */
    public static boolean isAllEmpty(Object... objects) {
        if (null == objects || objects.length == 0) {
            return true;
        }

        for (Object object : objects) {
            if (isNotEmpty(object)) {
                return false;
            }
        }
        return true;
    }

    /**
     * CharSequence 是否不为{@link #isEmpty(CharSequence)}
     *
     * @param object
     * @return
     */
    public static boolean isNotEmpty(CharSequence object) {
        return !isEmpty(object);
    }

    /**
     * CharSequence 是否全部不为{@link #isEmpty(CharSequence)}
     *
     * @param objects
     * @return
     */
    public static boolean isNotEmpty(CharSequence... objects) {
        return !isAllEmpty(objects);
    }

    /**
     * Map 是否不为{@link Objects#isEmpty(Map)}
     *
     * @param object
     * @return
     */
    public static boolean isNotEmpty(Map<?, ?> object) {
        return !isEmpty(object);
    }

    /**
     * Map 是否全部不为{@link Objects#isEmpty(Map)}
     *
     * @param objects
     * @return
     */
    public static boolean isNotEmpty(Map<?, ?>... objects) {
        return !isAllEmpty(objects);
    }

    /**
     * Iterable 是否不为{@link #isEmpty(Iterable)}
     *
     * @param object
     * @return
     */
    public static boolean isNotEmpty(Iterable<?> object) {
        return !isEmpty(object);
    }

    /**
     * Iterator 是否全部不为{@link #isEmpty(Iterator)}
     *
     * @param objects
     * @return
     */
    public static boolean isNotEmpty(Iterator<?>... objects) {
        return !isAllEmpty(objects);
    }

    /**
     * Iterator 是否不为{@link #isEmpty(Iterator)}
     *
     * @param object
     * @return
     */
    public static boolean isNotEmpty(Iterator<?> object) {
        return !isEmpty(object);
    }

    /**
     * Iterable 是否全部不为{@link #isEmpty(Iterable)}
     *
     * @param objects
     * @return
     */
    public static boolean isNotEmpty(Iterable<?>... objects) {
        return !isAllEmpty(objects);
    }

    /**
     * Object 是否不为{@link #isEmpty(Object)}
     *
     * @param object
     * @return
     */
    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }

    /**
     * Object 是否全部不为{@link #isEmpty(Object)}
     *
     * @param objects
     * @return
     */
    public static boolean isNotEmpty(Object... objects) {
        return !isAllEmpty(objects);
    }

    /**
     * byte[] 是否不为{@link #isEmpty(byte[])}
     *
     * @param objects
     * @return
     */
    public static boolean isNotEmpty(byte[] objects) {
        return !isEmpty(objects);
    }

    /**
     * short[] 是否不为{@link #isEmpty(short[])}
     *
     * @param objects
     * @return
     */
    public static boolean isNotEmpty(short[] objects) {
        return !isEmpty(objects);
    }

    /**
     * int[] 是否不为{@link #isEmpty(int[])}
     *
     * @param objects
     * @return
     */
    public static boolean isNotEmpty(int[] objects) {
        return !isEmpty(objects);
    }

    /**
     * long[] 是否不为{@link #isEmpty(long[])}
     *
     * @param objects
     * @return
     */
    public static boolean isNotEmpty(long[] objects) {
        return !isEmpty(objects);
    }

    /**
     * float[] 是否不为{@link #isEmpty(float[])}
     *
     * @param objects
     * @return
     */
    public static boolean isNotEmpty(float[] objects) {
        return !isEmpty(objects);
    }

    /**
     * double[] 是否不为{@link #isEmpty(double[])}
     *
     * @param objects
     * @return
     */
    public static boolean isNotEmpty(double[] objects) {
        return !isEmpty(objects);
    }

    /**
     * char[] 是否不为{@link #isEmpty(char[])}
     *
     * @param objects
     * @return
     */
    public static boolean isNotEmpty(char[] objects) {
        return !isEmpty(objects);
    }

    /**
     * String[] 是否不为{@link #isEmpty(String[])}
     *
     * @param objects
     * @return
     */
    public static boolean isNotEmpty(String[] objects) {
        return !isEmpty(objects);
    }

    /**
     * boolean[] 是否不为{@link #isEmpty(boolean[])}
     *
     * @param objects
     * @return
     */
    public static boolean isNotEmpty(boolean[] objects) {
        return !isEmpty(objects);
    }

    //============================================= blank

    /**
     * CharSequence 是否为空白，定义：
     * <pre>
     * 1、为 null
     * 2、为不可见字符（如空格）
     * 3、""
     * </pre>
     *
     * @param object
     * @return
     */
    public static boolean isBlank(CharSequence object) {
        int length;

        if ((null == object) || ((length = object.length()) == 0)) {
            return true;
        }

        for (int i = 0; i < length; i++) {
            // 只要有一个非空字符即为非空CharSequence
            int tmp = object.charAt(i);
            if (!(Character.isWhitespace(tmp) || Character.isSpaceChar(tmp) || tmp == '\ufeff' || tmp == '\u202a')) {
                return false;
            }
        }

        return true;
    }

    /**
     * CharSequence 是否为空白，定义：
     * <pre>
     * 1、为 null
     * 2、为不可见字符（如空格）
     * 3、""
     * 如果有一个为空，则返回true
     * </pre>
     *
     * @param objects
     * @return
     */
    public static boolean isBlank(CharSequence... objects) {
        if (null == objects) {
            return true;
        }
        for (CharSequence str : objects) {
            if (isBlank(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * CharSequence 是否全部为{@link #isBlank(CharSequence)}
     *
     * @param objects
     * @return
     */
    public static boolean isAllBlank(CharSequence... objects) {
        if (null == objects || objects.length == 0) {
            return true;
        }

        for (CharSequence object : objects) {
            if (isNotBlank(object)) {
                return false;
            }
        }
        return true;
    }

    /**
     * CharSequence 是否不为{@link #isBlank(CharSequence)}
     *
     * @param object
     * @return
     */
    public static boolean isNotBlank(CharSequence object) {
        return !isBlank(object);
    }

    /**
     * CharSequence 是否全部不为{@link #isBlank(CharSequence)}
     *
     * @param objects
     * @return
     */
    public static boolean isNotBlank(CharSequence... objects) {
        return !isAllBlank(objects);
    }

    //============================================= positive

    /**
     * 如果为正整数，返回 true
     *
     * @param number
     * @return
     */
    public static boolean isPositive(Number number) {
        return number != null && number.intValue() > 0;
    }

    /**
     * 如果全部为正整数，返回 true
     *
     * @param numbers
     * @return
     */
    public static boolean isAllPositive(Number... numbers) {
        if (null == numbers) {
            return false;
        }
        for (Number number : numbers) {
            if (isNotPositive(number)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 如果不为正整数，返回 true
     *
     * @param number
     * @return
     */
    public static boolean isNotPositive(Number number) {
        return !isPositive(number);
    }

    /**
     * 如果有一个不为正整数，返回 true
     *
     * @param numbers
     * @return
     */
    public static boolean isNotPositive(Number... numbers) {
        return !isAllPositive(numbers);
    }

    /**
     * 如果为 true，返回 true
     *
     * @param supplier
     * @return
     */
    public static boolean isTrue(Supplier<Boolean> supplier) {
        return isTrue(supplier.get());
    }

    //============================================= boolean

    /**
     * 如果为 true，返回 true
     *
     * @param isTrue
     * @return
     */
    public static boolean isTrue(Boolean isTrue) {
        return isNotEmpty(isTrue) && isTrue;
    }

    /**
     * 如果有一个为 true，返回 true
     *
     * @param areTrue
     * @return
     */
    public static boolean isTrue(Boolean... areTrue) {
        if (ArrayUtil.isEmpty(areTrue)) {
            return false;
        }
        for (Boolean isTrue : areTrue) {
            if (isTrue) {
                return true;
            }
        }
        return false;
    }

    /**
     * 如果全部为 true，返回 true
     *
     * @param areTrue
     * @return
     */
    public static boolean isAllTrue(Boolean... areTrue) {
        if (ArrayUtil.isEmpty(areTrue)) {
            return false;
        }
        for (Boolean isTrue : areTrue) {
            if (!isTrue) {
                return false;
            }
        }
        return true;
    }

    /**
     * 如果为 false，返回 true
     *
     * @param isFalse
     * @return
     */
    public static boolean isFalse(Boolean isFalse) {
        return isNotNull(isFalse) && !isFalse;
    }

    /**
     * 如果为 false，返回 true
     *
     * @param supplier
     * @return
     */
    public static boolean isFalse(Supplier<Boolean> supplier) {
        return isFalse(supplier.get());
    }

    /**
     * 如果为 false，返回 true
     *
     * @param predicate
     * @return
     */
    public static <T> boolean isFalse(T item, Predicate<T> predicate) {
        return !predicate.test(item);
    }

    /**
     * 如果有一个为 false，返回 true
     *
     * @param areFalse
     * @return
     */
    public static boolean isFalse(Boolean... areFalse) {
        if (ArrayUtil.isEmpty(areFalse)) {
            return false;
        }
        for (Boolean isFalse : areFalse) {
            if (!isFalse) {
                return true;
            }
        }
        return false;
    }

    /**
     * 如果全部为 false，返回 true
     *
     * @param areFalse
     * @return
     */
    public static boolean isAllFalse(Boolean... areFalse) {
        if (ArrayUtil.isEmpty(areFalse)) {
            return false;
        }
        for (Boolean isFalse : areFalse) {
            if (isFalse) {
                return false;
            }
        }
        return true;
    }

    //============================================= equals

    /**
     * 比较两个对象是否相同；安全的 null 值比较
     * <pre>
     * 相同的条件如下，满足一个则返回 true：
     * 1、obj1 == null && obj2 == null
     * 2、obj1.equals(obj2)
     * 3、BigDecimal 的比较，则为 0 == obj1.compareTo(obj2)
     * 4、数组的比较，则为数组长度和每个元素都相等
     * </pre>
     *
     * @param obj1
     * @param obj2
     * @return
     */
    public static boolean equals(@Nullable Object obj1, @Nullable Object obj2) {
        if (obj1 == obj2) {
            return true;
        }
        if (null == obj1 || null == obj2) {
            return false;
        }
        if (obj1.equals(obj2)) {
            return true;
        }
        if (obj1 instanceof BigDecimal && obj2 instanceof BigDecimal) {
            return 0 == ((BigDecimal) obj1).compareTo((BigDecimal) obj2);
        }
        if (obj1.getClass().isArray() && obj2.getClass().isArray()) {
            return ArrayUtil.equals(obj1, obj2);
        }
        return false;
    }

    /**
     * 比较第一个对象是否与后续对象相同，若第一个对象与后续对象有一个相同，返回 true，否则返回 false；
     * <pre>
     * 比较规则：{@link #equals(Object, Object)}
     * </pre>
     *
     * @param obj1
     * @param objs
     * @return
     * @see Objects#equals(Object, Object)
     */
    public static boolean equals(@Nullable Object obj1, @Nullable Object... objs) {
        if (obj1 == objs) {
            return true;
        }
        if (null == obj1 || null == objs) {
            return false;
        }
        for (Object objToCompare : objs) {
            if (obj1.equals(objToCompare)) {
                return true;
            }
            if (obj1 instanceof BigDecimal && objToCompare instanceof BigDecimal) {
                return 0 == ((BigDecimal) obj1).compareTo((BigDecimal) objToCompare);
            }
            if (obj1.getClass().isArray() && objToCompare.getClass().isArray()) {
                return ArrayUtil.equals(obj1, objToCompare);
            }
        }
        return false;
    }

    /**
     * 比较对象是否与集合元素是否相同，若对象与集合元素有一个相同，返回 true；<br>
     * 两者有一个为 null，返回false；
     *
     * @param obj1
     * @param objs
     * @return
     */
    public static <T> boolean collEquals(@Nullable T obj1, @Nullable Collection<T> objs) {
        if (null == obj1 || null == objs) {
            return false;
        }
        for (T obj2 : objs) {
            if (Objects.equals(obj1, obj2)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 比较两个对象是否不同，若不同返回 true，否则返回 false；安全的 null 值比较
     *
     * @param obj1
     * @param obj2
     * @return
     */
    public static boolean notEquals(@Nullable Object obj1, @Nullable Object obj2) {
        return !equals(obj1, obj2);
    }

    /**
     * 比较第一个对象是否与后续对象不同，若第一个对象与后续对象全部都不同，返回 true，否则返回 false；安全的 null 值比较
     *
     * @param obj1
     * @param objs
     * @return
     */
    public static boolean notEquals(@Nullable Object obj1, @Nullable Object... objs) {
        return !equals(obj1, objs);
    }

    /**
     * 若对象是与集合所有元素全部不同，返回 true；安全的 null 值比较
     *
     * @param obj1
     * @param objs
     * @return
     */
    public static <T> boolean collNotEquals(@Nullable T obj1, @Nullable Collection<T> objs) {
        if (null == obj1 || null == objs) {
            return true;
        }
        for (T obj2 : objs) {
            if (Objects.equals(obj1, obj2)) {
                return false;
            }
        }
        return true;
    }

    // null、empty、blank、positive、boolean、equals end =============================================

    //============================================= require start

    /**
     * 需要为 null
     *
     * @param object
     * @param <T>
     * @return
     */
    public static <T> T requireNull(T object) {
        Assert.NULL_NEED.throwsIfNotNull(object);
        return object;
    }

    /**
     * 需要为 null
     *
     * @param httpStatus
     * @param object
     * @return
     */
    public static <T> T requireNull(HttpStatus httpStatus, T object) {
        Assert.INSTANCE.set(httpStatus).throwsIfNotNull(object);
        return object;
    }

    /**
     * 需要为 null
     *
     * @param httpStatus
     * @param message
     * @param object
     * @return
     */
    public static <T> T requireNull(HttpStatus httpStatus, Supplier<String> message, T object) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfNotNull(object);
        return object;
    }

    /**
     * 需要为 null
     *
     * @param object
     * @param message
     * @param <T>
     * @return
     */
    public static <T> T requireNull(Supplier<String> message, T object) {
        Assert.INSTANCE.set(message).throwsIfNotNull(object);
        return object;
    }

    /**
     * 需要至少有一个为 null
     *
     * @param objects
     * @param <T>
     * @return
     */
    public static <T> void requireNull(T... objects) {
        Assert.NULL_NEED.throwsIfNotNull(objects);
    }

    /**
     * 需要至少有一个为 null
     *
     * @param httpStatus
     * @param objects
     * @return
     */
    public static <T> void requireNull(HttpStatus httpStatus, T... objects) {
        Assert.INSTANCE.set(httpStatus).throwsIfNotNull(objects);
    }

    /**
     * 需要至少有一个为 null
     *
     * @param httpStatus
     * @param message
     * @param objects
     * @return
     */
    public static <T> void requireNull(HttpStatus httpStatus, Supplier<String> message, T... objects) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfNotNull(objects);
    }

    /**
     * 需要至少有一个为 null
     *
     * @param objects
     * @param message
     * @param <T>
     * @return
     */
    public static <T> void requireNull(Supplier<String> message, T... objects) {
        Assert.INSTANCE.set(message).throwsIfNotNull(objects);
    }

    /**
     * 需要为 empty
     *
     * @param object
     * @param <T>
     * @return
     */
    public static <T> T requireEmpty(T object) {
        Assert.EMPTY_NEED.throwsIfNotEmpty(object);
        return object;
    }

    /**
     * 需要为 empty
     *
     * @param httpStatus
     * @param object
     * @return
     */
    public static <T> T requireEmpty(HttpStatus httpStatus, T object) {
        Assert.INSTANCE.set(httpStatus).throwsIfNotEmpty(object);
        return object;
    }

    /**
     * 需要为 empty
     *
     * @param httpStatus
     * @param message
     * @param object
     * @return
     */
    public static <T> T requireEmpty(HttpStatus httpStatus, Supplier<String> message, T object) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfNotEmpty(object);
        return object;
    }

    /**
     * 需要为 empty
     *
     * @param object
     * @param message
     * @param <T>
     * @return
     */
    public static <T> T requireEmpty(Supplier<String> message, T object) {
        Assert.INSTANCE.set(message).throwsIfNotEmpty(object);
        return object;
    }

    /**
     * 需要至少有一个为 empty
     *
     * @param objects
     * @param <T>
     * @return
     */
    public static <T> void requireEmpty(T... objects) {
        Assert.EMPTY_NEED.throwsIfNotEmpty(objects);
    }

    /**
     * 需要至少有一个为 empty
     *
     * @param httpStatus
     * @param objects
     * @return
     */
    public static <T> void requireEmpty(HttpStatus httpStatus, T... objects) {
        Assert.INSTANCE.set(httpStatus).throwsIfNotEmpty(objects);
    }

    /**
     * 需要至少有一个为 empty
     *
     * @param httpStatus
     * @param message
     * @param objects
     * @return
     */
    public static <T> void requireEmpty(HttpStatus httpStatus, Supplier<String> message, T... objects) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfNotEmpty(objects);
    }

    /**
     * 需要至少有一个为 empty
     *
     * @param objects
     * @param message
     * @param <T>
     * @return
     */
    public static <T> void requireEmpty(Supplier<String> message, T... objects) {
        Assert.INSTANCE.set(message).throwsIfNotEmpty(objects);
    }

    /**
     * 需要为 blank
     *
     * @param object
     * @return
     */
    public static String requireBlank(String object) {
        Assert.BLANK_NEED.throwsIfNotBlank(object);
        return object;
    }

    /**
     * 需要为 blank
     *
     * @param httpStatus
     * @param object
     * @return
     */
    public static String requireBlank(HttpStatus httpStatus, String object) {
        Assert.INSTANCE.set(httpStatus).throwsIfNotBlank(object);
        return object;
    }

    /**
     * 需要为 blank
     *
     * @param httpStatus
     * @param message
     * @param object
     * @return
     */
    public static String requireBlank(HttpStatus httpStatus, Supplier<String> message, String object) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfNotBlank(object);
        return object;
    }

    /**
     * 需要为 blank
     *
     * @param object
     * @param message
     * @return
     */
    public static String requireBlank(Supplier<String> message, String object) {
        Assert.INSTANCE.set(message).throwsIfNotBlank(object);
        return object;
    }

    /**
     * 需要至少有一个为 blank
     *
     * @param objects
     * @return
     */
    public static void requireBlank(String... objects) {
        Assert.BLANK_NEED.throwsIfNotBlank(objects);
    }

    /**
     * 需要至少有一个为 blank
     *
     * @param httpStatus
     * @param objects
     * @return
     */
    public static void requireBlank(HttpStatus httpStatus, String... objects) {
        Assert.INSTANCE.set(httpStatus).throwsIfNotBlank(objects);
    }

    /**
     * 需要至少有一个为 blank
     *
     * @param httpStatus
     * @param message
     * @param objects
     * @return
     */
    public static void requireBlank(HttpStatus httpStatus, Supplier<String> message, String... objects) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfNotBlank(objects);
    }

    /**
     * 需要至少有一个为 blank
     *
     * @param objects
     * @param message
     * @return
     */
    public static void requireBlank(Supplier<String> message, String... objects) {
        Assert.INSTANCE.set(message).throwsIfNotBlank(objects);
    }

    /**
     * 不可为 null
     *
     * @param object
     * @param <T>
     * @return
     */
    public static <T> T requireNotNull(T object) {
        Assert.NULL_CHECK.throwsIfNull(object);
        return object;
    }

    /**
     * 不可为 null
     *
     * @param httpStatus
     * @param object
     * @return
     */
    public static <T> T requireNotNull(HttpStatus httpStatus, T object) {
        Assert.INSTANCE.set(httpStatus).throwsIfNull(object);
        return object;
    }

    /**
     * 不可为 null
     *
     * @param httpStatus
     * @param message
     * @param object
     * @return
     */
    public static <T> T requireNotNull(HttpStatus httpStatus, Supplier<String> message, T object) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfNull(object);
        return object;
    }

    /**
     * 不可为 null
     *
     * @param message
     * @param object
     * @param <T>
     * @return
     */
    public static <T> T requireNotNull(Supplier<String> message, T object) {
        Assert.INSTANCE.set(message).throwsIfNull(object);
        return object;
    }

    /**
     * 不可为 null
     *
     * @param exception
     * @param object
     * @param <T>
     * @return
     */
    public static <T> T requireNotNull(Exception exception, T object) {
        Assert.INSTANCE.set(exception).throwsIfNull(object);
        return object;
    }

    /**
     * 需要至少有一个不为 null
     *
     * @param objects
     * @param <T>
     * @return
     */
    public static <T> void requireNotNull(T... objects) {
        Assert.NULL_CHECK.throwsIfAllNull(objects);
    }

    /**
     * 需要至少有一个不为 null
     *
     * @param httpStatus
     * @param objects
     * @return
     */
    public static <T> void requireNotNull(HttpStatus httpStatus, T... objects) {
        Assert.INSTANCE.set(httpStatus).throwsIfAllNull(objects);
    }

    /**
     * 需要至少有一个不为 null
     *
     * @param httpStatus
     * @param message
     * @param objects
     * @return
     */
    public static <T> void requireNotNull(HttpStatus httpStatus, Supplier<String> message, T... objects) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfAllNull(objects);
    }

    /**
     * 需要至少有一个不为 null
     *
     * @param objects
     * @param message
     * @param <T>
     * @return
     */
    public static <T> void requireNotNull(Supplier<String> message, T... objects) {
        Assert.INSTANCE.set(message).throwsIfAllNull(objects);
    }

    /**
     * 需要全部不为 null
     *
     * @param objects
     * @param <T>
     * @return
     */
    public static <T> void requireNonNull(T... objects) {
        Assert.NULL_NEED.throwsIfNull(objects);
    }

    /**
     * 需要全部不为 null
     *
     * @param httpStatus
     * @param objects
     * @return
     */
    public static <T> void requireNonNull(HttpStatus httpStatus, T... objects) {
        Assert.INSTANCE.set(httpStatus).throwsIfNull(objects);
    }

    /**
     * 需要全部不为 null
     *
     * @param httpStatus
     * @param message
     * @param objects
     * @return
     */
    public static <T> void requireNonNull(HttpStatus httpStatus, Supplier<String> message, T... objects) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfNull(objects);
    }

    /**
     * 需要全部不为 null
     *
     * @param objects
     * @param message
     * @param <T>
     * @return
     */
    public static <T> void requireNonNull(Supplier<String> message, T... objects) {
        Assert.INSTANCE.set(message).throwsIfNull(objects);
    }

    /**
     * 不可为 empty
     *
     * @param object
     * @param <T>
     * @return
     */
    public static <T> T requireNotEmpty(T object) {
        Assert.EMPTY_CHECK.throwsIfEmpty(object);
        return object;
    }

    /**
     * 不可为 empty
     *
     * @param httpStatus
     * @param object
     * @return
     */
    public static <T> T requireNotEmpty(HttpStatus httpStatus, T object) {
        Assert.INSTANCE.set(httpStatus).throwsIfEmpty(object);
        return object;
    }

    /**
     * 不可为 empty
     *
     * @param httpStatus
     * @param message
     * @param object
     * @return
     */
    public static <T> T requireNotEmpty(HttpStatus httpStatus, Supplier<String> message, T object) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfEmpty(object);
        return object;
    }

    /**
     * 不可为 empty
     *
     * @param message
     * @param object
     * @param <T>
     * @return
     */
    public static <T> T requireNotEmpty(Supplier<String> message, T object) {
        Assert.INSTANCE.set(message).throwsIfEmpty(object);
        return object;
    }

    /**
     * 不可为 empty
     *
     * @param exception
     * @param object
     * @param <T>
     * @return
     */
    public static <T> T requireNotEmpty(Exception exception, T object) {
        Assert.INSTANCE.set(exception).throwsIfEmpty(object);
        return object;
    }

    /**
     * 不可有一个为 empty
     *
     * @param objects
     * @param <T>
     * @return
     */
    public static <T> void requireNotEmpty(T... objects) {
        Assert.EMPTY_CHECK.throwsIfEmpty(objects);
    }

    /**
     * 不可有一个为 empty
     *
     * @param httpStatus
     * @param objects
     * @return
     */
    public static <T> void requireNotEmpty(HttpStatus httpStatus, T... objects) {
        Assert.INSTANCE.set(httpStatus).throwsIfEmpty(objects);
    }

    /**
     * 不可有一个为 empty
     *
     * @param httpStatus
     * @param message
     * @param objects
     * @return
     */
    public static <T> void requireNotEmpty(HttpStatus httpStatus, Supplier<String> message, T... objects) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfEmpty(objects);
    }

    /**
     * 不可有一个为 empty
     *
     * @param objects
     * @param message
     * @param <T>
     * @return
     */
    public static <T> void requireNotEmpty(Supplier<String> message, T... objects) {
        Assert.INSTANCE.set(message).throwsIfEmpty(objects);
    }

    /**
     * 不可全部为 empty
     *
     * @param objects
     * @param <T>
     */
    public static <T> void requireNonEmpty(T... objects) {
        Assert.EMPTY_CHECK.throwsIfAllEmpty(objects);
    }

    /**
     * 不可全部为 empty
     *
     * @param httpStatus
     * @param objects
     * @return
     */
    public static <T> void requireNonEmpty(HttpStatus httpStatus, T... objects) {
        Assert.INSTANCE.set(httpStatus).throwsIfAllEmpty(objects);
    }

    /**
     * 不可全部为 empty
     *
     * @param httpStatus
     * @param message
     * @param objects
     * @return
     */
    public static <T> void requireNonEmpty(HttpStatus httpStatus, Supplier<String> message, T... objects) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfAllEmpty(objects);
    }

    /**
     * 不可全部为 empty
     *
     * @param objects
     * @param message
     * @param <T>
     * @return
     */
    public static <T> void requireNonEmpty(Supplier<String> message, T... objects) {
        Assert.INSTANCE.set(message).throwsIfAllEmpty(objects);
    }

    /**
     * 不可为 blank
     *
     * @param object
     * @return
     */
    public static String requireNotBlank(String object) {
        Assert.BLANK_CHECK.throwsIfBlank(object);
        return object;
    }

    /**
     * 不可为 blank
     *
     * @param httpStatus
     * @param object
     * @return
     */
    public static String requireNotBlank(HttpStatus httpStatus, String object) {
        Assert.INSTANCE.set(httpStatus).throwsIfBlank(object);
        return object;
    }

    /**
     * 不可为 blank
     *
     * @param httpStatus
     * @param message
     * @param object
     * @return
     */
    public static String requireNotBlank(HttpStatus httpStatus, Supplier<String> message, String object) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfBlank(object);
        return object;
    }

    /**
     * 不可为 blank
     *
     * @param message
     * @param object
     * @return
     */
    public static String requireNotBlank(Supplier<String> message, String object) {
        Assert.INSTANCE.set(message).throwsIfBlank(object);
        return object;
    }

    /**
     * 不可为 blank
     *
     * @param exception
     * @param object
     * @return
     */
    public static String requireNotBlank(Exception exception, String object) {
        Assert.INSTANCE.set(exception).throwsIfBlank(object);
        return object;
    }

    /**
     * 不可有一个为 blank
     *
     * @param objects
     * @return
     */
    public static void requireNotBlank(String... objects) {
        Assert.BLANK_CHECK.throwsIfBlank(objects);
    }

    /**
     * 不可有一个为 blank
     *
     * @param httpStatus
     * @param objects
     * @return
     */
    public static void requireNotBlank(HttpStatus httpStatus, String... objects) {
        Assert.INSTANCE.set(httpStatus).throwsIfBlank(objects);
    }

    /**
     * 不可有一个为 blank
     *
     * @param httpStatus
     * @param message
     * @param objects
     * @return
     */
    public static void requireNotBlank(HttpStatus httpStatus, Supplier<String> message, String... objects) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfBlank(objects);
    }

    /**
     * 不可有一个为 blank
     *
     * @param message
     * @param objects
     * @return
     */
    public static void requireNotBlank(Supplier<String> message, String... objects) {
        Assert.INSTANCE.set(message).throwsIfBlank(objects);
    }

    /**
     * 不可有一个为 blank
     *
     * @param exception
     * @param objects
     * @return
     */
    public static void requireNotBlank(Exception exception, String... objects) {
        Assert.INSTANCE.set(exception).throwsIfBlank(objects);
    }

    /**
     * 不可全部为 blank
     *
     * @param objects
     * @return
     */
    public static void requireNonBlank(String... objects) {
        Assert.BLANK_CHECK.throwsIfAllBlank(objects);
    }

    /**
     * 不可全部为 blank
     *
     * @param httpStatus
     * @param objects
     * @return
     */
    public static void requireNonBlank(HttpStatus httpStatus, String... objects) {
        Assert.INSTANCE.set(httpStatus).throwsIfAllBlank(objects);
    }

    /**
     * 不可全部为 blank
     *
     * @param objects
     * @param message
     * @return
     */
    public static void requireNonBlank(Supplier<String> message, String... objects) {
        Assert.INSTANCE.set(message).throwsIfAllBlank(objects);
    }

    /**
     * 不可全部为 blank
     *
     * @param httpStatus
     * @param message
     * @param objects
     * @return
     */
    public static void requireNonBlank(HttpStatus httpStatus, Supplier<String> message, String... objects) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfAllBlank(objects);
    }

    /**
     * 需要为 true
     *
     * @param object
     * @return
     */
    public static Boolean requireTrue(Boolean object) {
        Assert.TRUE_NEED.throwsIfFalse(object);
        return object;
    }

    /**
     * 需要为 true
     *
     * @param httpStatus
     * @param object
     * @return
     */
    public static Boolean requireTrue(HttpStatus httpStatus, Boolean object) {
        Assert.INSTANCE.set(httpStatus).throwsIfFalse(object);
        return object;
    }

    /**
     * 需要为 true
     *
     * @param httpStatus
     * @param message
     * @param object
     * @return
     */
    public static Boolean requireTrue(HttpStatus httpStatus, Supplier<String> message, Boolean object) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfFalse(object);
        return object;
    }

    /**
     * 需要为 true
     *
     * @param message
     * @param object
     * @return
     */
    public static Boolean requireTrue(Supplier<String> message, Boolean object) {
        Assert.INSTANCE.set(message).throwsIfFalse(object);
        return object;
    }

    /**
     * 需要为 true
     *
     * @param exception
     * @param object
     * @return
     */
    public static Boolean requireTrue(Exception exception, Boolean object) {
        Assert.INSTANCE.set(exception).throwsIfFalse(object);
        return object;
    }

    /**
     * 需要至少有一个为 true
     *
     * @param object
     * @return
     */
    public static void requireTrue(Boolean... object) {
        Assert.TRUE_NEED.throwsIfAllFalse(object);
    }

    /**
     * 需要至少有一个为 true
     *
     * @param httpStatus
     * @param objects
     * @return
     */
    public static void requireTrue(HttpStatus httpStatus, Boolean... objects) {
        Assert.INSTANCE.set(httpStatus).throwsIfAllFalse(objects);
    }

    /**
     * 需要至少有一个为 true
     *
     * @param httpStatus
     * @param message
     * @param objects
     * @return
     */
    public static void requireTrue(HttpStatus httpStatus, Supplier<String> message, Boolean... objects) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfAllFalse(objects);
    }

    /**
     * 需要至少有一个为 true
     *
     * @param objects
     * @param message
     * @return
     */
    public static void requireTrue(Supplier<String> message, Boolean... objects) {
        Assert.INSTANCE.set(message).throwsIfAllFalse(objects);
    }

    /**
     * 需要全部为 true
     *
     * @param object
     * @return
     */
    public static void requireAllTrue(Boolean... object) {
        Assert.TRUE_NEED.throwsIfFalse(object);
    }

    /**
     * 需要全部为 true
     *
     * @param httpStatus
     * @param objects
     * @return
     */
    public static void requireAllTrue(HttpStatus httpStatus, Boolean... objects) {
        Assert.INSTANCE.set(httpStatus).throwsIfFalse(objects);
    }

    /**
     * 需要全部为 true
     *
     * @param httpStatus
     * @param message
     * @param objects
     * @return
     */
    public static void requireAllTrue(HttpStatus httpStatus, Supplier<String> message, Boolean... objects) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfFalse(objects);
    }

    /**
     * 需要全部为 true
     *
     * @param objects
     * @param message
     * @return
     */
    public static void requireAllTrue(Supplier<String> message, Boolean... objects) {
        Assert.INSTANCE.set(message).throwsIfFalse(objects);
    }

    /**
     * 需要为 false
     *
     * @param object
     * @return
     */
    public static Boolean requireFalse(Boolean object) {
        Assert.FALSE_NEED.throwsIfTrue(object);
        return object;
    }

    /**
     * 需要为 false
     *
     * @param httpStatus
     * @param object
     * @return
     */
    public static Boolean requireFalse(HttpStatus httpStatus, Boolean object) {
        Assert.INSTANCE.set(httpStatus).throwsIfTrue(object);
        return object;
    }

    /**
     * 需要为 false
     *
     * @param httpStatus
     * @param message
     * @param object
     * @return
     */
    public static Boolean requireFalse(HttpStatus httpStatus, Supplier<String> message, Boolean object) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfTrue(object);
        return object;
    }

    /**
     * 需要为 false
     *
     * @param message
     * @param object
     * @return
     */
    public static Boolean requireFalse(Supplier<String> message, Boolean object) {
        Assert.INSTANCE.set(message).throwsIfTrue(object);
        return object;
    }

    /**
     * 需要为 false
     *
     * @param exception
     * @param object
     * @return
     */
    public static Boolean requireFalse(Exception exception, Boolean object) {
        Assert.INSTANCE.set(exception).throwsIfTrue(object);
        return object;
    }

    /**
     * 需要至少有一个为 false
     *
     * @param objects
     * @return
     */
    public static void requireFalse(Boolean... objects) {
        Assert.FALSE_NEED.throwsIfAllTrue(objects);
    }

    /**
     * 需要至少有一个为 false
     *
     * @param objects
     * @param message
     * @return
     */
    public static void requireFalse(Supplier<String> message, Boolean... objects) {
        Assert.INSTANCE.set(message).throwsIfAllTrue(objects);
    }

    /**
     * 需要至少有一个为 false
     *
     * @param httpStatus
     * @param objects
     * @return
     */
    public static void requireFalse(HttpStatus httpStatus, Boolean... objects) {
        Assert.INSTANCE.set(httpStatus).throwsIfAllTrue(objects);
    }

    /**
     * 需要至少有一个为 false
     *
     * @param httpStatus
     * @param message
     * @param objects
     * @return
     */
    public static void requireFalse(HttpStatus httpStatus, Supplier<String> message, Boolean... objects) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfAllTrue(objects);
    }

    /**
     * 需要全部为 false
     *
     * @param objects
     * @return
     */
    public static void requireAllFalse(Boolean... objects) {
        Assert.FALSE_NEED.throwsIfTrue(objects);
    }

    /**
     * 需要全部为 false
     *
     * @param httpStatus
     * @param objects
     * @return
     */
    public static void requireAllFalse(HttpStatus httpStatus, Boolean... objects) {
        Assert.INSTANCE.set(httpStatus).throwsIfTrue(objects);
    }

    /**
     * 需要全部为 false
     *
     * @param httpStatus
     * @param message
     * @param objects
     * @return
     */
    public static void requireAllFalse(HttpStatus httpStatus, Supplier<String> message, Boolean... objects) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfTrue(objects);
    }

    /**
     * 需要全部为 false
     *
     * @param objects
     * @param message
     * @return
     */
    public static void requireAllFalse(Supplier<String> message, Boolean... objects) {
        Assert.INSTANCE.set(message).throwsIfTrue(objects);
    }

    /**
     * 需要为 正整数
     *
     * @param object
     * @return
     */
    public static <T extends Number> T requirePositive(T object) {
        Assert.POSITIVE_NEED.throwsIfNotPositive(object);
        return object;
    }

    /**
     * 需要为 正整数
     *
     * @param httpStatus
     * @param object
     * @return
     */
    public static <T extends Number> T requirePositive(HttpStatus httpStatus, T object) {
        Assert.INSTANCE.set(httpStatus).throwsIfNotPositive(object);
        return object;
    }

    /**
     * 需要为 正整数
     *
     * @param httpStatus
     * @param message
     * @param object
     * @return
     */
    public static <T extends Number> T requirePositive(HttpStatus httpStatus, Supplier<String> message, T object) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfNotPositive(object);
        return object;
    }

    /**
     * 需要为 正整数
     *
     * @param object
     * @param message
     * @return
     */
    public static <T extends Number> T requirePositive(Supplier<String> message, T object) {
        Assert.INSTANCE.set(message).throwsIfNotPositive(object);
        return object;
    }

    /**
     * 需要全部为 正整数
     *
     * @param objects
     * @return
     */
    public static void requireAllPositive(Number... objects) {
        Assert.POSITIVE_NEED.throwsIfNotPositive(objects);
    }

    /**
     * 需要全部为 正整数
     *
     * @param httpStatus
     * @param objects
     * @return
     */
    public static void requireAllPositive(HttpStatus httpStatus, Number... objects) {
        Assert.INSTANCE.set(httpStatus).throwsIfNotPositive(objects);
    }

    /**
     * 需要全部为 正整数
     *
     * @param httpStatus
     * @param message
     * @param objects
     * @return
     */
    public static void requireAllPositive(HttpStatus httpStatus, Supplier<String> message, Number... objects) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfNotPositive(objects);
    }

    /**
     * 需要全部为 正整数
     *
     * @param objects
     * @param message
     * @return
     */
    public static void requireAllPositive(Supplier<String> message, Number... objects) {
        Assert.INSTANCE.set(message).throwsIfNotPositive(objects);
    }

    /**
     * 需要为 0 或 负数
     *
     * @param object
     * @return
     */
    public static <T extends Number> T requireNotPositive(T object) {
        Assert.POSITIVE_CHECK.throwsIfPositive(object);
        return object;
    }

    /**
     * 需要为 0 或 负数
     *
     * @param httpStatus
     * @param object
     * @return
     */
    public static <T extends Number> T requireNotPositive(HttpStatus httpStatus, T object) {
        Assert.INSTANCE.set(httpStatus).throwsIfPositive(object);
        return object;
    }

    /**
     * 需要为 0 或 负数
     *
     * @param httpStatus
     * @param message
     * @param object
     * @return
     */
    public static <T extends Number> T requireNotPositive(HttpStatus httpStatus, Supplier<String> message, T object) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfPositive(object);
        return object;
    }

    /**
     * 需要为 0 或 负数
     *
     * @param object
     * @param message
     * @return
     */
    public static <T extends Number> T requireNotPositive(Supplier<String> message, T object) {
        Assert.INSTANCE.set(message).throwsIfPositive(object);
        return object;
    }

    /**
     * 需要有一个为 0 或 负数
     *
     * @param objects
     * @return
     */
    public static void requireNotPositive(Number... objects) {
        Assert.POSITIVE_CHECK.throwsIfAllPositive(objects);
    }

    /**
     * 需要有一个为 0 或 负数
     *
     * @param httpStatus
     * @param objects
     * @return
     */
    public static void requireNotPositive(HttpStatus httpStatus, Number... objects) {
        Assert.INSTANCE.set(httpStatus).throwsIfAllPositive(objects);
    }

    /**
     * 需要有一个为 0 或 负数
     *
     * @param httpStatus
     * @param message
     * @param objects
     * @return
     */
    public static void requireNotPositive(HttpStatus httpStatus, Supplier<String> message, Number... objects) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfAllPositive(objects);
    }

    /**
     * 需要有一个为 0 或 负数
     *
     * @param objects
     * @param message
     * @return
     */
    public static void requireNotPositive(Supplier<String> message, Number... objects) {
        Assert.INSTANCE.set(message).throwsIfAllPositive(objects);
    }

    /**
     * 需要两者相等
     *
     * @param obj1
     * @param obj2
     */
    public static void requireEquals(@Nullable Object obj1, @Nullable Object obj2) {
        Assert.EQUALS_NEED.throwsIfNotEquals(obj1, obj2);
    }

    /**
     * 需要起码有一个对象相等
     *
     * @param obj1
     * @param objs
     */
    public static void requireEquals(@Nullable Object obj1, @Nullable Object... objs) {
        Assert.EQUALS_NEED.throwsIfNotEquals(obj1, objs);
    }

    /**
     * 需要起码有一个对象与集合的元素相同
     *
     * @param obj1
     * @param objs
     */
    public static <T> void requireCollEquals(@Nullable T obj1, @Nullable Collection<T> objs) {
        Assert.EQUALS_NEED.throwsIfCollNotEquals(obj1, objs);
    }

    /**
     * 需要两者不相等
     *
     * @param obj1
     * @param obj2
     */
    public static void requireNotEquals(@Nullable Object obj1, @Nullable Object obj2) {
        Assert.EQUALS_CHECK.throwsIfEquals(obj1, obj2);
    }

    /**
     * 需要全部不相等
     *
     * @param obj1
     * @param objs
     */
    public static void requireNotEquals(@Nullable Object obj1, @Nullable Object... objs) {
        Assert.EQUALS_CHECK.throwsIfEquals(obj1, objs);
    }

    /**
     * 需要与集合的所有元素都不相等
     *
     * @param obj1
     * @param objs
     */
    public static <T> void requireCollNotEquals(@Nullable T obj1, @Nullable Collection<T> objs) {
        Assert.EQUALS_CHECK.throwsIfCollEquals(obj1, objs);
    }

    // require end =============================================

    //============================================= modify value start

    /**
     * 当给定字符串为 null 时，设置为空串
     *
     * @param value 被检查字符串
     * @return 原字符串或空串
     */
    public static String emptyIfNull(CharSequence value) {
        return StringsUtil.emptyIfNull(value);
    }

    /**
     * 当给定对象为 null 时，返回设定的值
     *
     * @param value       被检查对象
     * @param valueIfNull 设定的值
     * @param <T>         对象类型
     * @return 原对象或设定的值
     */
    public static <T> T setIfNull(T value, T valueIfNull) {
        if (isNull(value)) {
            return valueIfNull;
        }
        return value;
    }

    /**
     * 当给定对象为 empty 时，返回设定的值
     *
     * @param value        被检查对象
     * @param valueIfEmpty 设定的值
     * @param <T>          对象类型
     * @return 原对象或设定的值
     */
    public static <T> T setIfEmpty(T value, T valueIfEmpty) {
        if (isEmpty(value)) {
            return valueIfEmpty;
        }
        return value;
    }

    //============================================= modify value end

    /**
     * 是否为 JSONObject，首尾都为大括号判定为 JSONObject
     *
     * @param data
     * @return
     */
    public static boolean doesStringLikeJson(String data) {
        if (isBlank(data)) {
            return false;
        }

        CharSequence str = data.trim();
        return str.charAt(0) == CharPool.DELIM_START && str.charAt(str.length() - 1) == CharPool.DELIM_END;
    }

    /**
     * 是否为 JSONArray，首尾都为中括号判定为 JSONArray
     *
     * @param data
     * @return
     */
    public static boolean doesStringLikeJsonArray(String data) {
        if (isBlank(data)) {
            return false;
        }

        CharSequence str = data.trim();
        return str.charAt(0) == CharPool.BRACKET_START && str.charAt(str.length() - 1) == CharPool.BRACKET_END;
    }

    /**
     * 是否不为 JSONObject，首尾都为大括号判定为 JSONObject
     *
     * @param data
     * @return
     */
    public static boolean doesStringNotLikeJson(String data) {
        return !doesStringLikeJson(data);
    }

    /**
     * 是否不为 JSONArray，首尾都为中括号判定为 JSONArray
     *
     * @param data
     * @return
     */
    public static boolean doesStringNotLikeJsonArray(String data) {
        return !doesStringLikeJsonArray(data);
    }

    /**
     * 获取实际的值 TODO 场景未够完善
     *
     * @param value
     * @return
     */
    public static Object getActualValue(String value) {
        if (Objects.isNull(value)) {
            return null;
        }
        try {
            if (NumbersUtil.isInteger(value)) {
                return Integer.valueOf(value);
            }
            if (NumbersUtil.isLong(value)) {
                return Long.valueOf(value);
            }
        } catch (Exception ignore) {
        }
        return value;
    }

}

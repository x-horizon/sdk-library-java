package cn.srd.itcp.sugar.tool.core;

import cn.hutool.core.util.ArrayUtil;
import cn.srd.itcp.sugar.tool.core.asserts.Assert;
import cn.srd.itcp.sugar.tool.core.validation.Nullable;
import cn.srd.itcp.sugar.tool.web.HttpStatus;

import java.io.ObjectStreamClass;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 通用工具
 *
 * @author wjm
 * @since 2020/7/8 16:11
 */
public class Objects {

    /**
     * private block constructor
     */
    private Objects() {
    }

    // ============================================= null、empty、blank、positive、boolean、equals start

    // ============================================= null

    /**
     * 判断是否为 null
     *
     * @param object 待判断对象
     * @return 是否为 null
     */
    public static boolean isNull(Object object) {
        return null == object;
    }

    /**
     * 判断是否为 null，如果有一个为 null，返回 true
     *
     * @param objects 待判断对象
     * @return 是否为 null
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
     * 判断是否全部为 null
     *
     * @param objects 待判断对象
     * @return 是否全部为 null
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
     * 判断是否不为 null
     *
     * @param object 待判断对象
     * @return 是否不为 null
     */
    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }

    /**
     * 判断是否全部不为 null
     *
     * @param objects 待判断对象
     * @return 是否全部不为 null
     */
    public static boolean isNotNull(Object... objects) {
        return !isNull(objects);
    }

    // ============================================= empty

    /**
     * 判断是否为空，定义：
     * <pre>
     * 1、为 null
     * 2、为""
     * </pre>
     *
     * @param object 待判断对象
     * @return 是否为空
     */
    public static boolean isEmpty(CharSequence object) {
        return null == object || object.length() == 0;
    }

    /**
     * 判断是否为空，定义：
     * <pre>
     * 1、为 null
     * 2、为""
     * 如果有一个为空，则返回true
     * </pre>
     *
     * @param objects 待判断对象
     * @return 是否为空
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
     * 判断是否为空，定义：
     * <pre>
     * 1、为 null
     * 2、size为0
     * </pre>
     *
     * @param object 待判断对象
     * @return 是否为空
     */
    public static boolean isEmpty(Map<?, ?> object) {
        return null == object || object.isEmpty();
    }

    /**
     * 判断是否为空，定义：
     * <pre>
     * 1、为 null
     * 2、size为0
     * 如果有一个为空，则返回true
     * </pre>
     *
     * @param objects 待判断对象
     * @return 是否为空
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
     * 判断是否为空，定义：
     * <pre>
     * 1、为 null
     * 2、size为0
     * </pre>
     *
     * @param object 待判断对象
     * @return 是否为空
     */
    public static boolean isEmpty(Iterable<?> object) {
        return null == object || isEmpty(object.iterator());
    }

    /**
     * 判断是否为空，定义：
     * <pre>
     * 1、为 null
     * 2、size为0
     * 如果有一个为空，则返回true
     * </pre>
     *
     * @param objects 待判断对象
     * @return 是否为空
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
     * @param object 待判断对象
     * @return 是否为空
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
     * @param objects 待判断对象
     * @return 是否为空
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
     * 判断是否为空：
     * <pre>
     * 1. CharSequence
     * 2. Map
     * 3. Iterable
     * 4. Iterator
     * 5. Array
     * 6. Object
     * </pre>
     *
     * @param object 待判断对象
     * @return 是否为空
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object object) {
        if (null == object) {
            return true;
        }

        if (object instanceof CharSequence) {
            return isBlank((CharSequence) object);
        } else if (object instanceof Map) {
            return isEmpty(((Map) object));
        } else if (object instanceof Iterable) {
            return isEmpty(((Iterable) object));
        } else if (object instanceof Iterator) {
            return isEmpty(((Iterator) object));
        } else if (object.getClass().isArray()) {
            return Array.getLength(object) == 0;
        }

        return false;
    }

    /**
     * 判断是否为空：
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
     * @param objects 待判断对象
     * @return 是否为空
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
     * @param objects 待判断对象
     * @return 是否为空
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
     * @param objects 待判断对象
     * @return 是否为空
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
     * @param objects 待判断对象
     * @return 是否为空
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
     * @param objects 待判断对象
     * @return 是否为空
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
     * @param objects 待判断对象
     * @return 是否为空
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
     * @param objects 待判断对象
     * @return 是否为空
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
     * @param objects 待判断对象
     * @return 是否为空
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
     * @param objects 待判断对象
     * @return 是否为空
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
     * @param objects 待判断对象
     * @return 是否为空
     */
    public static boolean isEmpty(boolean[] objects) {
        return null == objects || objects.length == 0;
    }

    /**
     * 判断是否全部为{@link #isEmpty(CharSequence)}
     *
     * @param objects 待判断对象
     * @return 是否全部为空
     */
    public static boolean isAllEmpty(CharSequence... objects) {
        if (null == objects) {
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
     * 判断是否全部为{@link Objects#isEmpty(Map)}
     *
     * @param objects 待判断对象
     * @return 是否全部为空
     */
    public static boolean isAllEmpty(Map<?, ?>... objects) {
        if (null == objects) {
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
     * 判断是否全部为{@link #isEmpty(Iterable)}
     *
     * @param objects 待判断对象
     * @return 是否全部为空
     */
    public static boolean isAllEmpty(Iterable<?>... objects) {
        if (null == objects) {
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
     * @param objects 待判断对象
     * @return 是否全部为空
     */
    public static boolean isAllEmpty(Iterator<?>... objects) {
        if (null == objects) {
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
     * 判断是否全部为{@link #isEmpty(Object)}
     *
     * @param objects 待判断对象
     * @return 是否全部为空
     */
    public static boolean isAllEmpty(Object... objects) {
        if (null == objects) {
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
     * 判断是否不为{@link #isEmpty(CharSequence)}
     *
     * @param object 待判断对象
     * @return 是否不为空
     */
    public static boolean isNotEmpty(CharSequence object) {
        return !isEmpty(object);
    }

    /**
     * 判断是否全部不为{@link #isEmpty(CharSequence)}
     *
     * @param objects 待判断对象
     * @return 是否全部不为空
     */
    public static boolean isNotEmpty(CharSequence... objects) {
        return !isAllEmpty(objects);
    }

    /**
     * 判断是否不为{@link Objects#isEmpty(Map)}
     *
     * @param object 待判断对象
     * @return 是否不为空
     */
    public static boolean isNotEmpty(Map<?, ?> object) {
        return !isEmpty(object);
    }

    /**
     * 判断是否全部不为{@link Objects#isEmpty(Map)}
     *
     * @param objects 待判断对象
     * @return 是否全部不为空
     */
    public static boolean isNotEmpty(Map<?, ?>... objects) {
        return !isAllEmpty(objects);
    }

    /**
     * 判断是否不为{@link #isEmpty(Iterable)}
     *
     * @param object 待判断对象
     * @return 是否不为空
     */
    public static boolean isNotEmpty(Iterable<?> object) {
        return !isEmpty(object);
    }

    /**
     * Iterator 是否全部不为{@link #isEmpty(Iterator)}
     *
     * @param objects 待判断对象
     * @return 是否全部不为空
     */
    public static boolean isNotEmpty(Iterator<?>... objects) {
        return !isAllEmpty(objects);
    }

    /**
     * Iterator 是否不为{@link #isEmpty(Iterator)}
     *
     * @param object 待判断对象
     * @return 是否不为空
     */
    public static boolean isNotEmpty(Iterator<?> object) {
        return !isEmpty(object);
    }

    /**
     * 判断是否全部不为{@link #isEmpty(Iterable)}
     *
     * @param objects 待判断对象
     * @return 是否全部不为空
     */
    public static boolean isNotEmpty(Iterable<?>... objects) {
        return !isAllEmpty(objects);
    }

    /**
     * 判断是否不为{@link #isEmpty(Object)}
     *
     * @param object 待判断对象
     * @return 是否不为空
     */
    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }

    /**
     * 判断是否全部不为{@link #isEmpty(Object)}
     *
     * @param objects 待判断对象
     * @return 是否全部不为空
     */
    public static boolean isNotEmpty(Object... objects) {
        return !isAllEmpty(objects);
    }

    /**
     * 判断是否不为{@link #isEmpty(byte[])}
     *
     * @param objects 待判断对象
     * @return 是否不为空
     */
    public static boolean isNotEmpty(byte[] objects) {
        return !isEmpty(objects);
    }

    /**
     * 判断是否不为{@link #isEmpty(short[])}
     *
     * @param objects 待判断对象
     * @return 是否不为空
     */
    public static boolean isNotEmpty(short[] objects) {
        return !isEmpty(objects);
    }

    /**
     * 判断是否不为{@link #isEmpty(int[])}
     *
     * @param objects 待判断对象
     * @return 是否不为空
     */
    public static boolean isNotEmpty(int[] objects) {
        return !isEmpty(objects);
    }

    /**
     * 判断是否不为{@link #isEmpty(long[])}
     *
     * @param objects 待判断对象
     * @return 是否不为空
     */
    public static boolean isNotEmpty(long[] objects) {
        return !isEmpty(objects);
    }

    /**
     * 判断是否不为{@link #isEmpty(float[])}
     *
     * @param objects 待判断对象
     * @return 是否不为空
     */
    public static boolean isNotEmpty(float[] objects) {
        return !isEmpty(objects);
    }

    /**
     * 判断是否不为{@link #isEmpty(double[])}
     *
     * @param objects 待判断对象
     * @return 是否不为空
     */
    public static boolean isNotEmpty(double[] objects) {
        return !isEmpty(objects);
    }

    /**
     * 判断是否不为{@link #isEmpty(char[])}
     *
     * @param objects 待判断对象
     * @return 是否不为空
     */
    public static boolean isNotEmpty(char[] objects) {
        return !isEmpty(objects);
    }

    /**
     * 判断是否不为{@link #isEmpty(String[])}
     *
     * @param objects 待判断对象
     * @return 是否不为空
     */
    public static boolean isNotEmpty(String[] objects) {
        return !isEmpty(objects);
    }

    /**
     * 判断是否不为{@link #isEmpty(boolean[])}
     *
     * @param objects 待判断对象
     * @return 是否不为空
     */
    public static boolean isNotEmpty(boolean[] objects) {
        return !isEmpty(objects);
    }

    // ============================================= blank

    /**
     * 判断是否为空白，定义：
     * <pre>
     * 1、为 null
     * 2、为不可见字符（如空格）
     * 3、""
     * </pre>
     *
     * @param object 待判断对象
     * @return 是否为空白
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
     * 判断是否为空白，定义：
     * <pre>
     * 1、为 null
     * 2、为不可见字符（如空格）
     * 3、""
     * 如果有一个为空，则返回true
     * </pre>
     *
     * @param objects 待判断对象
     * @return 是否为空白
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
     * 判断是否全部为{@link #isBlank(CharSequence)}
     *
     * @param objects 待判断对象
     * @return 是否全部为空白
     */
    public static boolean isAllBlank(CharSequence... objects) {
        if (null == objects) {
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
     * 判断是否不为{@link #isBlank(CharSequence)}
     *
     * @param object 待判断对象
     * @return 是否不为空白
     */
    public static boolean isNotBlank(CharSequence object) {
        return !isBlank(object);
    }

    /**
     * 判断是否全部不为{@link #isBlank(CharSequence)}
     *
     * @param objects 待判断对象
     * @return 是否全部不为空白
     */
    public static boolean isNotBlank(CharSequence... objects) {
        return !isAllBlank(objects);
    }

    // ============================================= positive

    /**
     * 如果为正整数，返回 true
     *
     * @param object 待判断对象
     * @return 是否为正整数
     */
    public static boolean isPositive(Number object) {
        return object != null && object.longValue() > 0;
    }

    /**
     * 如果全部为正整数，返回 true
     *
     * @param objects 待判断对象
     * @return 是否全部为正整数
     */
    public static boolean isAllPositive(Number... objects) {
        if (null == objects) {
            return false;
        }
        for (Number number : objects) {
            if (isNotPositive(number)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 如果不为正整数，返回 true
     *
     * @param object 待判断对象
     * @return 是否不为正整数
     */
    public static boolean isNotPositive(Number object) {
        return !isPositive(object);
    }

    /**
     * 如果有一个不为正整数，返回 true
     *
     * @param objects 待判断对象
     * @return 是否不为正整数
     */
    public static boolean isNotPositive(Number... objects) {
        return !isAllPositive(objects);
    }

    // ============================================= boolean

    /**
     * 如果为 true，返回 true
     *
     * @param object 待判断对象
     * @return 是否为 true
     */
    public static boolean isTrue(Supplier<Boolean> object) {
        return isTrue(object.get());
    }

    /**
     * 如果为 true，返回 true
     *
     * @param object 待判断对象
     * @return 是否为 true
     */
    public static boolean isTrue(Boolean object) {
        return isNotEmpty(object) && object;
    }

    /**
     * 如果有一个为 true，返回 true
     *
     * @param objects 待判断对象
     * @return 是否为 true
     */
    public static boolean isTrue(Boolean... objects) {
        if (ArrayUtil.isEmpty(objects)) {
            return false;
        }
        for (Boolean isTrue : objects) {
            if (isTrue) {
                return true;
            }
        }
        return false;
    }

    /**
     * 如果全部为 true，返回 true
     *
     * @param objects 待判断对象
     * @return 是否全部为 true
     */
    public static boolean isAllTrue(Boolean... objects) {
        if (ArrayUtil.isEmpty(objects)) {
            return false;
        }
        for (Boolean isTrue : objects) {
            if (!isTrue) {
                return false;
            }
        }
        return true;
    }

    /**
     * 如果为 false，返回 true
     *
     * @param object 待判断对象
     * @return 是否为 false
     */
    public static boolean isFalse(Boolean object) {
        return isNotNull(object) && !object;
    }

    /**
     * 如果为 false，返回 true
     *
     * @param object 待判断对象
     * @return 是否为 false
     */
    public static boolean isFalse(Supplier<Boolean> object) {
        return isFalse(object.get());
    }

    /**
     * 如果为 false，返回 true
     *
     * @param item      待判断对象
     * @param predicate 判断逻辑
     * @param <T>       对象类型
     * @return 是否为 false
     */
    public static <T> boolean isFalse(T item, Predicate<T> predicate) {
        return !predicate.test(item);
    }

    /**
     * 如果有一个为 false，返回 true
     *
     * @param objects 待判断对象
     * @return 是否为 false
     */
    public static boolean isFalse(Boolean... objects) {
        if (ArrayUtil.isEmpty(objects)) {
            return false;
        }
        for (Boolean isFalse : objects) {
            if (!isFalse) {
                return true;
            }
        }
        return false;
    }

    /**
     * 如果全部为 false，返回 true
     *
     * @param objects 待判断对象
     * @return 是否全部为 false
     */
    public static boolean isAllFalse(Boolean... objects) {
        if (ArrayUtil.isEmpty(objects)) {
            return false;
        }
        for (Boolean isFalse : objects) {
            if (isFalse) {
                return false;
            }
        }
        return true;
    }

    // ============================================= equals

    /**
     * 比较两个对象是否相同；安全的 null 值比较
     * <pre>
     * 相同的条件如下，满足一个则返回 true：
     * 1、obj1 == null 且 obj2 == null
     * 2、obj1.equals(obj2)
     * 3、BigDecimal 的比较，则为 0 == obj1.compareTo(obj2)
     * 4、数组的比较，则为数组长度和每个元素都相等
     * </pre>
     *
     * @param obj1 待比较对象
     * @param obj2 待比较对象
     * @return 是否相同
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
     * @param obj1 待比较对象
     * @param objs 待比较对象
     * @return 是否相同
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
     * 比较第一个对象是否与后续对象相同，若第一个对象与后续对象有一个不同，返回 false，全部相同返回 true；
     * <pre>
     * 比较规则：{@link #equals(Object, Object)}
     * </pre>
     *
     * @param obj1 待比较对象
     * @param objs 待比较对象
     * @return 是否相同
     * @see Objects#equals(Object, Object)
     */
    public static boolean allEquals(@Nullable Object obj1, @Nullable Object... objs) {
        if (obj1 == objs) {
            return true;
        }
        if (null == obj1 || null == objs) {
            return false;
        }
        for (Object objToCompare : objs) {
            if (!obj1.equals(objToCompare)) {
                return false;
            }
            if (obj1 instanceof BigDecimal && objToCompare instanceof BigDecimal && (0 != ((BigDecimal) obj1).compareTo((BigDecimal) objToCompare))) {
                return false;
            }
            if (obj1.getClass().isArray() && objToCompare.getClass().isArray() && (!ArrayUtil.equals(obj1, objToCompare))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 比较对象是否与集合元素是否相同，若对象与集合元素有一个相同，返回 true；<br>
     * 两者有一个为 null，返回false；
     *
     * @param obj1 待比较对象
     * @param objs 待比较对象
     * @param <T>  对象类型
     * @return 是否相同
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
     * @param obj1 待比较对象
     * @param obj2 待比较对象
     * @return 是否不同
     */
    public static boolean notEquals(@Nullable Object obj1, @Nullable Object obj2) {
        return !equals(obj1, obj2);
    }

    /**
     * 比较第一个对象是否与后续对象不同，若第一个对象与后续对象全部都不同，返回 true，否则返回 false；安全的 null 值比较
     *
     * @param obj1 待比较对象
     * @param objs 待比较对象
     * @return 是否不同
     */
    public static boolean notEquals(@Nullable Object obj1, @Nullable Object... objs) {
        return !equals(obj1, objs);
    }

    /**
     * 若对象是与集合所有元素全部不同，返回 true；安全的 null 值比较
     *
     * @param obj1 待比较对象
     * @param objs 待比较对象
     * @param <T>  对象类型
     * @return 是否不同
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

    // ============================================= require start

    /**
     * 需要为 null
     *
     * @param object 待断言对象
     * @param <T>    对象类型
     * @return 断言成功返回原对象
     */
    public static <T> T requireNull(T object) {
        Assert.NULL_NEED.throwsIfNotNull(object);
        return object;
    }

    /**
     * 需要为 null
     *
     * @param httpStatus http 状态码
     * @param object     待断言对象
     * @param <T>        对象类型
     * @return 断言成功返回原对象
     */
    public static <T> T requireNull(HttpStatus httpStatus, T object) {
        Assert.INSTANCE.set(httpStatus).throwsIfNotNull(object);
        return object;
    }

    /**
     * 需要为 null
     *
     * @param httpStatus http 状态码
     * @param message    断言信息
     * @param object     待断言对象
     * @param <T>        对象类型
     * @return 断言成功返回原对象
     */
    public static <T> T requireNull(HttpStatus httpStatus, Supplier<String> message, T object) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfNotNull(object);
        return object;
    }

    /**
     * 需要为 null
     *
     * @param message 断言信息
     * @param object  待断言对象
     * @param <T>     对象类型
     * @return 断言成功返回原对象
     */
    public static <T> T requireNull(Supplier<String> message, T object) {
        Assert.INSTANCE.set(message).throwsIfNotNull(object);
        return object;
    }

    /**
     * 需要至少有一个为 null
     *
     * @param objects 待断言对象
     * @param <T>     对象类型
     */
    @SafeVarargs
    public static <T> void requireNull(T... objects) {
        Assert.NULL_NEED.throwsIfNotNull((Object[]) objects);
    }

    /**
     * 需要至少有一个为 null
     *
     * @param httpStatus http 状态码
     * @param objects    待断言对象
     * @param <T>        对象类型
     */
    @SafeVarargs
    public static <T> void requireNull(HttpStatus httpStatus, T... objects) {
        Assert.INSTANCE.set(httpStatus).throwsIfNotNull((Object[]) objects);
    }

    /**
     * 需要至少有一个为 null
     *
     * @param httpStatus http 状态码
     * @param message    断言信息
     * @param objects    待断言对象
     * @param <T>        对象类型
     */
    @SafeVarargs
    public static <T> void requireNull(HttpStatus httpStatus, Supplier<String> message, T... objects) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfNotNull((Object[]) objects);
    }

    /**
     * 需要至少有一个为 null
     *
     * @param message 断言信息
     * @param objects 待断言对象
     * @param <T>     对象类型
     */
    @SafeVarargs
    public static <T> void requireNull(Supplier<String> message, T... objects) {
        Assert.INSTANCE.set(message).throwsIfNotNull((Object[]) objects);
    }

    /**
     * 需要为 empty
     *
     * @param object 待断言对象
     * @param <T>    对象类型
     * @return 断言成功返回原对象
     */
    public static <T> T requireEmpty(T object) {
        Assert.EMPTY_NEED.throwsIfNotEmpty(object);
        return object;
    }

    /**
     * @param httpStatus http 状态码
     * @param object     待断言对象
     * @param <T>        对象类型
     * @return 断言成功返回原对象
     */
    public static <T> T requireEmpty(HttpStatus httpStatus, T object) {
        Assert.INSTANCE.set(httpStatus).throwsIfNotEmpty(object);
        return object;
    }

    /**
     * 需要为 empty
     *
     * @param httpStatus http 状态码
     * @param message    断言信息
     * @param object     待断言对象
     * @param <T>        对象类型
     * @return 断言成功返回原对象
     */
    public static <T> T requireEmpty(HttpStatus httpStatus, Supplier<String> message, T object) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfNotEmpty(object);
        return object;
    }

    /**
     * 需要为 empty
     *
     * @param message 断言信息
     * @param object  待断言对象
     * @param <T>     对象类型
     * @return 断言成功返回原对象
     */
    public static <T> T requireEmpty(Supplier<String> message, T object) {
        Assert.INSTANCE.set(message).throwsIfNotEmpty(object);
        return object;
    }

    /**
     * 需要至少有一个为 empty
     *
     * @param objects 待断言对象
     * @param <T>     对象类型
     */
    @SafeVarargs
    public static <T> void requireEmpty(T... objects) {
        Assert.EMPTY_NEED.throwsIfNotEmpty((Object[]) objects);
    }

    /**
     * 需要至少有一个为 empty
     *
     * @param httpStatus http 状态码
     * @param objects    待断言对象
     * @param <T>        对象类型
     */
    @SafeVarargs
    public static <T> void requireEmpty(HttpStatus httpStatus, T... objects) {
        Assert.INSTANCE.set(httpStatus).throwsIfNotEmpty((Object[]) objects);
    }

    /**
     * 需要至少有一个为 empty
     *
     * @param httpStatus http 状态码
     * @param message    断言信息
     * @param objects    待断言对象
     * @param <T>        对象类型
     */
    @SafeVarargs
    public static <T> void requireEmpty(HttpStatus httpStatus, Supplier<String> message, T... objects) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfNotEmpty((Object[]) objects);
    }

    /**
     * 需要至少有一个为 empty
     *
     * @param message 断言信息
     * @param objects 待断言对象
     * @param <T>     对象类型
     */
    @SafeVarargs
    public static <T> void requireEmpty(Supplier<String> message, T... objects) {
        Assert.INSTANCE.set(message).throwsIfNotEmpty((Object[]) objects);
    }

    /**
     * 需要为 blank
     *
     * @param object 待断言对象
     * @return 断言成功返回原对象
     */
    public static String requireBlank(String object) {
        Assert.BLANK_NEED.throwsIfNotBlank(object);
        return object;
    }

    /**
     * 需要为 blank
     *
     * @param httpStatus http 状态码
     * @param object     待断言对象
     * @return 断言成功返回原对象
     */
    public static String requireBlank(HttpStatus httpStatus, String object) {
        Assert.INSTANCE.set(httpStatus).throwsIfNotBlank(object);
        return object;
    }

    /**
     * 需要为 blank
     *
     * @param httpStatus http 状态码
     * @param message    断言信息
     * @param object     待断言对象
     * @return 断言成功返回原对象
     */
    public static String requireBlank(HttpStatus httpStatus, Supplier<String> message, String object) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfNotBlank(object);
        return object;
    }

    /**
     * 需要为 blank
     *
     * @param object  待断言对象
     * @param message 断言信息
     * @return 断言成功返回原对象
     */
    public static String requireBlank(Supplier<String> message, String object) {
        Assert.INSTANCE.set(message).throwsIfNotBlank(object);
        return object;
    }

    /**
     * 需要至少有一个为 blank
     *
     * @param objects 待断言对象
     */
    public static void requireBlank(String... objects) {
        Assert.BLANK_NEED.throwsIfNotBlank(objects);
    }

    /**
     * 需要至少有一个为 blank
     *
     * @param httpStatus http 状态码
     * @param objects    待断言对象
     */
    public static void requireBlank(HttpStatus httpStatus, String... objects) {
        Assert.INSTANCE.set(httpStatus).throwsIfNotBlank(objects);
    }

    /**
     * 需要至少有一个为 blank
     *
     * @param httpStatus http 状态码
     * @param message    断言信息
     * @param objects    待断言对象
     */
    public static void requireBlank(HttpStatus httpStatus, Supplier<String> message, String... objects) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfNotBlank(objects);
    }

    /**
     * 需要至少有一个为 blank
     *
     * @param objects 待判断对象
     * @param message 断言信息
     */
    public static void requireBlank(Supplier<String> message, String... objects) {
        Assert.INSTANCE.set(message).throwsIfNotBlank(objects);
    }

    /**
     * 不可为 null
     *
     * @param object 待断言对象
     * @param <T>    对象类型
     * @return 断言成功返回原对象
     */
    public static <T> T requireNotNull(T object) {
        Assert.NULL_CHECK.throwsIfNull(object);
        return object;
    }

    /**
     * 不可为 null
     *
     * @param httpStatus http 状态码
     * @param object     待断言对象
     * @param <T>        对象类型
     * @return 断言成功返回原对象
     */
    public static <T> T requireNotNull(HttpStatus httpStatus, T object) {
        Assert.INSTANCE.set(httpStatus).throwsIfNull(object);
        return object;
    }

    /**
     * @param httpStatus http 状态码
     * @param message    断言信息
     * @param object     待断言对象
     * @param <T>        对象类型
     * @return 断言成功返回原对象
     */
    public static <T> T requireNotNull(HttpStatus httpStatus, Supplier<String> message, T object) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfNull(object);
        return object;
    }

    /**
     * 不可为 null
     *
     * @param message 断言信息
     * @param object  http 状态码
     * @param <T>     对象类型
     * @return 断言成功返回原对象
     */
    public static <T> T requireNotNull(Supplier<String> message, T object) {
        Assert.INSTANCE.set(message).throwsIfNull(object);
        return object;
    }

    /**
     * 不可为 null
     *
     * @param exception 自定义异常
     * @param object    待断言对象
     * @param <T>       对象类型
     * @return 断言成功返回原对象
     */
    public static <T> T requireNotNull(Exception exception, T object) {
        Assert.INSTANCE.set(exception).throwsIfNull(object);
        return object;
    }

    /**
     * 需要至少有一个不为 null
     *
     * @param objects 待断言对象
     * @param <T>     对象类型
     */
    @SafeVarargs
    public static <T> void requireNotNull(T... objects) {
        Assert.NULL_CHECK.throwsIfAllNull((Object[]) objects);
    }

    /**
     * 需要至少有一个不为 null
     *
     * @param httpStatus http 状态码
     * @param objects    待断言对象
     * @param <T>        对象类型
     */
    @SafeVarargs
    public static <T> void requireNotNull(HttpStatus httpStatus, T... objects) {
        Assert.INSTANCE.set(httpStatus).throwsIfAllNull((Object[]) objects);
    }

    /**
     * 需要至少有一个不为 null
     *
     * @param httpStatus http 状态码
     * @param message    断言信息
     * @param objects    待断言对象
     * @param <T>        对象类型
     */
    @SafeVarargs
    public static <T> void requireNotNull(HttpStatus httpStatus, Supplier<String> message, T... objects) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfAllNull((Object[]) objects);
    }

    /**
     * 需要至少有一个不为 null
     *
     * @param objects 待断言对象
     * @param message 断言信息
     * @param <T>     对象类型
     */
    @SafeVarargs
    public static <T> void requireNotNull(Supplier<String> message, T... objects) {
        Assert.INSTANCE.set(message).throwsIfAllNull((Object[]) objects);
    }

    /**
     * 需要全部不为 null
     *
     * @param objects 断言信息
     * @param <T>     对象类型
     */
    @SafeVarargs
    public static <T> void requireNonNull(T... objects) {
        Assert.NULL_NEED.throwsIfNull((Object[]) objects);
    }

    /**
     * 需要全部不为 null
     *
     * @param httpStatus http 状态码
     * @param objects    待断言对象
     * @param <T>        对象类型
     */
    @SafeVarargs
    public static <T> void requireNonNull(HttpStatus httpStatus, T... objects) {
        Assert.INSTANCE.set(httpStatus).throwsIfNull((Object[]) objects);
    }

    /**
     * 需要全部不为 null
     *
     * @param httpStatus http 状态码
     * @param message    断言信息
     * @param objects    待断言对象
     * @param <T>        对象类型
     */
    @SafeVarargs
    public static <T> void requireNonNull(HttpStatus httpStatus, Supplier<String> message, T... objects) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfNull((Object[]) objects);
    }

    /**
     * 需要全部不为 null
     *
     * @param objects 待断言对象
     * @param message 断言信息
     * @param <T>     对象类型
     */
    @SafeVarargs
    public static <T> void requireNonNull(Supplier<String> message, T... objects) {
        Assert.INSTANCE.set(message).throwsIfNull((Object[]) objects);
    }

    /**
     * 不可为 empty
     *
     * @param object 待断言对象
     * @param <T>    对象类型
     * @return 断言成功返回原对象
     */
    public static <T> T requireNotEmpty(T object) {
        Assert.EMPTY_CHECK.throwsIfEmpty(object);
        return object;
    }

    /**
     * 不可为 empty
     *
     * @param httpStatus http 状态码
     * @param object     待断言对象
     * @param <T>        对象类型
     * @return 断言成功返回原对象
     */
    public static <T> T requireNotEmpty(HttpStatus httpStatus, T object) {
        Assert.INSTANCE.set(httpStatus).throwsIfEmpty(object);
        return object;
    }

    /**
     * 不可为 empty
     *
     * @param httpStatus http 状态码
     * @param message    断言信息
     * @param object     待断言对象
     * @param <T>        对象类型
     * @return 断言成功返回原对象
     */
    public static <T> T requireNotEmpty(HttpStatus httpStatus, Supplier<String> message, T object) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfEmpty(object);
        return object;
    }

    /**
     * 不可为 empty
     *
     * @param message 断言信息
     * @param object  待断言对象
     * @param <T>     对象类型
     * @return 断言成功返回原对象
     */
    public static <T> T requireNotEmpty(Supplier<String> message, T object) {
        Assert.INSTANCE.set(message).throwsIfEmpty(object);
        return object;
    }

    /**
     * 不可为 empty
     *
     * @param exception 自定义异常
     * @param object    待断言对象
     * @param <T>       对象类型
     * @return 断言成功返回原对象
     */
    public static <T> T requireNotEmpty(Exception exception, T object) {
        Assert.INSTANCE.set(exception).throwsIfEmpty(object);
        return object;
    }

    /**
     * 不可有一个为 empty
     *
     * @param objects 待断言对象
     * @param <T>     对象类型
     */
    @SafeVarargs
    public static <T> void requireNotEmpty(T... objects) {
        Assert.EMPTY_CHECK.throwsIfEmpty((Object[]) objects);
    }

    /**
     * 不可有一个为 empty
     *
     * @param httpStatus http 状态码
     * @param objects    待断言对象
     * @param <T>        对象类型
     */
    @SafeVarargs
    public static <T> void requireNotEmpty(HttpStatus httpStatus, T... objects) {
        Assert.INSTANCE.set(httpStatus).throwsIfEmpty((Object[]) objects);
    }

    /**
     * 不可有一个为 empty
     *
     * @param httpStatus http 状态码
     * @param message    断言信息
     * @param objects    待断言对象
     * @param <T>        对象类型
     */
    @SafeVarargs
    public static <T> void requireNotEmpty(HttpStatus httpStatus, Supplier<String> message, T... objects) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfEmpty((Object[]) objects);
    }

    /**
     * 不可有一个为 empty
     *
     * @param objects 待断言对象
     * @param message 断言信息
     * @param <T>     对象类型
     */
    @SafeVarargs
    public static <T> void requireNotEmpty(Supplier<String> message, T... objects) {
        Assert.INSTANCE.set(message).throwsIfEmpty((Object[]) objects);
    }

    /**
     * 不可全部为 empty
     *
     * @param objects 待断言对象
     * @param <T>     对象类型
     */
    @SafeVarargs
    public static <T> void requireNonEmpty(T... objects) {
        Assert.EMPTY_CHECK.throwsIfAllEmpty((Object[]) objects);
    }

    /**
     * 不可全部为 empty
     *
     * @param httpStatus http 状态码
     * @param objects    待断言对象
     * @param <T>        对象类型
     */
    @SafeVarargs
    public static <T> void requireNonEmpty(HttpStatus httpStatus, T... objects) {
        Assert.INSTANCE.set(httpStatus).throwsIfAllEmpty((Object[]) objects);
    }

    /**
     * 不可全部为 empty
     *
     * @param httpStatus http 状态码
     * @param message    断言信息
     * @param objects    待断言对象
     * @param <T>        对象类型
     */
    @SafeVarargs
    public static <T> void requireNonEmpty(HttpStatus httpStatus, Supplier<String> message, T... objects) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfAllEmpty((Object[]) objects);
    }

    /**
     * 不可全部为 empty
     *
     * @param message 断言信息
     * @param objects 待断言对象
     * @param <T>     对象类型
     */
    @SafeVarargs
    public static <T> void requireNonEmpty(Supplier<String> message, T... objects) {
        Assert.INSTANCE.set(message).throwsIfAllEmpty((Object[]) objects);
    }

    /**
     * 不可为 blank
     *
     * @param object 待断言对象
     * @return 断言成功返回原对象
     */
    public static String requireNotBlank(String object) {
        Assert.BLANK_CHECK.throwsIfBlank(object);
        return object;
    }

    /**
     * 不可为 blank
     *
     * @param httpStatus http 状态码
     * @param object     待断言对象
     * @return 断言成功返回原对象
     */
    public static String requireNotBlank(HttpStatus httpStatus, String object) {
        Assert.INSTANCE.set(httpStatus).throwsIfBlank(object);
        return object;
    }

    /**
     * 不可为 blank
     *
     * @param httpStatus http 状态码
     * @param message    断言信息
     * @param object     待断言对象
     * @return 断言成功返回原对象
     */
    public static String requireNotBlank(HttpStatus httpStatus, Supplier<String> message, String object) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfBlank(object);
        return object;
    }

    /**
     * 不可为 blank
     *
     * @param message 断言信息
     * @param object  待断言对象
     * @return 断言成功返回原对象
     */
    public static String requireNotBlank(Supplier<String> message, String object) {
        Assert.INSTANCE.set(message).throwsIfBlank(object);
        return object;
    }

    /**
     * 不可为 blank
     *
     * @param exception 自定义异常
     * @param object    待判断对象
     * @return 断言成功返回原对象
     */
    public static String requireNotBlank(Exception exception, String object) {
        Assert.INSTANCE.set(exception).throwsIfBlank(object);
        return object;
    }

    /**
     * 不可有一个为 blank
     *
     * @param objects 待断言对象
     */
    public static void requireNotBlank(String... objects) {
        Assert.BLANK_CHECK.throwsIfBlank(objects);
    }

    /**
     * 不可有一个为 blank
     *
     * @param httpStatus http 状态码
     * @param objects    待断言对象
     */
    public static void requireNotBlank(HttpStatus httpStatus, String... objects) {
        Assert.INSTANCE.set(httpStatus).throwsIfBlank(objects);
    }

    /**
     * 不可有一个为 blank
     *
     * @param httpStatus http 状态码
     * @param message    断言信息
     * @param objects    待断言对象
     */
    public static void requireNotBlank(HttpStatus httpStatus, Supplier<String> message, String... objects) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfBlank(objects);
    }

    /**
     * 不可有一个为 blank
     *
     * @param message 断言信息
     * @param objects 待断言对象
     */
    public static void requireNotBlank(Supplier<String> message, String... objects) {
        Assert.INSTANCE.set(message).throwsIfBlank(objects);
    }

    /**
     * 不可有一个为 blank
     *
     * @param exception 自定义异常
     * @param objects   待断言对象
     */
    public static void requireNotBlank(Exception exception, String... objects) {
        Assert.INSTANCE.set(exception).throwsIfBlank(objects);
    }

    /**
     * 不可全部为 blank
     *
     * @param objects 待断言对象
     */
    public static void requireNonBlank(String... objects) {
        Assert.BLANK_CHECK.throwsIfAllBlank(objects);
    }

    /**
     * 不可全部为 blank
     *
     * @param httpStatus http 状态码
     * @param objects    待断言对象
     */
    public static void requireNonBlank(HttpStatus httpStatus, String... objects) {
        Assert.INSTANCE.set(httpStatus).throwsIfAllBlank(objects);
    }

    /**
     * 不可全部为 blank
     *
     * @param objects 待断言对象
     * @param message 断言信息
     */
    public static void requireNonBlank(Supplier<String> message, String... objects) {
        Assert.INSTANCE.set(message).throwsIfAllBlank(objects);
    }

    /**
     * 不可全部为 blank
     *
     * @param httpStatus http 状态码
     * @param message    断言信息
     * @param objects    待断言对象
     */
    public static void requireNonBlank(HttpStatus httpStatus, Supplier<String> message, String... objects) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfAllBlank(objects);
    }

    /**
     * 需要为 true
     *
     * @param object 待断言对象
     * @return 断言成功返回原对象
     */
    public static Boolean requireTrue(Boolean object) {
        Assert.TRUE_NEED.throwsIfFalse(object);
        return object;
    }

    /**
     * 需要为 true
     *
     * @param httpStatus http 状态码
     * @param object     待断言对象
     * @return 断言成功返回原对象
     */
    public static Boolean requireTrue(HttpStatus httpStatus, Boolean object) {
        Assert.INSTANCE.set(httpStatus).throwsIfFalse(object);
        return object;
    }

    /**
     * 需要为 true
     *
     * @param httpStatus http 状态码
     * @param message    断言信息
     * @param object     待断言对象
     * @return 断言成功返回原对象
     */
    public static Boolean requireTrue(HttpStatus httpStatus, Supplier<String> message, Boolean object) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfFalse(object);
        return object;
    }

    /**
     * 需要为 true
     *
     * @param message 断言信息
     * @param object  待断言对象
     * @return 断言成功返回原对象
     */
    public static Boolean requireTrue(Supplier<String> message, Boolean object) {
        Assert.INSTANCE.set(message).throwsIfFalse(object);
        return object;
    }

    /**
     * 需要为 true
     *
     * @param exception 自定义异常
     * @param object    待断言对象
     * @return 断言成功返回原对象
     */
    public static Boolean requireTrue(Exception exception, Boolean object) {
        Assert.INSTANCE.set(exception).throwsIfFalse(object);
        return object;
    }

    /**
     * 需要至少有一个为 true
     *
     * @param object 待断言对象
     */
    public static void requireTrue(Boolean... object) {
        Assert.TRUE_NEED.throwsIfAllFalse(object);
    }

    /**
     * 需要至少有一个为 true
     *
     * @param httpStatus http 状态码
     * @param objects    待断言对象
     */
    public static void requireTrue(HttpStatus httpStatus, Boolean... objects) {
        Assert.INSTANCE.set(httpStatus).throwsIfAllFalse(objects);
    }

    /**
     * 需要至少有一个为 true
     *
     * @param httpStatus http 状态码
     * @param message    断言信息
     * @param objects    待断言对象
     */
    public static void requireTrue(HttpStatus httpStatus, Supplier<String> message, Boolean... objects) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfAllFalse(objects);
    }

    /**
     * 需要至少有一个为 true
     *
     * @param objects 待断言对象
     * @param message 断言信息
     */
    public static void requireTrue(Supplier<String> message, Boolean... objects) {
        Assert.INSTANCE.set(message).throwsIfAllFalse(objects);
    }

    /**
     * 需要全部为 true
     *
     * @param object 待断言对象
     */
    public static void requireAllTrue(Boolean... object) {
        Assert.TRUE_NEED.throwsIfFalse(object);
    }

    /**
     * 需要全部为 true
     *
     * @param httpStatus http 状态码
     * @param objects    待断言对象
     */
    public static void requireAllTrue(HttpStatus httpStatus, Boolean... objects) {
        Assert.INSTANCE.set(httpStatus).throwsIfFalse(objects);
    }

    /**
     * 需要全部为 true
     *
     * @param httpStatus http 状态码
     * @param message    断言信息
     * @param objects    待断言对象
     */
    public static void requireAllTrue(HttpStatus httpStatus, Supplier<String> message, Boolean... objects) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfFalse(objects);
    }

    /**
     * 需要全部为 true
     *
     * @param objects 待断言对象
     * @param message 断言信息
     */
    public static void requireAllTrue(Supplier<String> message, Boolean... objects) {
        Assert.INSTANCE.set(message).throwsIfFalse(objects);
    }

    /**
     * 需要为 false
     *
     * @param object 待断言对象
     * @return 断言成功返回原对象
     */
    public static Boolean requireFalse(Boolean object) {
        Assert.FALSE_NEED.throwsIfTrue(object);
        return object;
    }

    /**
     * 需要为 false
     *
     * @param httpStatus http 状态码
     * @param object     待断言对象
     * @return 断言成功返回原对象
     */
    public static Boolean requireFalse(HttpStatus httpStatus, Boolean object) {
        Assert.INSTANCE.set(httpStatus).throwsIfTrue(object);
        return object;
    }

    /**
     * 需要为 false
     *
     * @param httpStatus http 状态码
     * @param message    断言信息
     * @param object     待断言对象
     * @return 断言成功返回原对象
     */
    public static Boolean requireFalse(HttpStatus httpStatus, Supplier<String> message, Boolean object) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfTrue(object);
        return object;
    }

    /**
     * 需要为 false
     *
     * @param message 断言信息
     * @param object  待断言对象
     * @return 断言成功返回原对象
     */
    public static Boolean requireFalse(Supplier<String> message, Boolean object) {
        Assert.INSTANCE.set(message).throwsIfTrue(object);
        return object;
    }

    /**
     * 需要为 false
     *
     * @param exception 自定义异常
     * @param object    待断言对象
     * @return 断言成功返回原对象
     */
    public static Boolean requireFalse(Exception exception, Boolean object) {
        Assert.INSTANCE.set(exception).throwsIfTrue(object);
        return object;
    }

    /**
     * 需要至少有一个为 false
     *
     * @param objects 待断言对象
     */
    public static void requireFalse(Boolean... objects) {
        Assert.FALSE_NEED.throwsIfAllTrue(objects);
    }

    /**
     * 需要至少有一个为 false
     *
     * @param objects 待断言对象
     * @param message 断言信息
     */
    public static void requireFalse(Supplier<String> message, Boolean... objects) {
        Assert.INSTANCE.set(message).throwsIfAllTrue(objects);
    }

    /**
     * 需要至少有一个为 false
     *
     * @param httpStatus http 状态码
     * @param objects    待断言对象
     */
    public static void requireFalse(HttpStatus httpStatus, Boolean... objects) {
        Assert.INSTANCE.set(httpStatus).throwsIfAllTrue(objects);
    }

    /**
     * 需要至少有一个为 false
     *
     * @param httpStatus http 状态码
     * @param message    断言信息
     * @param objects    待断言对象
     */
    public static void requireFalse(HttpStatus httpStatus, Supplier<String> message, Boolean... objects) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfAllTrue(objects);
    }

    /**
     * 需要全部为 false
     *
     * @param objects 待断言对象
     */
    public static void requireAllFalse(Boolean... objects) {
        Assert.FALSE_NEED.throwsIfTrue(objects);
    }

    /**
     * 需要全部为 false
     *
     * @param httpStatus http 状态码
     * @param objects    待断言对象
     */
    public static void requireAllFalse(HttpStatus httpStatus, Boolean... objects) {
        Assert.INSTANCE.set(httpStatus).throwsIfTrue(objects);
    }

    /**
     * 需要全部为 false
     *
     * @param httpStatus http 状态码
     * @param message    断言信息
     * @param objects    待断言对象
     */
    public static void requireAllFalse(HttpStatus httpStatus, Supplier<String> message, Boolean... objects) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfTrue(objects);
    }

    /**
     * 需要全部为 false
     *
     * @param message 断言信息
     * @param objects 待断言对象
     */
    public static void requireAllFalse(Supplier<String> message, Boolean... objects) {
        Assert.INSTANCE.set(message).throwsIfTrue(objects);
    }

    /**
     * 需要为 正整数
     *
     * @param object 待断言对象
     * @param <T>    对象类型
     * @return 断言成功返回原对象
     */
    public static <T extends Number> T requirePositive(T object) {
        Assert.POSITIVE_NEED.throwsIfNotPositive(object);
        return object;
    }

    /**
     * 需要为 正整数
     *
     * @param httpStatus http 状态码
     * @param object     待断言对象
     * @param <T>        对象类型
     * @return 断言成功返回原对象
     */
    public static <T extends Number> T requirePositive(HttpStatus httpStatus, T object) {
        Assert.INSTANCE.set(httpStatus).throwsIfNotPositive(object);
        return object;
    }

    /**
     * 需要为 正整数
     *
     * @param httpStatus http 状态码
     * @param message    断言信息
     * @param object     待断言对象
     * @param <T>        对象类型
     * @return 断言成功返回原对象
     */
    public static <T extends Number> T requirePositive(HttpStatus httpStatus, Supplier<String> message, T object) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfNotPositive(object);
        return object;
    }

    /**
     * 需要为 正整数
     *
     * @param object  待断言对象
     * @param message 断言信息
     * @param <T>     对象类型
     * @return 断言成功返回原对象
     */
    public static <T extends Number> T requirePositive(Supplier<String> message, T object) {
        Assert.INSTANCE.set(message).throwsIfNotPositive(object);
        return object;
    }

    /**
     * 需要全部为 正整数
     *
     * @param objects 待断言对象
     */
    public static void requireAllPositive(Number... objects) {
        Assert.POSITIVE_NEED.throwsIfNotPositive(objects);
    }

    /**
     * 需要全部为 正整数
     *
     * @param httpStatus http 状态码
     * @param objects    待断言对象
     */
    public static void requireAllPositive(HttpStatus httpStatus, Number... objects) {
        Assert.INSTANCE.set(httpStatus).throwsIfNotPositive(objects);
    }

    /**
     * 需要全部为 正整数
     *
     * @param httpStatus http 状态码
     * @param message    断言信息
     * @param objects    待断言对象
     */
    public static void requireAllPositive(HttpStatus httpStatus, Supplier<String> message, Number... objects) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfNotPositive(objects);
    }

    /**
     * 需要全部为 正整数
     *
     * @param message 断言信息
     * @param objects 待断言对象
     */
    public static void requireAllPositive(Supplier<String> message, Number... objects) {
        Assert.INSTANCE.set(message).throwsIfNotPositive(objects);
    }

    /**
     * 需要为 0 或 负数
     *
     * @param object 待断言对象
     * @param <T>    对象类型
     * @return 断言成功返回原对象
     */
    public static <T extends Number> T requireNotPositive(T object) {
        Assert.POSITIVE_CHECK.throwsIfPositive(object);
        return object;
    }

    /**
     * 需要为 0 或 负数
     *
     * @param httpStatus http 状态码
     * @param object     待断言对象
     * @param <T>        对象类型
     * @return 断言成功返回原对象
     */
    public static <T extends Number> T requireNotPositive(HttpStatus httpStatus, T object) {
        Assert.INSTANCE.set(httpStatus).throwsIfPositive(object);
        return object;
    }

    /**
     * 需要为 0 或 负数
     *
     * @param httpStatus http 状态码
     * @param message    断言信息
     * @param object     待断言对象
     * @param <T>        对象类型
     * @return 断言成功返回原对象
     */
    public static <T extends Number> T requireNotPositive(HttpStatus httpStatus, Supplier<String> message, T object) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfPositive(object);
        return object;
    }

    /**
     * 需要为 0 或 负数
     *
     * @param object  待断言对象
     * @param message 断言信息
     * @param <T>     对象类型
     * @return 断言成功返回原对象
     */
    public static <T extends Number> T requireNotPositive(Supplier<String> message, T object) {
        Assert.INSTANCE.set(message).throwsIfPositive(object);
        return object;
    }

    /**
     * 需要有一个为 0 或 负数
     *
     * @param objects 待断言对象
     */
    public static void requireNotPositive(Number... objects) {
        Assert.POSITIVE_CHECK.throwsIfAllPositive(objects);
    }

    /**
     * 需要有一个为 0 或 负数
     *
     * @param httpStatus http 状态码
     * @param objects    待断言对象
     */
    public static void requireNotPositive(HttpStatus httpStatus, Number... objects) {
        Assert.INSTANCE.set(httpStatus).throwsIfAllPositive(objects);
    }

    /**
     * 需要有一个为 0 或 负数
     *
     * @param httpStatus http 状态码
     * @param message    断言信息
     * @param objects    待断言对象
     */
    public static void requireNotPositive(HttpStatus httpStatus, Supplier<String> message, Number... objects) {
        Assert.INSTANCE.set(httpStatus, message).throwsIfAllPositive(objects);
    }

    /**
     * 需要有一个为 0 或 负数
     *
     * @param objects 待断言对象
     * @param message 断言信息
     */
    public static void requireNotPositive(Supplier<String> message, Number... objects) {
        Assert.INSTANCE.set(message).throwsIfAllPositive(objects);
    }

    /**
     * 需要两者相等
     *
     * @param obj1 待断言对象
     * @param obj2 待断言对象
     */
    public static void requireEquals(@Nullable Object obj1, @Nullable Object obj2) {
        Assert.EQUALS_NEED.throwsIfNotEquals(obj1, obj2);
    }

    /**
     * 需要起码有一个对象相等
     *
     * @param obj1 待断言对象
     * @param objs 待断言对象
     */
    public static void requireEquals(@Nullable Object obj1, @Nullable Object... objs) {
        Assert.EQUALS_NEED.throwsIfNotEquals(obj1, objs);
    }

    /**
     * 需要起码有一个对象与集合的元素相同
     *
     * @param obj1 待断言对象
     * @param objs 待断言对象
     * @param <T>  对象类型
     */
    public static <T> void requireCollEquals(@Nullable T obj1, @Nullable Collection<T> objs) {
        Assert.EQUALS_NEED.throwsIfCollNotEquals(obj1, objs);
    }

    /**
     * 需要两者不相等
     *
     * @param obj1 待断言对象
     * @param obj2 待断言对象
     */
    public static void requireNotEquals(@Nullable Object obj1, @Nullable Object obj2) {
        Assert.EQUALS_CHECK.throwsIfEquals(obj1, obj2);
    }

    /**
     * 需要全部不相等
     *
     * @param obj1 待断言对象
     * @param objs 待断言对象
     */
    public static void requireNotEquals(@Nullable Object obj1, @Nullable Object... objs) {
        Assert.EQUALS_CHECK.throwsIfEquals(obj1, objs);
    }

    /**
     * 需要与集合的所有元素都不相等
     *
     * @param obj1 待断言对象
     * @param objs 待断言对象
     * @param <T>  对象类型
     */
    public static <T> void requireCollNotEquals(@Nullable T obj1, @Nullable Collection<T> objs) {
        Assert.EQUALS_CHECK.throwsIfCollEquals(obj1, objs);
    }

    // require end =============================================

    // ============================================= modify value start

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

    /**
     * 当给定对象不为 null 时，返回设定的值
     *
     * @param value          被检查对象
     * @param valueIfNotNull 设定的值
     * @param <T>            对象类型
     * @return 原对象或设定的值
     */
    public static <T> T setIfNotNull(T value, T valueIfNotNull) {
        if (isNotNull(value)) {
            return valueIfNotNull;
        }
        return null;
    }

    /**
     * 当给定对象不为 empty 时，返回设定的值
     *
     * @param value           被检查对象
     * @param valueIfNotEmpty 设定的值
     * @param <T>             对象类型
     * @return 原对象或设定的值
     */
    public static <T> T setIfNotEmpty(T value, T valueIfNotEmpty) {
        if (isNotEmpty(value)) {
            return valueIfNotEmpty;
        }
        return value;
    }

    /**
     * 当给定对象不为 null 时，返回设定的值，否则返回 null
     *
     * @param value           被检查对象
     * @param valueIfNotEmpty 设定的值
     * @param <T>             被检查对象类型
     * @param <K>             满足条件时返回的对象类型
     * @return 满足条件时返回设定的值，否则返回 null
     */
    public static <T, K> List<K> setIfNotNull(T value, Supplier<List<K>> valueIfNotEmpty) {
        if (isNotNull(value)) {
            return valueIfNotEmpty.get();
        }
        return new ArrayList<>();
    }

    /**
     * 当给定对象不为 null 时，返回设定的值，否则返回 null
     *
     * @param value           被检查对象
     * @param valueIfNotEmpty 设定的值
     * @param <T>             被检查对象类型
     * @param <K>             满足条件时返回的对象类型
     * @return 满足条件时返回设定的值，否则返回 null
     */
    public static <T, K> K setIfNotNull(T value, Function<T, K> valueIfNotEmpty) {
        if (isNotNull(value)) {
            return valueIfNotEmpty.apply(value);
        }
        return null;
    }

    /**
     * 当给定数组不为 empty 时，返回设定的值，否则返回空数组
     *
     * @param value           被检查对象
     * @param valueIfNotEmpty 设定的值
     * @param <T>             被检查对象类型
     * @param <K>             满足条件时返回的对象类型
     * @return 满足条件时返回设定的值，否则返回空数组
     */
    public static <T, K> List<K> setIfNotEmpty(List<T> value, Supplier<List<K>> valueIfNotEmpty) {
        if (isNotEmpty(value)) {
            return valueIfNotEmpty.get();
        }
        return new ArrayList<>();
    }

    /**
     * 当给定数组不为 empty 时，返回设定的值，否则返回空数组
     *
     * @param value           被检查对象
     * @param valueIfNotEmpty 设定的值
     * @param <T>             被检查对象类型
     * @param <K>             满足条件时返回的对象类型
     * @return 满足条件时返回设定的值，否则返回空数组
     */
    public static <T, K> List<K> setIfNotEmpty(List<T> value, Function<List<T>, List<K>> valueIfNotEmpty) {
        if (isNotEmpty(value)) {
            return valueIfNotEmpty.apply(value);
        }
        return new ArrayList<>();
    }

    // ============================================= modify value end

    // ============================================= 未整理

    /**
     * 获取实际的值 TODO 场景未够完善
     *
     * @param object 输入值
     * @return 获取实际值
     */
    public static Object getActualValue(String object) {
        if (Objects.isNull(object)) {
            return null;
        }
        try {
            if (NumbersUtil.isInteger(object)) {
                return Integer.valueOf(object);
            }
            if (NumbersUtil.isLong(object)) {
                return Long.valueOf(object);
            }
        } catch (Exception ignore) {
        }
        return object;
    }

    /**
     * 获取序列化ID，类必须要实现{@link java.io.Serializable Serializable}
     *
     * @param clazz 实现了 {@link java.io.Serializable Serializable} 的类
     * @return 序列化ID
     */
    public static long getSerialVersionUID(Class<?> clazz) {
        return ObjectStreamClass.lookup(clazz).getSerialVersionUID();
    }

    /**
     * 比较两个集合的值是否相等（TODO wjm 临时加，后续完善）
     *
     * @param input1 待比较对象
     * @param input2 待比较对象
     * @param <E>    对象类型
     * @return 是否相等
     */
    public static <E> boolean equals(Collection<E> input1, Collection<E> input2) {
        if (input1 == input2) {
            return true;
        }

        if (input2 != null && input1 == null) {
            return false;
        }

        if (input2 == null) {
            return false;
        }

        if (input1.size() == 0 && input2.size() == 0) {
            return true;
        }

        Set<E> duplicateRemovalInput1 = new HashSet<>(input1);
        Set<E> duplicateRemovalInput2 = new HashSet<>(input2);

        if (duplicateRemovalInput1.size() != duplicateRemovalInput2.size()) {
            return false;
        }

        return duplicateRemovalInput1.containsAll(duplicateRemovalInput2);
    }

}

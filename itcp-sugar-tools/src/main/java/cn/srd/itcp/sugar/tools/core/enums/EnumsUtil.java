package cn.srd.itcp.sugar.tools.core.enums;

import cn.hutool.core.util.EnumUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.srd.itcp.sugar.tools.core.ClassesUtil;
import cn.srd.itcp.sugar.tools.core.CollectionsUtil;
import cn.srd.itcp.sugar.tools.core.Objects;
import org.springframework.lang.NonNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 枚举工具
 *
 * @author wjm
 * @since 2021/5/10 17:46
 */
public class EnumsUtil extends EnumUtil {

    private EnumsUtil() {
    }

    /**
     * 指定类是否不为 Enum 类
     *
     * @param clazz
     * @return
     */
    public static boolean isNotEnum(Class<?> clazz) {
        return !isEnum(clazz);
    }

    /**
     * 指定类是否不为 Enum 类
     *
     * @param obj
     * @return
     */
    public static boolean isNotEnum(Object obj) {
        return !isEnum(obj);
    }

    /**
     * 获取 Enum 中 T 类型的字段值，仅支持一种 T 类型的字段；其余情况返回 null
     *
     * @param eEnum
     * @param clazz
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getEnumValue(Enum<?> eEnum, Class<T> clazz) {
        if (Objects.isNull(eEnum, clazz)) {
            return null;
        }

        Class<?> eEnumClass = eEnum.getClass();

        final Field[] fields = ReflectUtil.getFields(eEnumClass);
        final Enum<?>[] enums = (Enum<?>[]) eEnumClass.getEnumConstants();
        String fieldName;
        Class<?> fieldType;
        for (Field field : fields) {
            fieldName = field.getName();
            fieldType = field.getType();

            // 跳过特殊字段、name、enum 类型的字段；只寻找 Enum 类中为对应类型且除特殊字段外的字段
            if (isEnum(fieldType) || Objects.equals("name", fieldName) || isEnumSpecialField(field)) {
                continue;
            }
            if (!ClassesUtil.isAssignable(fieldType, clazz)) {
                continue;
            }
            for (Enum<?> enumObj : enums) {
                if (Objects.equals(eEnum, enumObj)) {
                    return (T) ReflectUtil.getFieldValue(enumObj, field);
                }
            }
        }

        return null;
    }

    /**
     * 根据枚举类获取枚举值
     *
     * @param enumClass
     * @param <E>
     * @return
     */
    @NonNull
    public static <E extends Enum<E>> List<E> getEnumValues(@NonNull Class<E> enumClass) {
        Objects.requireNotNull(enumClass);
        E[] enumConstants = enumClass.getEnumConstants();
        if (enumConstants == null) {
            return new ArrayList<>();
        }
        return CollectionsUtil.newArrayList(enumConstants);
    }

    /**
     * 转换 Enum，不匹配时返回 null<br>
     * 如枚举字段为 SUNDAY，传入 "SUNDAY" 可获取对应的枚举
     *
     * @param enumValue 枚举值
     * @param enumClass 枚举类
     * @param <E>
     * @return
     */
    public static <E extends Enum<E>> E toEnum(String enumValue, Class<E> enumClass) {
        if (Objects.isNull(enumClass, enumValue)) {
            return null;
        }
        try {
            return Enum.valueOf(enumClass, enumValue);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * 转换 Enum，不匹配时返回 null<br>
     * 如枚举字段为 SUNDAY(1，"星期一")，传入 "星期一"/1 可获取对应的枚举；如果多个枚举都有一样的值，则返回匹配到的第一个枚举
     *
     * @param enumFiledValue 枚举字段值
     * @param enumClass      枚举类
     * @return
     */
    public static <E extends Enum<E>> E capableToEnum(Object enumFiledValue, Class<E> enumClass) {
        if (Objects.isNull(enumClass, enumFiledValue)) {
            return null;
        }

        Object currentEnumFiledValue = enumFiledValue;
        if (enumFiledValue instanceof CharSequence) {
            currentEnumFiledValue = enumFiledValue.toString().trim();
        }

        final Field[] fields = ReflectUtil.getFields(enumClass);
        final E[] enums = enumClass.getEnumConstants();
        for (Field field : fields) {
            // 跳过特殊字段、enum 类型的字段；
            if (field.getType().isEnum() || isEnumSpecialField(field)) {
                continue;
            }
            for (E enumObj : enums) {
                if (Objects.equals(currentEnumFiledValue, ReflectUtil.getFieldValue(enumObj, field))) {
                    return enumObj;
                }
            }
        }
        return null;
    }
    // public static <E extends Enum<E>> E capableToEnum(Object enumFiledValue, Class<E> enumClass) {
    //     if (Objects.isNull(enumClass, enumFiledValue)) {
    //         return null;
    //     }
    //
    //     Object currentEnumFiledValue = enumFiledValue;
    //     if (enumFiledValue instanceof CharSequence) {
    //         currentEnumFiledValue = enumFiledValue.toString().trim();
    //     }
    //
    //     final Field[] fields = ReflectUtil.getFields(enumClass);
    //     final Enum<?>[] enums = enumClass.getEnumConstants();
    //     for (Field field : fields) {
    //         //跳过特殊字段、enum 类型的字段；
    //         if (field.getType().isEnum() || isEnumSpecialField(field)) {
    //             continue;
    //         }
    //         for (Enum<?> enumObj : enums) {
    //             if (Objects.equals(currentEnumFiledValue, ReflectUtil.getFieldValue(enumObj, field))) {
    //                 return (E) enumObj;
    //             }
    //         }
    //     }
    //     return null;
    // }

    /**
     * 是否是Enum的特殊字段： ENUM$VALUES、ordinal
     *
     * @param field
     * @return
     */
    public static boolean isEnumSpecialField(Field field) {
        return "ENUM$VALUES".equals(field.getName()) || "ordinal".equals(field.getName());
    }

}
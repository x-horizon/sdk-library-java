package cn.library.java.tool.enums.strategy;

import cn.library.java.tool.enums.EnumAutowired;

import java.util.List;

/**
 * see {@link EnumAutowired#matchRule()}
 *
 * @author wjm
 * @since 2021-09-08 16:07
 */
public interface EnumAutowiredFieldMatchRule {

    /**
     * get the most suitable subclass simple name implemented by {@link EnumAutowired#rootClasses()} to autowired the subclass instance in spring ioc
     *
     * @param enumField                        the enum marked with {@link EnumAutowired} field
     * @param enumAutowiredSubclassSimpleNames all subclass simple names implement by {@link EnumAutowired#rootClasses()}
     * @param <E>                              the enum marked with {@link EnumAutowired} type
     * @return the most suitable subclass simple name
     */
    <E extends Enum<E>> String getMostSuitableAutowiredClassSimpleName(Enum<E> enumField, List<String> enumAutowiredSubclassSimpleNames);

}
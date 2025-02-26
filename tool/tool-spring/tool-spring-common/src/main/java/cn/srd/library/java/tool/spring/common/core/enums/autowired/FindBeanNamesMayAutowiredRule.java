package cn.srd.library.java.tool.spring.common.core.enums.autowired;

import java.util.List;
import java.util.Set;

/**
 * 匹配枚举可能要注入的实现类规则
 *
 * @author wjm
 * @since 2021/9/8 16:07
 */
public interface FindBeanNamesMayAutowiredRule {

    /**
     * 获取到指定的父级接口所有实现类后，根据该规则，匹配到枚举可能要注入的实现类
     *
     * @param internalEnumWithEnumAutowired         标记了 {@link EnumAutowired} 的枚举中的内部枚举
     * @param autowiredBeanChildrenClassSimpleNames {@link EnumAutowired#autowiredBeanClass()} 接口的所有实现类类名
     * @param <E>                                   枚举中的内部枚举类类型
     * @return 枚举可能要注入的实现类类名
     */
    <E extends Enum<E>> List<String> getBeanNamesMayAutowired(Enum<E> internalEnumWithEnumAutowired, Set<String> autowiredBeanChildrenClassSimpleNames);

}

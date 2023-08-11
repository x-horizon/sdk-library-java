package cn.srd.sugar.tool.spring.common.core.enums.autowired;

import cn.srd.sugar.tool.lang.core.CollectionsUtil;
import cn.srd.sugar.tool.lang.core.StringsUtil;
import cn.srd.sugar.tool.constant.core.StringPool;

import java.util.List;
import java.util.Set;

/**
 * 匹配枚举可能要注入的实现类规则：{@link EnumAutowired#autowiredBeanClass()} 的实现类类名包含枚举名（忽略大小写）
 *
 * @author wjm
 * @since 2021/9/8 16:07
 */
public class FindBeanNamesMayAutowiredByBeanNameContainsIgnoreCaseEnumNameRule implements FindBeanNamesMayAutowiredRule {

    @Override
    public <E extends Enum<E>> List<String> getBeanNamesMayAutowired(Enum<E> internalEnumWithEnumAutowired, Set<String> autowiredBeanChildrenClassSimpleNames) {
        return CollectionsUtil.filtersToList(autowiredBeanChildrenClassSimpleNames, autowiredBeanChildrenClassSimpleName ->
                StringsUtil.containsIgnoreCase(autowiredBeanChildrenClassSimpleName, StringsUtil.removeAll(internalEnumWithEnumAutowired.name(), StringPool.UNDERLINE))
        );
    }

}

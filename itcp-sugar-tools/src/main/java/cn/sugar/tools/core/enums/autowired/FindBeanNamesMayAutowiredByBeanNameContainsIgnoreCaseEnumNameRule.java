package cn.sugar.tools.core.enums.autowired;

import cn.sugar.tools.constant.StringPool;
import cn.sugar.tools.core.CollectionsUtil;
import cn.sugar.tools.core.StringsUtil;

import java.util.List;
import java.util.Set;

/**
 * 匹配枚举可能要注入的实现类规则：{@link EnumAutowired#autowiredBeanClass()} 的实现类类名包含枚举名（忽略大小写）
 *
 * @author wjm
 * @date 2021/9/8 16:07
 */
public class FindBeanNamesMayAutowiredByBeanNameContainsIgnoreCaseEnumNameRule implements FindBeanNamesMayAutowiredRule {

    @Override
    public <E extends Enum<E>> List<String> getBeanNamesMayAutowired(Enum<E> internalEnumWithEnumAutowired, Set<String> autowiredBeanChildrenClassSimpleNames) {
        return CollectionsUtil.filters(autowiredBeanChildrenClassSimpleNames, autowiredBeanChildrenClassSimpleName ->
                StringsUtil.containsIgnoreCase(autowiredBeanChildrenClassSimpleName, StringsUtil.removeAll(internalEnumWithEnumAutowired.name(), StringPool.UNDERLINE))
        );
    }

}

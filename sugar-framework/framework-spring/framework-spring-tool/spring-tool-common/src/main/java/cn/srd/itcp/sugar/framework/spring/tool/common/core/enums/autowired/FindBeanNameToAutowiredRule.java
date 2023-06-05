package cn.srd.itcp.sugar.framework.spring.tool.common.core.enums.autowired;

import java.util.List;

/**
 * 匹配枚举最终要注入的实现类规则
 *
 * @author wjm
 * @since 2021/9/8 16:07
 */
public interface FindBeanNameToAutowiredRule {

    /**
     * 根据 {@link EnumAutowired#findBeanNamesMayAutowiredRule()} 获取到枚举可能要注入的实现类后，根据该规则，筛选出最终要注入的实现类
     *
     * @param beanNamesMayAutowired 枚举可能要注入的实现类类名
     * @return 最终要注入的实现类类名
     */
    String getBeanNameToAutowired(List<String> beanNamesMayAutowired);

}

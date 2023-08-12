package cn.srd.library.tool.spring.common.core.enums.autowired;

import cn.srd.library.tool.lang.core.CollectionsUtil;

import java.util.List;

/**
 * 匹配枚举最终要注入的实现类规则：从可能要注入的实现类中的获取其最短的类名
 *
 * @author wjm
 * @since 2021/9/8 16:07
 */
public class FindBeanNameToAutowiredByShortestBeanNameRule implements FindBeanNameToAutowiredRule {

    @Override
    public String getBeanNameToAutowired(List<String> beanNamesMayAutowired) {
        return CollectionsUtil.getMin(beanNamesMayAutowired);
    }

}

package cn.srd.itcp.sugar.cache.all.support;

import cn.srd.itcp.sugar.cache.contract.core.CacheTemplate;
import cn.srd.itcp.sugar.framework.spring.tool.common.core.enums.autowired.EnumAutowired;
import lombok.Getter;

/**
 * @author wjm
 * @since 2023-06-06 16:14:13
 */
@Getter
@EnumAutowired(autowiredBeanClass = CacheTemplate.class)
public enum CacheType {

    MAP,
    CAFFEINE,
    REDIS,

    ;

    CacheType() {
    }

    private CacheTemplate<Object> template;

}



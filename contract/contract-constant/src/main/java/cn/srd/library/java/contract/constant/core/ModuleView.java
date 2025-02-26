package cn.srd.library.java.contract.constant.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * all module name of library
 *
 * @author wjm
 * @since 2023-06-29 20:25:44
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ModuleView {

    /**
     * the enum autowired module name
     */
    public static final String ENUM_AUTOWIRED_SYSTEM = "Enum Autowired System - ";

    /**
     * the cache system module name
     */
    public static final String CACHE_SYSTEM = "Cache System - ";

    /**
     * the actor system module name
     */
    public static final String ACTOR_SYSTEM = "Actor System - ";

    /**
     * the orm mybatis flex module name
     */
    public static final String ORM_MYBATIS_FLEX_SYSTEM = "ORM Mybatis Flex System - ";

}

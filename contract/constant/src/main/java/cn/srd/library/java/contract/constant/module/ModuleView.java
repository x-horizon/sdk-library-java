// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.contract.constant.module;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * all module name of library
 *
 * @author wjm
 * @since 2023-06-29 20:25
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ModuleView {

    /**
     * the actor system module name
     */
    public static final String ACTOR_SYSTEM = "Actor System - ";

    /**
     * the annotation system module name
     */
    public static final String ANNOTATION_SYSTEM = "Annotation System - ";

    /**
     * the cache system module name
     */
    public static final String CACHE_SYSTEM = "Cache System - ";

    /**
     * the class system module name
     */
    public static final String CLASS_SYSTEM = "Class System - ";

    /**
     * the enum system module name
     */
    public static final String ENUM_SYSTEM = "Enum System - ";

    /**
     * the orm mybatis system module name
     */
    public static final String ORM_MYBATIS_SYSTEM = "ORM Mybatis System - ";

    /**
     * the web system module name
     */
    public static final String WEB_SYSTEM = "Web System - ";

}

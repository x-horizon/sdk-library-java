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
     * the cache system module name
     */
    public static final String CACHE_SYSTEM = "Cache System - ";

    /**
     * the actor system module name
     */
    public static final String CONCURRENT_ACTOR_SYSTEM = "Concurrent Actor System - ";

    /**
     * the orm mybatis system module name
     */
    public static final String ORM_MYBATIS_SYSTEM = "ORM Mybatis System - ";

    /**
     * the annotation system module name
     */
    public static final String TOOL_ANNOTATION_SYSTEM = "Tool Annotation System - ";

    /**
     * the class system module name
     */
    public static final String TOOL_CLASS_SYSTEM = "Tool Class System - ";

    /**
     * the enum system module name
     */
    public static final String TOOL_ENUM_SYSTEM = "Tool Enum System - ";

    /**
     * the snowflake id system module name
     */
    public static final String TOOL_SNOWFLAKE_ID_SYSTEM = "Tool Snowflake ID System - ";

    /**
     * the spring webmvc system module name
     */
    public static final String TOOL_SPRING_WEBMVC_SYSTEM = "Tool Spring WebMVC System - ";

}

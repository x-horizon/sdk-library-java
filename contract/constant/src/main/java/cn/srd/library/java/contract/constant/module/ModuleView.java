// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
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
     * the root module name
     */
    public static final String ROOT_MODULE = "Library Java - ";

    /**
     * the cache system module name
     */
    public static final String CACHE_SYSTEM = ROOT_MODULE + "Cache System - ";

    /**
     * the actor system module name
     */
    public static final String CONCURRENT_ACTOR_SYSTEM = ROOT_MODULE + "Concurrent Actor System - ";

    /**
     * the message engine system module name
     */
    public static final String MESSAGE_ENGINE_SYSTEM = ROOT_MODULE + "Message Engine System - ";

    /**
     * the orm mybatis system module name
     */
    public static final String ORM_MYBATIS_SYSTEM = ROOT_MODULE + "ORM Mybatis System - ";

    /**
     * the oss system module name
     */
    public static final String OSS_SYSTEM = ROOT_MODULE + "Oss System - ";

    /**
     * the annotation system module name
     */
    public static final String TOOL_ANNOTATION_SYSTEM = ROOT_MODULE + "Tool Annotation System - ";

    /**
     * the class system module name
     */
    public static final String TOOL_CLASS_SYSTEM = ROOT_MODULE + "Tool Class System - ";

    /**
     * the enum system module name
     */
    public static final String TOOL_ENUM_SYSTEM = ROOT_MODULE + "Tool Enum System - ";

    /**
     * the snowflake id system module name
     */
    public static final String TOOL_SNOWFLAKE_ID_SYSTEM = ROOT_MODULE + "Tool Snowflake ID System - ";

    /**
     * the spring webmvc system module name
     */
    public static final String TOOL_SPRING_WEBMVC_SYSTEM = ROOT_MODULE + "Tool Spring WebMVC System - ";

    /**
     * the spring webflux system module name
     */
    public static final String TOOL_SPRING_WEBFLUX_SYSTEM = ROOT_MODULE + "Tool Spring WebFlux System - ";

    /**
     * the web grpc system module name
     */
    public static final String WEB_GRPC_SYSTEM = ROOT_MODULE + "Web Grpc System - ";

}
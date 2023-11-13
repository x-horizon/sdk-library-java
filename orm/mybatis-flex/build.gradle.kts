// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.ORM_MYBATIS_CONTRACT)))
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_ID_SNOWFLAKE))) // TODO wjm 此处实现不够好，与 snowflake id 强绑定，客户端不一定需要用到 snowflake id，目前客户端必须提供正确的 redis 配置，否则项目启动报错

    api(GradleDependency.ORM_MYBATIS_FLEX.withoutVersion)

    annotationProcessor(project(GradleModule.toReferenceName(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_MYBATIS_FLEX)))
    testAnnotationProcessor(project(GradleModule.toReferenceName(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_MYBATIS_FLEX)))
}
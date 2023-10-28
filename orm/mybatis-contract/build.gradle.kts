// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.POOL_DATABASE_HIKARICP)))
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_ENUMS)))
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_ID_SNOWFLAKE)))
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_LANG)))
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_SPRING_CONTRACT)))

    implementation(GradleDependency.DOC_KNIFE4J.withoutVersion)
    api(GradleDependency.TOOL_VALIDATION_HIBERNATE.withoutVersion)
}
// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_ENUMS)))
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_LANG)))
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_SPRING_CONTRACT)))
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_CONVERT_ALL)))
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_ID_SNOWFLAKE)))
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_ID_UUID)))

    api(GradleDependency.MESSAGE_SPRING_INTEGRATION_STREAM.withoutVersion)
}
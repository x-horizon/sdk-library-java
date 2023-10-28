// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_LANG)))
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_VALIDATION_HIBERNATE)))
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_VALIDATION_SPRING_BOOT)))

    api(GradleDependency.TOOL_SERIALIZATION_JACKSON_CORE.withoutVersion)
    api(GradleDependency.TOOL_SERIALIZATION_JACKSON_DATABIND.withoutVersion)
    api(GradleDependency.TOOL_SERIALIZATION_JACKSON_ANNOTATION.withoutVersion)
}
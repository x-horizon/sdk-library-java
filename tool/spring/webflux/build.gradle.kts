// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_CONVERT_ALL)))
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_SPRING_CONTRACT)))

    api(GradleDependency.FRAMEWORK_SPRING_BOOT_WEBFLUX.withoutVersion)
}
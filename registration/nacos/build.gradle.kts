// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_LANG)))

//    api(GradleDependency.REGISTRATION_SPRING_CLOUD_ALIBABA_NACOS_CONFIG.withoutVersion)
    api(GradleDependency.REGISTRATION_SPRING_CLOUD_ALIBABA_NACOS_DISCOVERY.withoutVersion)
}
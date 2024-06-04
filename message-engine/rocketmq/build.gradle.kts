// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.MESSAGE_ENGINE_CONTRACT)))

    api(GradleDependency.MESSAGE_ROCKETMQ_SPRING_CLOUD_STREAM.withoutVersion)
    api(GradleDependency.MESSAGE_ROCKETMQ_TODO.withoutVersion) { exclude(group = "commons-logging", module = "commons-logging") }
}
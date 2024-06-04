// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_LANG)))

    api(GradleDependency.METRIC_BIT_WALKER_USER_AGENT.withoutVersion)
    api(GradleDependency.METRIC_OSHI_CORE.withoutVersion)
}
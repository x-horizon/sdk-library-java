// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_THREAD_POOL_CONTRACT)))

    api(GradleDependency.TOOL_THREAD_POOL_DYNAMIC_TP_INTEGRATION_GRPC.withoutVersion)
}
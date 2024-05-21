// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.CONTRACT_COMPONENT_MESSAGE_SPRING)))

    api(GradleDependency.MESSAGE_SPRING_CLOUD_STREAM_KAFKA.withoutVersion)
}
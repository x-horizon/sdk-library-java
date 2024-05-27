// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.MESSAGE_ENGINE_CONTRACT)))

    api(GradleDependency.MESSAGE_KAFKA_SPRING_CLOUD_STREAM.withoutVersion)
}
// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.CONTRACT_COMPONENT_MESSAGE_ENGINE_SPRING)))

    api(GradleDependency.MESSAGE_SPRING_INTEGRATION_MQTT.withoutVersion)

    testImplementation(project(GradleModule.toReferenceName(GradleModule.TEST_JUNIT)))
    testImplementation(project(GradleModule.toReferenceName(GradleModule.TEST_SPRING_BOOT)))
    testImplementation(project(GradleModule.toReferenceName(GradleModule.TEST_SPRING_INTEGRATION)))
    testImplementation(project(GradleModule.toReferenceName(GradleModule.TOOL_LOG)))
}
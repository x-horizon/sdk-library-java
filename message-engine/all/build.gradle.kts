// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.MESSAGE_ENGINE_KAFKA)))
    api(project(GradleModule.toReferenceName(GradleModule.MESSAGE_ENGINE_MQTT_V3)))
    api(project(GradleModule.toReferenceName(GradleModule.MESSAGE_ENGINE_MQTT_V5)))
    api(project(GradleModule.toReferenceName(GradleModule.MESSAGE_ENGINE_RABBITMQ)))
    api(project(GradleModule.toReferenceName(GradleModule.MESSAGE_ENGINE_REDIS)))
    api(project(GradleModule.toReferenceName(GradleModule.MESSAGE_ENGINE_ROCKETMQ)))

    testImplementation(project(GradleModule.toReferenceName(GradleModule.TEST_SPRING_INTEGRATION)))
}
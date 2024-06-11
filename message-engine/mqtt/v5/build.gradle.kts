// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.MESSAGE_ENGINE_MQTT_CONTRACT)))

    api(GradleDependency.MESSAGE_MQTT_SPRING_INTEGRATION.withoutVersion)
    api(GradleDependency.MESSAGE_MQTT_V5_ECLIPSE_PAHO.withoutVersion)

    testImplementation(project(GradleModule.toReferenceName(GradleModule.TEST_SPRING_INTEGRATION)))
}
// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

plugins {
    id(GradlePlugin.PROTOBUF) version (GradlePlugin.PROTOBUF_VERSION)
}

dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.WEB_GRPC_CONTRACT)))

    api(GradleDependency.WEB_GRPC_SPRING_BOOT_SERVER.withoutVersion)

    testImplementation(project(GradleModule.toReferenceName(GradleModule.TEST_JUNIT)))
    testImplementation(project(GradleModule.toReferenceName(GradleModule.TEST_SPRING_BOOT)))
    testImplementation(project(GradleModule.toReferenceName(GradleModule.TOOL_SPRING_WEBMVC)))
    testImplementation(testFixtures(project(GradleModule.toReferenceName(GradleModule.WEB_GRPC_CONTRACT))))
}
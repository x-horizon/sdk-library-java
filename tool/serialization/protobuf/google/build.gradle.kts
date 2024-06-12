// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

plugins {
    id(GradlePlugin.PROTOBUF) version (GradlePlugin.PROTOBUF_VERSION)
}

dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_LANG)))

    api(GradleDependency.TOOL_SERIALIZATION_PROTOBUF_GOOGLE_JAVA.withoutVersion)
    api(GradleDependency.TOOL_SERIALIZATION_PROTOBUF_GOOGLE_JAVA_UTIL.withoutVersion)
}

protobuf {
    protoc {
        artifact = GradleDependency.withVersion(GradleDependency.TOOL_SERIALIZATION_PROTOBUF_GOOGLE_PROTOC)
    }
    generateProtoTasks {
        ofSourceSet("main")
        ofSourceSet("test")
    }
}
plugins {
    id(GradlePlugin.PROTOBUF) version (GradlePlugin.PROTOBUF_VERSION)
}

dependencies {
    api(project(GradleModule.TOOL_SERIALIZATION_PROTOBUF_GOOGLE))
}

protobuf {
    protoc { artifact = GradleDependency.withVersion(GradleDependency.TOOL_SERIALIZATION_PROTOBUF_GOOGLE_PROTOC) }
    generateProtoTasks {
        ofSourceSet("main")
        ofSourceSet("test")
    }
}
import com.google.protobuf.gradle.id

plugins {
    id(GradlePlugin.JAVA_TEST_FIXTURES)
    id(GradlePlugin.PROTOBUF) version (GradlePlugin.PROTOBUF_VERSION)
}

dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_CONVERT_PROTOBUF)))
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_LANG)))

    api(GradleDependency.TOOL_ANNOTATION_API_JAVAX.withoutVersion)
    api(GradleDependency.WEB_GRPC_API.withoutVersion)
    api(GradleDependency.WEB_GRPC_PROTOBUF.withoutVersion)
    api(GradleDependency.WEB_GRPC_STUB.withoutVersion)
}

protobuf {
    protoc { artifact = GradleDependency.withVersion(GradleDependency.TOOL_SERIALIZATION_PROTOBUF_GOOGLE_PROTOC) }
    plugins { id(GradlePlugin.GRPC) { artifact = GradleDependency.withVersion(GradleDependency.WEB_GRPC_PROTO_GEN) } }
    generateProtoTasks {
        ofSourceSet("main").forEach { it.plugins { id(GradlePlugin.GRPC) { } } }
        ofSourceSet("test").forEach { it.plugins { id(GradlePlugin.GRPC) { } } }
        ofSourceSet("testFixtures").forEach { it.plugins { id(GradlePlugin.GRPC) { } } }
    }
}
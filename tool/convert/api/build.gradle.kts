plugins {
    id(GradlePlugin.PROTOBUF).version(GradlePlugin.PROTOBUF_VERSION)
}

dependencies {
    implementation(project(GradleModule.TOOL_CONVERT_JACKSON))
    implementation(project(GradleModule.TOOL_CONVERT_MAPSTRUCT_PLUS))
    implementation(project(GradleModule.TOOL_CONVERT_PROTOBUF))
    implementation(project(GradleModule.TOOL_CONVERT_SPRING))

    annotationProcessor(project(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_LOMBOK_MAPSTRUCT_BINDING))
    annotationProcessor(project(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_MAPSTRUCT_PLUS))
    testAnnotationProcessor(project(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_LOMBOK_MAPSTRUCT_BINDING))
    testAnnotationProcessor(project(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_MAPSTRUCT_PLUS))
}

protobuf {
    protoc { artifact = GradleDependency.withVersion(GradleDependency.TOOL_SERIALIZATION_PROTOBUF_GOOGLE_PROTOC) }
    generateProtoTasks {
        ofSourceSet("main")
        ofSourceSet("test")
    }
}
plugins {
    id(GradlePlugin.PROTOBUF) version (GradlePlugin.PROTOBUF_VERSION)
}

dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.WEB_GRPC_CONTRACT)))
    implementation(project(GradleModule.toReferenceName(GradleModule.TOOL_SPRING_WEBMVC)))
    implementation(project(GradleModule.toReferenceName(GradleModule.TOOL_SPRING_WEBFLUX)))

    api(GradleDependency.WEB_GRPC_SPRING_BOOT_CLIENT.withoutVersion)

    testImplementation(testFixtures(project(GradleModule.toReferenceName(GradleModule.WEB_GRPC_CONTRACT))))
}
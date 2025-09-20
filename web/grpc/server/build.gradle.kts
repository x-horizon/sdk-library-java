plugins {
    id(GradlePlugin.PROTOBUF).version(GradlePlugin.PROTOBUF_VERSION)
}

dependencies {
    api(project(GradleModule.WEB_GRPC_CONTRACT))

    api(GradleDependency.WEB_GRPC_SPRING_BOOT_SERVER.withoutVersion)

    testImplementation(project(GradleModule.TOOL_SPRING_WEBMVC))
    testImplementation(testFixtures(project(GradleModule.WEB_GRPC_CONTRACT)))
}
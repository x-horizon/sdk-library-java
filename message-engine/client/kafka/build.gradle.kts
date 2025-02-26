dependencies {
    api(project(GradleModule.MESSAGE_ENGINE_CLIENT_CONTRACT))

    api(GradleDependency.MESSAGE_KAFKA_SPRING_INTEGRATION.withoutVersion)

    testImplementation(project(GradleModule.TEST_SPRING_INTEGRATION))
}
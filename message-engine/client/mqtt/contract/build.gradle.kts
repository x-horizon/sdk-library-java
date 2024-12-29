dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.MESSAGE_ENGINE_CLIENT_CONTRACT)))

    api(GradleDependency.MESSAGE_MQTT_SPRING_INTEGRATION.withoutVersion)

    testImplementation(project(GradleModule.toReferenceName(GradleModule.TEST_SPRING_INTEGRATION)))
}
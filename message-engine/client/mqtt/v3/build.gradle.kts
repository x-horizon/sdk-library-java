dependencies {
    api(project(GradleModule.MESSAGE_ENGINE_CLIENT_MQTT_CONTRACT))

    api(GradleDependency.MESSAGE_MQTT_SPRING_INTEGRATION.withoutVersion)

    testImplementation(project(GradleModule.TEST_SPRING_INTEGRATION))
}
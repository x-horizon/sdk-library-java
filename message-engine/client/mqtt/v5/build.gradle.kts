dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.MESSAGE_ENGINE_CLIENT_MQTT_CONTRACT)))

    api(GradleDependency.MESSAGE_MQTT_SPRING_INTEGRATION.withoutVersion)
    api(GradleDependency.MESSAGE_MQTT_V5_ECLIPSE_PAHO.withoutVersion)

    testImplementation(project(GradleModule.toReferenceName(GradleModule.TEST_SPRING_INTEGRATION)))
}
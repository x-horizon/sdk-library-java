dependencies {
    api(project(GradleModule.MESSAGE_ENGINE_CLIENT_MQTT_CONTRACT))

    api(GradleDependency.MESSAGE_MQTT_V5_ECLIPSE_PAHO.withoutVersion)

    testImplementation(project(GradleModule.TEST_SPRING_INTEGRATION))
}
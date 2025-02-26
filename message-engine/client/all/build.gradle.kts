dependencies {
    api(project(GradleModule.MESSAGE_ENGINE_CLIENT_KAFKA))
    api(project(GradleModule.MESSAGE_ENGINE_CLIENT_MQTT_V3))
    api(project(GradleModule.MESSAGE_ENGINE_CLIENT_MQTT_V5))
    api(project(GradleModule.MESSAGE_ENGINE_CLIENT_RABBITMQ))
    api(project(GradleModule.MESSAGE_ENGINE_CLIENT_REDIS_STREAM))
    api(project(GradleModule.MESSAGE_ENGINE_CLIENT_ROCKETMQ))

    testImplementation(project(GradleModule.TEST_SPRING_INTEGRATION))
}
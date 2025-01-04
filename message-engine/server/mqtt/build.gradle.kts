dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.MESSAGE_ENGINE_SERVER_CONTRACT)))
    api(GradleDependency.WEB_IO_NETTY_CODEC_MQTT.withoutVersion)

    testImplementation(project(GradleModule.toReferenceName(GradleModule.CONCURRENT_ACTOR)))
}
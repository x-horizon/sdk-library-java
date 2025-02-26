dependencies {
    api(project(GradleModule.MESSAGE_ENGINE_CLIENT_CONTRACT))

    api(GradleDependency.MESSAGE_ROCKETMQ_SPRING_CLOUD_STREAM.withoutVersion)
    api(GradleDependency.MESSAGE_ROCKETMQ_TODO.withoutVersion) { exclude(group = "commons-logging", module = "commons-logging") }
}
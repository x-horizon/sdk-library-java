dependencies {
    api(project(GradleModule.TOOL_LANG))

    api(GradleDependency.REGISTRATION_SPRING_CLOUD_ZOOKEEPER.withoutVersion)
}
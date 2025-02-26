dependencies {
    api(project(GradleModule.TOOL_LANG))

    api(GradleDependency.CLOUD_NATIVE_DOCKER_CORE.withoutVersion)
    api(GradleDependency.CLOUD_NATIVE_DOCKER_TRANSPORT_OKHTTP.withoutVersion)
}
dependencies {
    api(project(GradleModule.TOOL_LANG))
    api(project(GradleModule.TOOL_SPRING_WEBMVC))

    api(GradleDependency.TOOL_RATE_LIMITING_SENTINEL.withoutVersion)
}
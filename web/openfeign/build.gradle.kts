dependencies {
    api(project(GradleModule.TOOL_CONVERT_API))
    api(project(GradleModule.TOOL_CONVERT_JACKSON))
    api(project(GradleModule.TOOL_LANG))
    api(project(GradleModule.TOOL_SPRING_CONTRACT))

    api(GradleDependency.WEB_OPEN_FEIGN_OKHTTP.withoutVersion)
    api(GradleDependency.WEB_SPRING_CLOUD_OPEN_FEIGN.withoutVersion)
}
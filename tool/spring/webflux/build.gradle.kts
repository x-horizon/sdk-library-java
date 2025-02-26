dependencies {
    api(project(GradleModule.TOOL_CONVERT_API))
    api(project(GradleModule.TOOL_CONVERT_JACKSON))
    api(project(GradleModule.TOOL_SPRING_CONTRACT))

    api(GradleDependency.FRAMEWORK_SPRING_BOOT_WEBFLUX.withoutVersion)
}
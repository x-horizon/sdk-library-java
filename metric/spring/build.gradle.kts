dependencies {
    api(project(GradleModule.TOOL_LANG))

    api(GradleDependency.METRIC_SPRING_BOOT_ACTUATOR.withoutVersion)
}
dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_LANG)))

    api(GradleDependency.LOG_SPRING_BOOT.withoutVersion)
}
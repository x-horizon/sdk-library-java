dependencies {
    api(project(GradleModule.TOOL_LANG))
    api(project(GradleModule.TOOL_SPRING_CONTRACT))

    api(GradleDependency.CACHE_REDISSON.withoutVersion)
}
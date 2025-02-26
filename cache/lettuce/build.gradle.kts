dependencies {
    api(project(GradleModule.TOOL_LANG))

    api(GradleDependency.CACHE_LETTUCE.withoutVersion)
}
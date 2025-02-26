dependencies {
    api(project(GradleModule.CACHE_REDIS))
    api(project(GradleModule.TOOL_JNA))
    api(project(GradleModule.TOOL_LANG))
    api(project(GradleModule.TOOL_SPRING_CONTRACT))

    api(GradleDependency.TOOL_YITTER_SNOWFLAKE_ID.withoutVersion)
}
dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.CACHE_REDIS)))
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_JNA)))
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_LANG)))
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_SPRING_CONTRACT)))

    api(GradleDependency.TOOL_YITTER_SNOWFLAKE_ID.withoutVersion)
}
dependencies {
    api(project(GradleModule.MESSAGE_ENGINE_CONTRACT))
    api(project(GradleModule.TOOL_ID_SNOWFLAKE))

    api(GradleDependency.MESSAGE_SPRING_INTEGRATION_STREAM.withoutVersion)
}
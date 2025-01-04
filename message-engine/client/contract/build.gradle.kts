dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.MESSAGE_ENGINE_CONTRACT)))
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_ID_SNOWFLAKE)))
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_ID_UUID)))

    api(GradleDependency.MESSAGE_SPRING_INTEGRATION_STREAM.withoutVersion)
}
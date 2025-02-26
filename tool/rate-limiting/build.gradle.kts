dependencies {
    api(project(GradleModule.TOOL_LANG))

    api(GradleDependency.TOOL_RATE_LIMITING_BUCKET4J.withoutVersion)
}
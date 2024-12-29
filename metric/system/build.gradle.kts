dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_LANG)))

    api(GradleDependency.METRIC_BIT_WALKER_USER_AGENT.withoutVersion)
    api(GradleDependency.METRIC_OSHI_CORE.withoutVersion)
}
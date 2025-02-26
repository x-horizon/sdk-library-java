dependencies {
    api(project(GradleModule.TOOL_LANG))

    api(GradleDependency.METRIC_MICROMETER_PROMETHEUS.withoutVersion)
}
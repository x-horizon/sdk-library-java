dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_LANG)))

    api(GradleDependency.METRIC_MICROMETER_PROMETHEUS.withoutVersion)
}
dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_LANG)))

    api(GradleDependency.METRIC_DROPWIZARD_CORE.withoutVersion)
    api(GradleDependency.METRIC_DROPWIZARD_JMX.withoutVersion)
    api(GradleDependency.METRIC_DROPWIZARD_JVM.withoutVersion)
}
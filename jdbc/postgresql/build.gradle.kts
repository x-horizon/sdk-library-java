dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_LANG)))

    api(GradleDependency.JDBC_POSTGRESQL.withoutVersion)
}
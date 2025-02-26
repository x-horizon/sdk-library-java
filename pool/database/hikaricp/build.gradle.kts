dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_LANG)))

    api(GradleDependency.POOL_DATABASE_HIKARICP.withoutVersion)
}
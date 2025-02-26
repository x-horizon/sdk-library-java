dependencies {
    api(project(GradleModule.TOOL_LANG))

    api(GradleDependency.POOL_DATABASE_HIKARICP.withoutVersion)
}
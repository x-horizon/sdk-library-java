dependencies {
    api(project(GradleModule.TOOL_LANG))

    api(GradleDependency.TEST_JUNIT.withoutVersion)
}
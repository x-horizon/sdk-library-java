dependencies {
    api(project(GradleModule.TOOL_LANG))

    api(GradleDependency.TEST_MOCKITO_CORE.withoutVersion)
}
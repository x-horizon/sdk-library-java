dependencies {
    api(project(GradleModule.TOOL_LANG))

    api(GradleDependency.TEST_OPENJDK_JMH_CORE.withoutVersion)
}
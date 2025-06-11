dependencies {
    api(project(GradleModule.TOOL_LANG))

    api(GradleDependency.TEST_JUNIT.withoutVersion)
    api(GradleDependency.TEST_JUNIT_PLATFORM_LAUNCHER.withoutVersion)
}
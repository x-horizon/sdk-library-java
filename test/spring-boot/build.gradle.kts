dependencies {
    api(project(GradleModule.TOOL_LANG))

    api(GradleDependency.TEST_SPRING_BOOT_TEST.withoutVersion)
}
dependencies {
    api(project(GradleModule.TOOL_LANG))

    api(GradleDependency.TRANSACTION_SPRING.withoutVersion)
}
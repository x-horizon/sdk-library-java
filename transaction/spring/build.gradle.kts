dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_LANG)))

    api(GradleDependency.TRANSACTION_SPRING.withoutVersion)
}
dependencies {
    api(project(GradleModule.TOOL_LANG))

    api(GradleDependency.TOOL_VALIDATION_HIBERNATE.withoutVersion)
}
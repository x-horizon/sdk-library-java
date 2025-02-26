dependencies {
    api(project(GradleModule.TOOL_LANG))
    api(project(GradleModule.TOOL_VALIDATION_HIBERNATE))
    api(project(GradleModule.TOOL_VALIDATION_SPRING))

    api(GradleDependency.TOOL_SERIALIZATION_JACKSON_CORE.withoutVersion)
    api(GradleDependency.TOOL_SERIALIZATION_JACKSON_DATABIND.withoutVersion)
    api(GradleDependency.TOOL_SERIALIZATION_JACKSON_ANNOTATION.withoutVersion)
}
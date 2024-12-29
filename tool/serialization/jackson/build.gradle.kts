dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_LANG)))
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_VALIDATION_HIBERNATE)))
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_VALIDATION_SPRING_BOOT)))

    api(GradleDependency.TOOL_SERIALIZATION_JACKSON_CORE.withoutVersion)
    api(GradleDependency.TOOL_SERIALIZATION_JACKSON_DATABIND.withoutVersion)
    api(GradleDependency.TOOL_SERIALIZATION_JACKSON_ANNOTATION.withoutVersion)
}
dependencies {
    api(project(GradleModule.TOOL_LANG))
    api(project(GradleModule.TOOL_SPRING_CONTRACT))

    testImplementation(project(GradleModule.TOOL_CONVERT_API))
    testImplementation(project(GradleModule.TOOL_CONVERT_JACKSON))
    testImplementation(project(GradleModule.TOOL_ENUMS))
    testImplementation(project(GradleModule.TOOL_LOG))
}
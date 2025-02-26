dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_LANG)))
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_SPRING_CONTRACT)))

    testImplementation(project(GradleModule.toReferenceName(GradleModule.TOOL_CONVERT_API)))
    testImplementation(project(GradleModule.toReferenceName(GradleModule.TOOL_CONVERT_JACKSON)))
    testImplementation(project(GradleModule.toReferenceName(GradleModule.TOOL_ENUMS)))
    testImplementation(project(GradleModule.toReferenceName(GradleModule.TOOL_LOG)))
}
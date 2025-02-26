dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_LANG)))

    api(GradleDependency.DOC_SPRING_OPENAPI_COMMON.withoutVersion)
}
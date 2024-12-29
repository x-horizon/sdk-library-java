dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_LANG)))

    api(GradleDependency.TOOL_ALIBABA_EASY_EXCEL.withoutVersion)
}
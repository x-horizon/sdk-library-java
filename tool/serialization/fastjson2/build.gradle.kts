dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_LANG)))

    api(GradleDependency.TOOL_SERIALIZATION_FASTJSON2.withoutVersion)
}
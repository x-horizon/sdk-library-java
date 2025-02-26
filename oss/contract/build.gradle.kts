dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.CONTRACT_COMPONENT_OSS)))
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_SPRING_CONTRACT)))

    api(GradleDependency.OSS_ALL_FACED.withoutVersion)
}
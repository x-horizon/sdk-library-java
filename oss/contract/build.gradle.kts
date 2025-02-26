dependencies {
    api(project(GradleModule.CONTRACT_COMPONENT_OSS))
    api(project(GradleModule.TOOL_SPRING_CONTRACT))

    api(GradleDependency.OSS_ALL_FACED.withoutVersion)
}
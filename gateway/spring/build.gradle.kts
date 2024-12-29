dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_LANG)))

    api(GradleDependency.GATEWAY_SPRING_CLOUD.withoutVersion)
    api(GradleDependency.LOADBALANCER_SPRING_CLOUD.withoutVersion)
}
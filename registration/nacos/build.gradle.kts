dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_LANG)))

    api(GradleDependency.LOADBALANCER_SPRING_CLOUD.withoutVersion)
    api(GradleDependency.REGISTRATION_SPRING_CLOUD_ALIBABA_NACOS_CONFIG.withoutVersion)
    api(GradleDependency.REGISTRATION_SPRING_CLOUD_ALIBABA_NACOS_DISCOVERY.withoutVersion)
}
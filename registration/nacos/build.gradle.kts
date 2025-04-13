dependencies {
    api(project(GradleModule.TOOL_LANG))
    api(project(GradleModule.TOOL_SPRING_CONTRACT))
    api(project(GradleModule.TOOL_CONVERT_API))
    api(project(GradleModule.TOOL_CONVERT_JACKSON))

    api(GradleDependency.LOADBALANCER_SPRING_CLOUD.withoutVersion)
    api(GradleDependency.REGISTRATION_SPRING_CLOUD_ALIBABA_NACOS_CONFIG.withoutVersion)
    api(GradleDependency.REGISTRATION_SPRING_CLOUD_ALIBABA_NACOS_DISCOVERY.withoutVersion)
}
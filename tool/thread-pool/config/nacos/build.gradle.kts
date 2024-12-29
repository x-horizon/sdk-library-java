dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.REGISTRATION_NACOS)))
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_THREAD_POOL_CONTRACT)))

    api(GradleDependency.TOOL_THREAD_POOL_DYNAMIC_TP_CONFIG_NACOS.withoutVersion)
}
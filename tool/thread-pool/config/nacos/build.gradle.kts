dependencies {
    api(project(GradleModule.REGISTRATION_NACOS))
    api(project(GradleModule.TOOL_THREAD_POOL_CONTRACT))

    api(GradleDependency.TOOL_THREAD_POOL_DYNAMIC_TP_CONFIG_NACOS.withoutVersion)
}
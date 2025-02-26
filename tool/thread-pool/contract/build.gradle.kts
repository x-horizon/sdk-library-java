dependencies {
    api(project(GradleModule.TOOL_LANG))
    api(project(GradleModule.TOOL_SPRING_CONTRACT))

    api(GradleDependency.TOOL_THREAD_POOL_DYNAMIC_TP_COMMON.withoutVersion)
//    api(GradleDependency.TOOL_THREAD_POOL_DYNAMIC_TP_CORE.withoutVersion)
}
dependencies {
    api(project(GradleModule.TOOL_THREAD_POOL_CONTRACT))

    api(GradleDependency.TOOL_THREAD_POOL_DYNAMIC_TP_INTEGRATION_GRPC.withoutVersion)
}
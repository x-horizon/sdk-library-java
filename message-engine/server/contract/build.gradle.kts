dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.MESSAGE_ENGINE_CONTRACT)))
    api(GradleDependency.WEB_IO_NETTY_TRANSPORT.withoutVersion)
}
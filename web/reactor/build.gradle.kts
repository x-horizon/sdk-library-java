dependencies {
    api(project(GradleModule.TOOL_LANG))

    api(GradleDependency.WEB_IO_REACTOR.withoutVersion)
}
dependencies {
    api(project(GradleModule.TOOL_LANG))

    api(GradleDependency.PROTOCOL_JAVAX_SIP.withoutVersion)
}
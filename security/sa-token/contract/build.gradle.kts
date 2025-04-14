dependencies {
    api(project(GradleModule.TOOL_LANG))

    api(GradleDependency.SECURITY_SA_TOKEN_CORE.withoutVersion)
    api(GradleDependency.SECURITY_SA_TOKEN_REDISSON.withoutVersion)
    api(GradleDependency.SECURITY_SA_TOKEN_OAUTH2.withoutVersion)
}
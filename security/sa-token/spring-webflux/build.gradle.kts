dependencies {
    api(project(GradleModule.SECURITY_SA_TOKEN_CONTRACT))

    api(GradleDependency.SECURITY_SA_TOKEN_SPRING_BOOT_WEBFLUX_STARTER.withoutVersion)
}
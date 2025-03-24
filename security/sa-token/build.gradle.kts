dependencies {
    api(project(GradleModule.CACHE_LETTUCE))
    api(project(GradleModule.POOL_APACHE))
    api(project(GradleModule.SECURITY_CONTRACT))

    api(GradleDependency.SECURITY_SA_TOKEN_ALONE_REDIS.withoutVersion)
    api(GradleDependency.SECURITY_SA_TOKEN_REDISSON.withoutVersion)
    api(GradleDependency.SECURITY_SA_TOKEN_OAUTH2.withoutVersion)
    api(GradleDependency.SECURITY_SA_TOKEN_SPRING_BOOT_STARTER.withoutVersion)
}
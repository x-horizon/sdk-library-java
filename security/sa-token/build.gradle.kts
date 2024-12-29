dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.CACHE_LETTUCE)))
    api(project(GradleModule.toReferenceName(GradleModule.POOL_APACHE)))
    api(project(GradleModule.toReferenceName(GradleModule.SECURITY_CONTRACT)))

    api(GradleDependency.SECURITY_SA_TOKEN_ALONE_REDIS.withoutVersion)
    api(GradleDependency.SECURITY_SA_TOKEN_DAO_REDIS_JACKSON.withoutVersion)
    api(GradleDependency.SECURITY_SA_TOKEN_OAUTH2.withoutVersion)
    api(GradleDependency.SECURITY_SA_TOKEN_SPRING_BOOT_STARTER.withoutVersion)
}
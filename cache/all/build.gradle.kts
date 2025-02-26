dependencies {
    api(project(GradleModule.CONCURRENT_REDIS))

    api(project(GradleModule.CACHE_CONTRACT))
    api(project(GradleModule.CACHE_MAP))
    api(project(GradleModule.CACHE_CAFFEINE))
    api(project(GradleModule.CACHE_REDIS))
}
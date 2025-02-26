dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.CONCURRENT_REDIS)))

    api(project(GradleModule.toReferenceName(GradleModule.CACHE_CONTRACT)))
    api(project(GradleModule.toReferenceName(GradleModule.CACHE_MAP)))
    api(project(GradleModule.toReferenceName(GradleModule.CACHE_CAFFEINE)))
    api(project(GradleModule.toReferenceName(GradleModule.CACHE_REDIS)))
}
dependencies {
    api(project(GradleModule.CACHE_CONTRACT))

    api(GradleDependency.CACHE_CAFFEINE.withoutVersion)
}
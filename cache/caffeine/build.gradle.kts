dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.CACHE_CONTRACT)))

    api(GradleDependency.CACHE_CAFFEINE.withoutVersion)
}
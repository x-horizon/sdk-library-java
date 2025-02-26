dependencies {
    api(project(GradleModule.SECURITY_CONTRACT))

    api(GradleDependency.SECURITY_SPRING_BOOT.withoutVersion)
}
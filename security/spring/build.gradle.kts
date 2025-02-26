dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.SECURITY_CONTRACT)))

    api(GradleDependency.SECURITY_SPRING_BOOT.withoutVersion)
}
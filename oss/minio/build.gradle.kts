dependencies {
    api(project(GradleModule.OSS_CONTRACT))

    api(GradleDependency.OSS_MINIO.withoutVersion)
}
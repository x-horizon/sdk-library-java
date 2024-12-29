dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.OSS_CONTRACT)))

    api(GradleDependency.OSS_MINIO.withoutVersion)
}
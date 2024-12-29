dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_LANG)))

    api(GradleDependency.CLOUD_NATIVE_KUBERNETES.withoutVersion)
}
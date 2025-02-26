dependencies {
    api(project(GradleModule.TOOL_LANG))

    api(GradleDependency.CLOUD_NATIVE_KUBERNETES.withoutVersion)
}
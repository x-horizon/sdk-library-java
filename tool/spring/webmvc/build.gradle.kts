dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_SPRING_CONTRACT)))

    api(GradleDependency.FRAMEWORK_SPRING_BOOT_WEBMVC.withoutVersion)
}
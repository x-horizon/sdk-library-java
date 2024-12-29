dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_LANG)))

    api(GradleDependency.ORM_SPRING_BOOT_JPA.withoutVersion)
}
dependencies {
    api(project(GradleModule.TOOL_LANG))

    api(GradleDependency.ORM_SPRING_BOOT_JPA.withoutVersion)
}
dependencies {
    api(project(GradleModule.TOOL_CONVERT_JACKSON))
    api(project(GradleModule.TOOL_LANG))
    api(project(GradleModule.TOOL_VALIDATION_HIBERNATE))
    api(project(GradleModule.TOOL_VALIDATION_JAKARTA))

    api(GradleDependency.FRAMEWORK_SPRING_BOOT.withoutVersion)
    api(GradleDependency.TOOL_AOP_SPRING_BOOT.withoutVersion)
    api(GradleDependency.TOOL_AUTOWIRED_SMART_SPRING.withoutVersion)
}
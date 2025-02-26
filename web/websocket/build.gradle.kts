dependencies {
    api(project(GradleModule.TOOL_LANG))
    api(project(GradleModule.TOOL_SPRING_CONTRACT))

    api(GradleDependency.WEB_SPRING_BOOT_WEBSOCKET.withoutVersion)
}
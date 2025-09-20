dependencies {
    api(project(GradleModule.TOOL_LANG))

    api(GradleDependency.JDBC_TDENGINE.withoutVersion) { exclude(group = "commons-logging", module = "commons-logging") }
}
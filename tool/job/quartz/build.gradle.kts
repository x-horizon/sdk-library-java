dependencies {
    api(project(GradleModule.TOOL_LANG))

    api(GradleDependency.TOOL_JOB_QUARTZ.withoutVersion)
}
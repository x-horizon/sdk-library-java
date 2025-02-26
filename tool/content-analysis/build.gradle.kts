dependencies {
    api(project(GradleModule.TOOL_LANG))

    api(GradleDependency.TOOL_CONTENT_ANALYSIS_APACHE_TIKA_CORE.withoutVersion)
    api(GradleDependency.TOOL_CONTENT_ANALYSIS_APACHE_TIKA_PARSER.withoutVersion)
}
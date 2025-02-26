object GradleConfig {

    // internal val activeEnvironment = Environment.LOCALHOST
    internal val activeEnvironment = Environment.DEVELOPMENT
    // internal val activeEnvironment = Environment.PRODUCTION

    const val JAVA_VERSION = "21"

    const val PROJECT_VERSION = "1.0"
    const val PROJECT_CHARSET = "UTF-8"

    const val WITH_PARAMETERS_ARG = "-parameters"
    const val WITH_ENABLE_PREVIEW_ARG = "--enable-preview"
    const val WITH_ENABLE_DYNAMIC_AGENT_LOADING = "-XX:+EnableDynamicAgentLoading"

    const val ACTIVE_ENVIRONMENT_FIELD_NAME = "activeEnvironmentName"
    var activeEnvironmentName = activeEnvironment.handler.getActiveEnvironmentName()

}
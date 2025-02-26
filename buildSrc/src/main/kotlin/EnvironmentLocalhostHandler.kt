object EnvironmentLocalhostHandler : EnvironmentHandler {

    override fun getActiveEnvironmentName(): String {
        return Environment.LOCALHOST.code
    }

    override fun getMavenPersonalUrlEnvironmentName(): String {
        return GradleRepository.MAVEN_PERSONAL_LOCALHOST_URL_ENVIRONMENT_NAME
    }

}
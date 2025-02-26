object EnvironmentDevelopmentHandler : EnvironmentHandler {

    override fun getActiveEnvironmentName(): String {
        return Environment.DEVELOPMENT.code
    }

    override fun getMavenPersonalUrlEnvironmentName(): String {
        return GradleRepository.MAVEN_PERSONAL_DEVELOPMENT_URL_ENVIRONMENT_NAME
    }

}
object EnvironmentDevelopmentHandler : EnvironmentHandler {

    override fun getActiveEnvironmentName(): String {
        return Environment.DEVELOPMENT.code
    }

    override fun getNexusUrl(): String {
        return GradleRepository.NEXUS_DEVELOPMENT_URL
    }

}
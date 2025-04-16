dependencies {
    api(project(GradleModule.TOOL_LANG))
    implementation(project(GradleModule.DOC_KNIFE4J_CONTRACT))
    implementation(GradleDependency.TOOL_SQUAREUP_JAVAPOET.withoutVersion)
    compileOnly(GradleDependency.TOOL_SPI_GOOGLE_AUTO_SERVICE.withoutVersion)
    annotationProcessor(GradleDependency.TOOL_SPI_GOOGLE_AUTO_SERVICE.withoutVersion)
}
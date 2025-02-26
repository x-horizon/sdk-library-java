dependencies {
    api(project(GradleModule.TOOL_LANG))

    api(GradleDependency.TOOL_XML_DOM4J.withoutVersion)
    api(GradleDependency.TOOL_XML_JACKSON.withoutVersion)
    api(GradleDependency.TOOL_XML_JAVAX_JAXB_API.withoutVersion)
}
dependencies {
    api(project(GradleModule.TOOL_LANG))

    api(GradleDependency.TOOL_GEOMETRY_LOCATION_TECH_JTS.withoutVersion)
    api(GradleDependency.TOOL_GEOMETRY_LOCATION_TECH_SPATIAL4J.withoutVersion)
}
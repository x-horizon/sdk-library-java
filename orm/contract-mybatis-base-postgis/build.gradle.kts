dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.JDBC_POSTGIS)))
    api(project(GradleModule.toReferenceName(GradleModule.ORM_CONTRACT_MYBATIS_BASE)))
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_GEOMETRY)))
}
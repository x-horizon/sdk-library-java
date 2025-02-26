dependencies {
    testImplementation(project(GradleModule.JDBC_POSTGRESQL))

    testImplementation(project(GradleModule.DOC_KNIFE4J_SPRING_WEBMVC))

    testImplementation(project(GradleModule.TOOL_ENUMS))
    testImplementation(project(GradleModule.TOOL_GEOMETRY))
    testImplementation(project(GradleModule.TOOL_JDK))
    testImplementation(project(GradleModule.TOOL_LANG))
    testImplementation(project(GradleModule.TOOL_CONVERT_API))
    testImplementation(project(GradleModule.TOOL_CONVERT_JACKSON))
    testImplementation(project(GradleModule.TOOL_CONVERT_MAPSTRUCT_PLUS))
    testImplementation(project(GradleModule.TOOL_SERIALIZATION_JACKSON))
    testImplementation(project(GradleModule.TOOL_SPRING_WEBMVC))
    testImplementation(project(GradleModule.TOOL_VALIDATION_JAKARTA))
    testImplementation(project(GradleModule.TOOL_VALIDATION_SPRING))

    testImplementation(project(GradleModule.ORM_MYBATIS_FLEX_POSTGRESQL))

    annotationProcessor(project(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_LOMBOK_MAPSTRUCT_BINDING))
    annotationProcessor(project(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_MAPSTRUCT_PLUS))
    annotationProcessor(project(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_MYBATIS_FLEX))
    testAnnotationProcessor(project(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_LOMBOK_MAPSTRUCT_BINDING))
    testAnnotationProcessor(project(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_MAPSTRUCT_PLUS))
    testAnnotationProcessor(project(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_MYBATIS_FLEX))
}
dependencies {
    api(project(GradleModule.DOC_KNIFE4J_CONTRACT))
    api(project(GradleModule.POOL_DATABASE_HIKARICP))
    api(project(GradleModule.TOOL_CONVERT_API))
    api(project(GradleModule.TOOL_CONVERT_JACKSON))
    api(project(GradleModule.TOOL_ENUMS))
    api(project(GradleModule.TOOL_LANG))
    api(project(GradleModule.TOOL_SPRING_CONTRACT))

    api(GradleDependency.TOOL_VALIDATION_HIBERNATE.withoutVersion)

    annotationProcessor(project(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_LOMBOK_MAPSTRUCT_BINDING))
    annotationProcessor(project(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_MAPSTRUCT_PLUS))
    testAnnotationProcessor(project(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_LOMBOK_MAPSTRUCT_BINDING))
    testAnnotationProcessor(project(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_MAPSTRUCT_PLUS))
}
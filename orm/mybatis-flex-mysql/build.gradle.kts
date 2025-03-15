dependencies {
    api(project(GradleModule.ORM_CONTRACT_MYBATIS_BASE_MYSQL))
    api(project(GradleModule.ORM_MYBATIS_FLEX_BASE))

    annotationProcessor(project(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_LOMBOK_MAPSTRUCT_BINDING))
    annotationProcessor(project(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_MAPSTRUCT_PLUS))
    annotationProcessor(project(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_MYBATIS_FLEX))
    testAnnotationProcessor(project(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_LOMBOK_MAPSTRUCT_BINDING))
    testAnnotationProcessor(project(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_MAPSTRUCT_PLUS))
    testAnnotationProcessor(project(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_MYBATIS_FLEX))

    testImplementation(project(GradleModule.TOOL_JDK))
}
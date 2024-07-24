// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

dependencies {
    testImplementation(project(GradleModule.toReferenceName(GradleModule.JDBC_POSTGRESQL)))

    testImplementation(project(GradleModule.toReferenceName(GradleModule.DOC_KNIFE4J_SPRING_WEBMVC)))

    testImplementation(project(GradleModule.toReferenceName(GradleModule.TOOL_ENUMS)))
    testImplementation(project(GradleModule.toReferenceName(GradleModule.TOOL_GEOMETRY)))
    testImplementation(project(GradleModule.toReferenceName(GradleModule.TOOL_JDK)))
    testImplementation(project(GradleModule.toReferenceName(GradleModule.TOOL_LANG)))
    testImplementation(project(GradleModule.toReferenceName(GradleModule.TOOL_CONVERT_API)))
    testImplementation(project(GradleModule.toReferenceName(GradleModule.TOOL_CONVERT_JACKSON)))
    testImplementation(project(GradleModule.toReferenceName(GradleModule.TOOL_CONVERT_MAPSTRUCT_PLUS)))
    testImplementation(project(GradleModule.toReferenceName(GradleModule.TOOL_SERIALIZATION_JACKSON)))
    testImplementation(project(GradleModule.toReferenceName(GradleModule.TOOL_SPRING_WEBMVC)))
    testImplementation(project(GradleModule.toReferenceName(GradleModule.TOOL_VALIDATION_JAKARTA)))
    testImplementation(project(GradleModule.toReferenceName(GradleModule.TOOL_VALIDATION_SPRING_BOOT)))

    testImplementation(project(GradleModule.toReferenceName(GradleModule.ORM_MYBATIS_FLEX_POSTGRESQL)))

    annotationProcessor(project(GradleModule.toReferenceName(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_LOMBOK_MAPSTRUCT_BINDING)))
    annotationProcessor(project(GradleModule.toReferenceName(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_MAPSTRUCT_PLUS)))
    annotationProcessor(project(GradleModule.toReferenceName(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_MYBATIS_FLEX)))
    testAnnotationProcessor(project(GradleModule.toReferenceName(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_LOMBOK_MAPSTRUCT_BINDING)))
    testAnnotationProcessor(project(GradleModule.toReferenceName(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_MAPSTRUCT_PLUS)))
    testAnnotationProcessor(project(GradleModule.toReferenceName(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_MYBATIS_FLEX)))
}
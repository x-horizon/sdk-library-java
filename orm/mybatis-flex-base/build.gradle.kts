// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.ORM_CONTRACT_MYBATIS_FLEX)))
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_CONVERT_MAPSTRUCT_PLUS)))
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_ID_SNOWFLAKE)))
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_ID_UUID)))

    annotationProcessor(project(GradleModule.toReferenceName(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_LOMBOK_MAPSTRUCT_BINDING)))
    annotationProcessor(project(GradleModule.toReferenceName(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_MAPSTRUCT_PLUS)))
    annotationProcessor(project(GradleModule.toReferenceName(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_MYBATIS_FLEX)))
    testAnnotationProcessor(project(GradleModule.toReferenceName(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_LOMBOK_MAPSTRUCT_BINDING)))
    testAnnotationProcessor(project(GradleModule.toReferenceName(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_MAPSTRUCT_PLUS)))
    testAnnotationProcessor(project(GradleModule.toReferenceName(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_MYBATIS_FLEX)))
}
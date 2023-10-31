// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

enum class GradleDependency(val withoutVersion: String, val version: String) {

    AOP_SPRING_BOOT("org.springframework.boot:spring-boot-starter-aop", ""),

    BOM_CLOUD_NATIVE_DOCKER("com.github.docker-java:docker-java-bom", "3.3.3"),
    BOM_DOC_KNIFE4J("com.github.xiaoymin:knife4j-dependencies", "4.3.0"),
    BOM_FRAMEWORK_AWAZON_AWS("com.amazonaws:aws-java-sdk-bom", "1.12.568"),
    BOM_FRAMEWORK_AZURE_CLOUD("com.azure.spring:spring-cloud-azure-dependencies", "5.5.0"), // TODO wjm 到 spring-boot 版本 3.1.2，待更新
    BOM_FRAMEWORK_GOOGLE_CLOUD("com.google.cloud:spring-cloud-gcp-dependencies", "4.8.0"),
    BOM_FRAMEWORK_SPRING("org.springframework:spring-framework-bom", "6.0.11"),
    BOM_FRAMEWORK_SPRING_INTEGRATION("org.springframework.integration:spring-integration-bom", "6.1.2"),
    BOM_FRAMEWORK_SPRING_BOOT("org.springframework.boot:spring-boot-dependencies", "3.1.3"),
    BOM_FRAMEWORK_SPRING_CLOUD("org.springframework.cloud:spring-cloud-dependencies", "2022.0.4"),
    BOM_METRIC_MICROMETER("io.micrometer:micrometer-bom", "1.11.5"),
    BOM_METRIC_DROP_WIZARD("io.dropwizard.metrics:metrics-bom", "4.2.19"),
    BOM_ORM_MYBATIS_FLEX("com.mybatis-flex:mybatis-flex-dependencies", "1.7.2"),
    BOM_ORM_MYBATIS_PLUS("com.baomidou:mybatis-plus-bom", "3.5.4"),
    BOM_SECURITY_SA_TOKEN("cn.dev33:sa-token-bom", "1.37.0"),
    BOM_TOOL_CONTENT_ANALYSIS_APACHE_TIKA("org.apache.tika:tika-bom", "2.9.1"),
    BOM_TOOL_SERIALIZATION_JACKSON("com.fasterxml.jackson:jackson-bom", "2.15.2"),
    BOM_TOOL_SERIALIZATION_PROTOBUF_GOOGLE("com.google.protobuf:protobuf-bom", "3.24.3"),
    BOM_TOOL_SERIALIZATION_PROTOBUF_SQUAREUP_WIRE("com.squareup.wire:wire-bom", "4.9.1"),
    BOM_TEST_CONTAINER("org.testcontainers:testcontainers-bom", "1.19.1"),
    BOM_TEST_MOCKITO("org.mockito:mockito-bom", "5.3.1"),
    BOM_WEB_IO_NETTY("io.netty:netty-bom", "4.1.100.Final"),
    BOM_WEB_IO_REACTOR("io.projectreactor:reactor-bom", "2022.0.10"),
    BOM_WEB_OPEN_FEIGN("io.github.openfeign:feign-bom", "12.4"),

    CACHE_CAFFEINE("com.github.ben-manes.caffeine:caffeine", "3.1.8"),
    CACHE_LETTUCE("io.lettuce:lettuce-core", "6.2.6.RELEASE"),
    CACHE_REDISSON("org.redisson:redisson-spring-boot-starter", "3.23.5"),

    CLOUD_COMMUNICATION_JAKARTA_MAIL("com.sun.mail:jakarta.mail", "2.0.1"),
    CLOUD_COMMUNICATION_AWAZON_AWS_JAVA_SDK_SQS("com.amazonaws:aws-java-sdk-sqs", ""),
    CLOUD_COMMUNICATION_AWAZON_AWS_JAVA_SDK_SNS("com.amazonaws:aws-java-sdk-sns", ""),
    CLOUD_COMMUNICATION_DROMARA_SMS("org.dromara.sms4j:sms4j-spring-boot-starter", "3.0.3"),
    CLOUD_COMMUNICATION_TWILIO("com.twilio.sdk:twilio", "9.13.1"),
    CLOUD_NATIVE_DOCKER_CORE("com.github.docker-java:docker-java-core", ""),
    CLOUD_NATIVE_DOCKER_TRANSPORT_OKHTTP("com.github.docker-java:docker-java-transport-okhttp", ""),
    CLOUD_NATIVE_KUBERNETES("org.springframework.cloud:spring-cloud-starter-kubernetes-fabric8-all", ""),

    DATA_CASSANDRA("org.springframework.boot:spring-boot-starter-data-cassandra", ""), // TODO wjm 后续需要转为 spring 相关模块 ("org.apache.cassandra:cassandra-all", "4.1.3")
    DATA_ELASTICSEARCH("org.springframework.boot:spring-boot-starter-data-elasticsearch", ""),
    DATA_HSQLDB("org.hsqldb:hsqldb", "2.7.2"),
    DATA_MYSQL("com.mysql:mysql-connector-j", "8.1.0"),
    DATA_POSTGRESQL("org.postgresql:postgresql", "42.6.0"),

    DOC_KNIFE4J("com.github.xiaoymin:knife4j-openapi3-jakarta-spring-boot-starter", ""),

    FRAMEWORK_SPRING_BOOT("org.springframework.boot:spring-boot-starter", ""),
    FRAMEWORK_SPRING_BOOT_WEBMVC("org.springframework.boot:spring-boot-starter-web", ""),
    FRAMEWORK_SPRING_BOOT_WEBFLUX("org.springframework.boot:spring-boot-starter-webflux", ""),

    GATEWAY_SPRING_CLOUD("org.springframework.cloud:spring-cloud-starter-gateway", ""),

    LOG_SPRING_BOOT("org.springframework.boot:spring-boot-starter-logging", ""),

    MESSAGE_AZURE_CLOUD_SERVICE_BUS("com.azure.spring:spring-cloud-azure-stream-binder-servicebus", ""),
    MESSAGE_COAP_CALIFORNIUM("org.eclipse.californium:californium-core", "3.9.1"),
    MESSAGE_GOOGLE_CLOUD_PUBSUB("com.google.cloud:spring-cloud-gcp-starter-pubsub", ""), // TODO wjm 需移除该依赖，并替换为 spring cloud stream
    MESSAGE_SPRING_INTEGRATION_MQTT("org.springframework.integration:spring-integration-mqtt", ""),
    MESSAGE_SPRING_CLOUD_STREAM("org.springframework.cloud:spring-cloud-stream", ""),
    MESSAGE_SPRING_CLOUD_STREAM_KAFKA("org.springframework.cloud:spring-cloud-starter-stream-kafka", ""),
    MESSAGE_SPRING_CLOUD_STREAM_RABBITMQ("org.springframework.cloud:spring-cloud-starter-stream-rabbit", ""),
    MESSAGE_ROCKETMQ("org.apache.rocketmq:rocketmq-spring-boot-starter", "2.2.3"),

    METRIC_BIT_WALKER_USER_AGENT("eu.bitwalker:UserAgentUtils", "1.21"),
    METRIC_DROP_WIZARD_CORE("io.dropwizard.metrics:metrics-core", ""),
    METRIC_DROP_WIZARD_JMX("io.dropwizard.metrics:metrics-jmx", ""),
    METRIC_DROP_WIZARD_JVM("io.dropwizard.metrics:metrics-jvm", ""),
    METRIC_MICROMETER_PROMETHEUS("io.micrometer:micrometer-registry-prometheus", ""),//    METRIC_MICROMETER_CORE("io.micrometer:micrometer-core", ""),
    METRIC_OSHI_CORE("com.github.oshi:oshi-core", "6.4.6"),
    METRIC_SPRING_BOOT_ACTUATOR("org.springframework.boot:spring-boot-starter-actuator", ""),

    ORM_DATASTAX_OSS_CASSANDRA("", "4.17.0"), // TODO wjm 后续需要转为 spring 相关模块
    ORM_DATASTAX_OSS_CASSANDRA_JAVA_DRIVER_CORE("com.datastax.oss:java-driver-core", ORM_DATASTAX_OSS_CASSANDRA.version),
    ORM_DATASTAX_OSS_CASSANDRA_JAVA_DRIVER_QUERY_BUILDER("com.datastax.oss:java-driver-query-builder", ORM_DATASTAX_OSS_CASSANDRA.version),
    ORM_MYBATIS_FLEX("com.mybatis-flex:mybatis-flex-spring-boot-starter", ""),
    ORM_MYBATIS_PLUS("com.baomidou:mybatis-plus-boot-starter", ""), // TODO wjm 待升级 mybatis-plus-spring-boot3-starter（因为其相关 springboot 版本太高暂不升级）
    ORM_MYBATIS_PLUS_DYNAMIC_DATASOURCE("com.baomidou:dynamic-datasource-spring-boot-starter", "4.2.0"),
    ORM_MYBATIS_PLUS_GENERATOR("com.baomidou:mybatis-plus-generator", ""),
    ORM_SPRING_JDBC("org.springframework:spring-jdbc", ""),
    ORM_SPRING_BOOT_JPA("org.springframework.boot:spring-boot-starter-data-jpa", ""),
    ORM_TD_ENGINE_JDBC("com.taosdata.jdbc:taos-jdbcdriver", "3.2.5"),

    OSS_MINIO("io.minio:minio", "8.5.6"),

    PLUGGABLE_ANNOTATION_PROCESSING_API_LOMBOK("org.projectlombok:lombok", "1.18.28"),
    PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_LOMBOK_MAPSTRUCT_BINDING("org.projectlombok:lombok-mapstruct-binding", "0.2.0"),
    PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_MAPSTRUCT("org.mapstruct:mapstruct-processor", "1.5.5.Final"),
    PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_MICA_AUTO("net.dreamlu:mica-auto", "3.1.2"),
    PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_MYBATIS_FLEX("com.mybatis-flex:mybatis-flex-processor", ""),
    PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_OPENJDK_JMH("org.openjdk.jmh:jmh-generator-annprocess", "1.37"),
    PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_SPRING_AUTOCONFIGURE("org.springframework.boot:spring-boot-autoconfigure-processor", BOM_FRAMEWORK_SPRING_BOOT.version),
    PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_SPRING_CONFIGURATION("org.springframework.boot:spring-boot-configuration-processor", BOM_FRAMEWORK_SPRING_BOOT.version),

    POOL_DATABASE_HIKARICP("com.zaxxer:HikariCP", "5.0.1"),
    POOL_APACHE("org.apache.commons:commons-pool2", "2.12.0"),

    PROTOCOL_JAVAX_SIP("javax.sip:jain-sip-ri", "1.3.0-91"),

    REGISTRATION_SPRING_CLOUD_ZOOKEEPER("org.springframework.cloud:spring-cloud-starter-zookeeper-discovery", ""),

    SECURITY_PASSAY("org.passay:passay", "1.6.4"),
    SECURITY_SPRING_BOOT("org.springframework.boot:spring-boot-starter-security", ""),
    SECURITY_SA_TOKEN_ALONE_REDIS("cn.dev33:sa-token-alone-redis", ""),
    SECURITY_SA_TOKEN_DAO_REDIS_JACKSON("cn.dev33:sa-token-redisson-jackson", ""),
    SECURITY_SA_TOKEN_OAUTH2("cn.dev33:sa-token-oauth2", ""),
    SECURITY_SA_TOKEN_SPRING_BOOT_STARTER("cn.dev33:sa-token-spring-boot3-starter", ""),

    SCRIPT_JAVA_SCRIPT("org.javadelight:delight-nashorn-sandbox", "0.4.2"),

    TRANSACTION_SPRING("org.springframework:spring-tx", ""),

    TOOL_ALIBABA_EASY_EXCEL("com.alibaba:easyexcel", "3.3.2"),
    TOOL_ALIBABA_TRANSMITTABLE_THREAD_LOCAL("com.alibaba:transmittable-thread-local", "2.14.3"),
    TOOL_BURNING_WAVE_CORE("org.burningwave:core", "12.63.1"), // TODO wjm warning: commons-parent-28.pom:1:44: 前言中不允许有内容。
    TOOL_CONTENT_ANALYSIS_APACHE_TIKA_CORE("org.apache.tika:tika-core", ""),
    TOOL_CONTENT_ANALYSIS_APACHE_TIKA_PARSER("org.apache.tika:tika-parsers", BOM_TOOL_CONTENT_ANALYSIS_APACHE_TIKA.version),
    TOOL_EXEC("org.zeroturnaround:zt-exec", "1.12"),
    TOOL_FREEMARKER("org.freemarker:freemarker", "2.3.32"),
    TOOL_GEOMETRY_LOCATION_TECH_JTS("org.locationtech.jts:jts-core", "1.19.0"),
    TOOL_GEOMETRY_LOCATION_TECH_SPATIAL4J("org.locationtech.spatial4j:spatial4j", "0.8"),
    TOOL_GOOGLE_GUAVA("com.google.guava:guava", "32.1.3-jre"),
    TOOL_HUTOOL("cn.hutool:hutool-all", "5.8.22"),
    TOOL_IO_VAVR("io.vavr:vavr", "0.10.4"),
    TOOL_JOB_QUARTZ("org.quartz-scheduler:quartz", "2.3.2"),
    TOOL_MAPSTRUCT("org.mapstruct:mapstruct", PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_MAPSTRUCT.version),
    TOOL_REFLECT_ASM("com.esotericsoftware:reflectasm", "1.11.9"),
    TOOL_RATE_LIMITING_BUCKET4J("com.github.vladimir-bukhtoyarov:bucket4j-core", "7.6.0"),
    TOOL_SERIALIZATION_FASTJSON("com.alibaba:fastjson", "1.2.83"),
    TOOL_SERIALIZATION_FASTJSON2("com.alibaba.fastjson2:fastjson2", "2.0.41"),
    TOOL_SERIALIZATION_FST("de.ruedigermoeller:fst", "3.0.4-jdk17"),
    TOOL_SERIALIZATION_GSON("com.google.code.gson:gson", "2.10.1"),
    TOOL_SERIALIZATION_JACKSON_CORE("com.fasterxml.jackson.core:jackson-core", ""),
    TOOL_SERIALIZATION_JACKSON_DATABIND("com.fasterxml.jackson.core:jackson-databind", ""),
    TOOL_SERIALIZATION_JACKSON_ANNOTATION("com.fasterxml.jackson.core:jackson-annotations", ""),
    TOOL_SERIALIZATION_PROTOBUF_GOOGLE_JAVA("com.google.protobuf:protobuf-java", ""),
    TOOL_SERIALIZATION_PROTOBUF_GOOGLE_JAVA_UTIL("com.google.protobuf:protobuf-java-util", ""),
    TOOL_SERIALIZATION_PROTOBUF_THINGSBOARD_DYNAMIC("org.thingsboard:protobuf-dynamic", "1.0.2TB"),
    TOOL_SERIALIZATION_PROTOBUF_SQUAREUP_WIRE("com.squareup.wire:wire-schema", ""),
    TOOL_VALIDATION_HIBERNATE("org.hibernate.validator:hibernate-validator", "8.0.1.Final"),
    TOOL_VALIDATION_JAKARTA("jakarta.validation:jakarta.validation-api", "3.0.2"),
    TOOL_VALIDATION_JSON_SCHEMA("com.github.java-json-tools:json-schema-validator", "2.2.14"),
    TOOL_VALIDATION_SPRING_BOOT("org.springframework.boot:spring-boot-starter-validation", ""),
    TOOL_XML_DOM4J("org.dom4j:dom4j", "2.1.4"),
    TOOL_XML_JACKSON("com.fasterxml.jackson.dataformat:jackson-dataformat-xml", ""),
    TOOL_XML_JAVAX_JAXB_API("javax.xml.bind:jaxb-api", "2.3.1"),
    TOOL_VJTOOL("com.vip.vjtools:vjkit", "1.0.8"),
    TOOL_YITTER_SNOWFLAKE_ID("com.github.yitter:yitter-idgenerator", "1.0.6"),

    TEST_CASSANDRA_UNIT("org.cassandraunit:cassandra-unit", "4.3.1.0"),
    TEST_CONTAINER("org.testcontainers:testcontainers", ""),
    TEST_DB_UNIT("org.dbunit:dbunit", "2.7.3"),
    TEST_DB_UNIT_SPRING("com.github.springtestdbunit:spring-test-dbunit", "1.3.0"),
    TEST_JUNIT("junit:junit", "4.13.2"),
    TEST_MOCKITO_CORE("org.mockito:mockito-core", ""),
    TEST_OPENJDK_JMH_CORE("org.openjdk.jmh:jmh-core", PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_OPENJDK_JMH.version),
    TEST_SPRING_BOOT_TEST("org.springframework.boot:spring-boot-starter-test", ""),

    WEB_IO_NETTY("io.netty:netty-all", ""),
    WEB_IO_REACTOR("io.projectreactor:reactor-core", ""),
    WEB_OPEN_FEIGN_OKHTTP("io.github.openfeign:feign-okhttp", ""),
    WEB_SPRING_CLOUD_OPEN_FEIGN("org.springframework.cloud:spring-cloud-starter-openfeign", ""),
    WEB_SPRING_BOOT_WEBSOCKET("org.springframework.boot:spring-boot-starter-websocket", ""),

    ;

    companion object {
        @JvmStatic
        fun withVersion(dependency: GradleDependency): String {
            return dependency.withoutVersion + ":" + dependency.version
        }
    }

}
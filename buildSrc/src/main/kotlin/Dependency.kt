// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

enum class GradleDependency(val withoutVersion: String, val version: String) {

    BOM_CLOUD_NATIVE_DOCKER("com.github.docker-java:docker-java-bom", "3.3.6"),
    BOM_DOC_XIAOYMIN_KNIFE4J("com.github.xiaoymin:knife4j-dependencies", "4.5.0"), // TODO wjm support spring-boot version to 3.0.4
    BOM_FRAMEWORK_ALIBABA_SPRING_CLOUD("com.alibaba.cloud:spring-cloud-alibaba-dependencies", "2023.0.1.0"), // TODO wjm support spring-boot version to 3.2.4
    BOM_FRAMEWORK_SPRING("org.springframework:spring-framework-bom", "6.1.8"), // support spring-boot version to 3.3.0
    BOM_FRAMEWORK_SPRING_BOOT("org.springframework.boot:spring-boot-dependencies", "3.3.0"),
    BOM_FRAMEWORK_SPRING_CLOUD("org.springframework.cloud:spring-cloud-dependencies", "2023.0.1"), // TODO wjm support spring-boot version to 3.2.4
    BOM_FRAMEWORK_SPRING_INTEGRATION("org.springframework.integration:spring-integration-bom", "6.3.0"), // support spring-boot version to 3.3.0
    BOM_METRIC_DROPWIZARD("io.dropwizard.metrics:metrics-bom", "4.2.25"), // TODO wjm support spring-boot version to 3.2.4
    BOM_METRIC_MICROMETER("io.micrometer:micrometer-bom", "1.13.0"), // support spring-boot version to 3.3.0
    BOM_ORM_MYBATIS_FLEX("com.mybatis-flex:mybatis-flex-dependencies", "1.8.9"), // support spring-boot version to 3.2.4
    BOM_ORM_MYBATIS_PLUS("com.baomidou:mybatis-plus-bom", "3.5.6"), // TODO wjm support spring-boot version to 3.2.0
    BOM_SECURITY_DEV33_SA_TOKEN("cn.dev33:sa-token-bom", "1.38.0"), // TODO wjm support spring-boot version to 2.5.15
    BOM_TEST_MOCKITO("org.mockito:mockito-bom", "5.11.0"),
    BOM_TEST_TESTCONTAINERS("org.testcontainers:testcontainers-bom", "1.19.8"), // TODO wjm support spring-boot version to 3.2.4
    BOM_TOOL_APACHE_TIKA("org.apache.tika:tika-bom", "2.9.2"),
    BOM_TOOL_MAPSTRUCT_PLUS("io.github.linpeilie:mapstruct-plus-pom", "1.4.0"), // TODO wjm support spring-boot version to 2.7.9
    BOM_TOOL_SERIALIZATION_FASTERXML_JACKSON("com.fasterxml.jackson:jackson-bom", "2.17.1"), // support spring-boot version to 3.3.0
    BOM_TOOL_SERIALIZATION_GOOGLE_PROTOBUF("com.google.protobuf:protobuf-bom", "4.26.1"),
    BOM_TOOL_SERIALIZATION_SQUAREUP_WIRE("com.squareup.wire:wire-bom", "4.9.9"),
    BOM_WEB_FEIGN("io.github.openfeign:feign-bom", "13.2.1"), // need to synchronize with "org.springframework.cloud:spring-cloud-starter-openfeign"
    BOM_WEB_NETTY("io.netty:netty-bom", "4.1.110.Final"), // support spring-boot version to 3.3.0
    BOM_WEB_REACTOR("io.projectreactor:reactor-bom", "2023.0.6"), // support spring-boot version to 3.3.0

    CACHE_CAFFEINE("com.github.ben-manes.caffeine:caffeine", "3.1.8"), // support spring-boot version to 3.3.0
    CACHE_LETTUCE("io.lettuce:lettuce-core", "6.3.2.RELEASE"), // support spring-boot version to 3.3.0
    CACHE_REDISSON("org.redisson:redisson-spring-boot-starter", "3.30.0"), // TODO wjm support spring-boot version to 3.2.2

    CLOUD_COMMUNICATION_JAKARTA_MAIL("com.sun.mail:jakarta.mail", "2.0.1"),
    CLOUD_COMMUNICATION_DROMARA_SMS("org.dromara.sms4j:sms4j-spring-boot-starter", "3.2.1"), // TODO wjm support spring-boot version to 2.7.18
    CLOUD_NATIVE_DOCKER_CORE("com.github.docker-java:docker-java-core", ""),
    CLOUD_NATIVE_DOCKER_TRANSPORT_OKHTTP("com.github.docker-java:docker-java-transport-okhttp", ""),
    CLOUD_NATIVE_KUBERNETES("org.springframework.cloud:spring-cloud-starter-kubernetes-fabric8-all", ""),

    DATA_CASSANDRA("org.springframework.boot:spring-boot-starter-data-cassandra", ""),
    DATA_ELASTICSEARCH("org.springframework.boot:spring-boot-starter-data-elasticsearch", ""),
    DATA_HSQLDB("org.hsqldb:hsqldb", "2.7.2"), // support spring-boot version to 3.3.0
    DATA_MYSQL("com.mysql:mysql-connector-j", "8.3.0"), // support spring-boot version to 3.3.0
    DATA_POSTGRESQL("org.postgresql:postgresql", "42.7.3"), // support spring-boot version to 3.3.0

    DOC_XIAOYMIN_KNIFE4J_OPENAPI3_JAKARTA_SPRING_BOOT("com.github.xiaoymin:knife4j-openapi3-jakarta-spring-boot-starter", ""),

    FRAMEWORK_SPRING_BOOT("org.springframework.boot:spring-boot-starter", ""),
    FRAMEWORK_SPRING_BOOT_WEBMVC("org.springframework.boot:spring-boot-starter-web", ""),
    FRAMEWORK_SPRING_BOOT_WEBFLUX("org.springframework.boot:spring-boot-starter-webflux", ""),

    GATEWAY_SPRING_CLOUD("org.springframework.cloud:spring-cloud-starter-gateway", ""),

    LOG_SPRING_BOOT("org.springframework.boot:spring-boot-starter-logging", ""),

    MESSAGE_SPRING_CLOUD_STREAM("org.springframework.cloud:spring-cloud-stream", ""),
    MESSAGE_SPRING_CLOUD_STREAM_KAFKA("org.springframework.cloud:spring-cloud-starter-stream-kafka", ""),
    MESSAGE_SPRING_CLOUD_STREAM_RABBITMQ("org.springframework.cloud:spring-cloud-starter-stream-rabbit", ""),
    MESSAGE_SPRING_INTEGRATION_STREAM("org.springframework.integration:spring-integration-stream", ""),
    MESSAGE_SPRING_INTEGRATION_MQTT("org.springframework.integration:spring-integration-mqtt", ""),
    MESSAGE_ROCKETMQ("com.alibaba.cloud:spring-cloud-starter-stream-rocketmq", ""),
    MESSAGE_ROCKETMQ_TODO("org.apache.rocketmq:rocketmq-spring-boot-starter", "2.2.3"), // TODO wjm need to remove

    METRIC_BIT_WALKER_USER_AGENT("eu.bitwalker:UserAgentUtils", "1.21"),
    METRIC_DROPWIZARD_CORE("io.dropwizard.metrics:metrics-core", ""),
    METRIC_DROPWIZARD_JMX("io.dropwizard.metrics:metrics-jmx", ""),
    METRIC_DROPWIZARD_JVM("io.dropwizard.metrics:metrics-jvm", ""),
    METRIC_MICROMETER_PROMETHEUS("io.micrometer:micrometer-registry-prometheus", ""),
    METRIC_OSHI_CORE("com.github.oshi:oshi-core", "6.4.6"),
    METRIC_SPRING_BOOT_ACTUATOR("org.springframework.boot:spring-boot-starter-actuator", ""),

    ORM_MYBATIS("org.mybatis.spring.boot:mybatis-spring-boot-starter", "3.0.3"), // need to synchronize with "com.mybatis-flex:mybatis-flex-dependencies"
    ORM_MYBATIS_FLEX("com.mybatis-flex:mybatis-flex-spring-boot-starter", ""),
    ORM_MYBATIS_FLEX_ANNOTATION("com.mybatis-flex:mybatis-flex-annotation", ""),
    ORM_MYBATIS_PLUS("com.baomidou:mybatis-plus-spring-boot3-starter", ""),
    ORM_MYBATIS_PLUS_DYNAMIC_DATASOURCE("com.baomidou:dynamic-datasource-spring-boot3-starter", "4.3.0"),  // TODO wjm support spring-boot version to 2.7.16, using bom
    ORM_SPRING_BOOT_JPA("org.springframework.boot:spring-boot-starter-data-jpa", ""),
    ORM_SPRING_JDBC("org.springframework:spring-jdbc", ""),
    ORM_SPRING_INTEGRATION_CASSANDRA("org.springframework.integration:spring-integration-cassandra", ""),
    ORM_TD_ENGINE_JDBC("com.taosdata.jdbc:taos-jdbcdriver", "3.2.5"), // TODO wjm org.springframework.integration

    OSS_MINIO("io.minio:minio", "8.5.9"),

    PLUGGABLE_ANNOTATION_PROCESSING_API_LOMBOK("org.projectlombok:lombok", "1.18.32"), // support spring-boot version to 3.3.0
    PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_LOMBOK_MAPSTRUCT_BINDING("org.projectlombok:lombok-mapstruct-binding", "0.2.0"),
    PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_MAPSTRUCT_PLUS("io.github.linpeilie:mapstruct-plus-processor", BOM_TOOL_MAPSTRUCT_PLUS.version),
    PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_MICA_AUTO("net.dreamlu:mica-auto", "3.1.2"),
    PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_MYBATIS_FLEX("com.mybatis-flex:mybatis-flex-processor", ""),
    PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_OPENJDK_JMH("org.openjdk.jmh:jmh-generator-annprocess", "1.37"),
    PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_SPRING_AUTOCONFIGURE("org.springframework.boot:spring-boot-autoconfigure-processor", BOM_FRAMEWORK_SPRING_BOOT.version),
    PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_SPRING_CONFIGURATION("org.springframework.boot:spring-boot-configuration-processor", BOM_FRAMEWORK_SPRING_BOOT.version),

    POOL_DATABASE_HIKARICP("com.zaxxer:HikariCP", "5.1.0"), // support spring-boot version to 3.3.0
    POOL_APACHE("org.apache.commons:commons-pool2", "2.12.0"), // support spring-boot version to 3.3.0

    PROTOCOL_JAVAX_SIP("javax.sip:jain-sip-ri", "1.3.0-91"), // TODO wjm org.springframework.integration

    REGISTRATION_SPRING_CLOUD_ZOOKEEPER("org.springframework.cloud:spring-cloud-starter-zookeeper-discovery", ""),

    SECURITY_PASSAY("org.passay:passay", "1.6.4"),
    SECURITY_SPRING_BOOT("org.springframework.boot:spring-boot-starter-security", ""),
    SECURITY_SA_TOKEN_ALONE_REDIS("cn.dev33:sa-token-alone-redis", ""),
    SECURITY_SA_TOKEN_DAO_REDIS_JACKSON("cn.dev33:sa-token-redisson-jackson", ""),
    SECURITY_SA_TOKEN_OAUTH2("cn.dev33:sa-token-oauth2", ""),
    SECURITY_SA_TOKEN_SPRING_BOOT_STARTER("cn.dev33:sa-token-spring-boot3-starter", ""),

    TRANSACTION_SPRING("org.springframework:spring-tx", ""),

    TOOL_ALIBABA_EASY_EXCEL("com.alibaba:easyexcel", "3.3.2"),
    TOOL_ALIBABA_TRANSMITTABLE_THREAD_LOCAL("com.alibaba:transmittable-thread-local", "2.14.5"),
    TOOL_AOP_SPRING_BOOT("org.springframework.boot:spring-boot-starter-aop", ""),
    TOOL_APACHE_FILE_ANALYSIS_TIKA_CORE("org.apache.tika:tika-core", ""),
    TOOL_APACHE_FILE_ANALYSIS_TIKA_PARSER("org.apache.tika:tika-parsers", BOM_TOOL_APACHE_TIKA.version),
    TOOL_APACHE_TEMPLATE_ENGINE_VELOCITY("org.apache.velocity:velocity", "1.7"), // TODO wjm 该包 1.7 版本后破坏性更新极大，暂使用旧版本，新版本为：TOOL_APACHE_TEMPLATE_ENGINE_VELOCITY("org.apache.velocity:velocity-engine-core", "2.3")，升级链接：https://velocity.apache.org/engine/2.3/upgrading.html#upgrading-from-velocity-17-to-velocity-20，目前暂使用旧版本
    TOOL_AUTOWIRED_SMART_SPRING("io.github.burukeyou:spring-smart-di-all", "0.2.0"),
    TOOL_GEOMETRY_LOCATION_TECH_JTS("org.locationtech.jts:jts-core", "1.19.0"),
    TOOL_GEOMETRY_LOCATION_TECH_SPATIAL4J("org.locationtech.spatial4j:spatial4j", "0.8"),
    TOOL_GOOGLE_GUAVA("com.google.guava:guava", "32.1.2-jre"), // support spring-boot version to 3.3.0
    TOOL_HUTOOL("cn.hutool:hutool-all", "5.8.26"),
    TOOL_IO_VAVR("io.vavr:vavr", "0.10.4"),
    TOOL_JDK_BURNING_WAVE_CORE("org.burningwave:core", "12.64.3"),
    TOOL_JNA_VERSION("", "5.13.0"),
    TOOL_JNA("net.java.dev.jna:jna", TOOL_JNA_VERSION.version),
    TOOL_JNA_JPMS("net.java.dev.jna:jna-jpms", TOOL_JNA_VERSION.version),
    TOOL_JOB_QUARTZ("org.quartz-scheduler:quartz", "2.3.2"),
    TOOL_MAPSTRUCT_PLUS("io.github.linpeilie:mapstruct-plus-spring-boot-starter", ""),
    TOOL_REFLECT_ASM("com.esotericsoftware:reflectasm", "1.11.9"),
    TOOL_RATE_LIMITING_BUCKET4J("com.github.vladimir-bukhtoyarov:bucket4j-core", "7.6.0"),
    TOOL_SERIALIZATION_FASTJSON("com.alibaba:fastjson", "1.2.83"),
    TOOL_SERIALIZATION_FASTJSON2("com.alibaba.fastjson2:fastjson2", "2.0.48"),
    TOOL_SERIALIZATION_FST("de.ruedigermoeller:fst", "3.0.4-jdk17"),
    TOOL_SERIALIZATION_JACKSON_CORE("com.fasterxml.jackson.core:jackson-core", ""),
    TOOL_SERIALIZATION_JACKSON_DATABIND("com.fasterxml.jackson.core:jackson-databind", ""),
    TOOL_SERIALIZATION_JACKSON_ANNOTATION("com.fasterxml.jackson.core:jackson-annotations", ""),
    TOOL_SERIALIZATION_PROTOBUF_GOOGLE_JAVA("com.google.protobuf:protobuf-java", ""),
    TOOL_SERIALIZATION_PROTOBUF_GOOGLE_JAVA_UTIL("com.google.protobuf:protobuf-java-util", ""),
    TOOL_SERIALIZATION_PROTOBUF_SQUAREUP_WIRE("com.squareup.wire:wire-schema", ""),
    TOOL_STREAM_JDFRAME("io.github.burukeyou:jdframe", "0.0.2"),
    TOOL_VALIDATION_HIBERNATE("org.hibernate.validator:hibernate-validator", "8.0.1.Final"),
    TOOL_VALIDATION_JAKARTA("jakarta.validation:jakarta.validation-api", "3.0.2"), // support spring-boot version to 3.3.0
    TOOL_VALIDATION_JSON_SCHEMA("com.github.java-json-tools:json-schema-validator", "2.2.14"),
    TOOL_VALIDATION_SPRING_BOOT("org.springframework.boot:spring-boot-starter-validation", ""),
    TOOL_VJTOOL("com.vip.vjtools:vjkit", "1.0.8"),
    TOOL_XML_DOM4J("org.dom4j:dom4j", "2.1.4"),
    TOOL_XML_JACKSON("com.fasterxml.jackson.dataformat:jackson-dataformat-xml", ""),
    TOOL_XML_JAVAX_JAXB_API("javax.xml.bind:jaxb-api", "2.3.1"),
    TOOL_YITTER_SNOWFLAKE_ID("com.github.yitter:yitter-idgenerator", "1.0.6"),

    TEST_CONTAINER("org.testcontainers:testcontainers", ""),
    TEST_JUNIT("org.junit.jupiter:junit-jupiter-api", "5.10.1"),
    TEST_MOCKITO_CORE("org.mockito:mockito-core", ""),
    TEST_OPENJDK_JMH_CORE("org.openjdk.jmh:jmh-core", PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_OPENJDK_JMH.version),
    TEST_SPRING_BOOT_TEST("org.springframework.boot:spring-boot-starter-test", ""),
    TEST_SPRING_INTEGRATION_TEST("org.springframework.integration:spring-integration-test", ""),

    WEB_OKHTTPS("cn.zhxu:okhttps-jackson", "4.0.2"),
    WEB_IO_NETTY("io.netty:netty-all", ""), // TODO wjm org.springframework.integration
    WEB_IO_REACTOR("io.projectreactor:reactor-core", ""),
    WEB_OPEN_FEIGN_OKHTTP("io.github.openfeign:feign-okhttp", ""), // TODO wjm org.springframework.integration
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
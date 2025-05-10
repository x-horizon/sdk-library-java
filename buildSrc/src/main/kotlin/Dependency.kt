enum class GradleDependency(val withoutVersion: String, val version: String) {

    BOM_CACHE_REDISSON("org.redisson:redisson-parent", "3.45.1"), // TODO wjm support spring-boot version to 3.4.2
    BOM_CLOUD_COMMUNICATION_DROMARA_SMS4J("", "3.3.4"), // TODO wjm support spring-boot version to 2.7.18
    BOM_CLOUD_NATIVE_DOCKER("com.github.docker-java:docker-java-bom", "3.4.0"),
    BOM_DOC_SPRING_OPENAPI("org.springdoc:springdoc-openapi", "2.8.5"), // TODO wjm support spring-boot version to 3.4.2
    BOM_DOC_XIAOYMIN_KNIFE4J("com.github.xiaoymin:knife4j-dependencies", "4.5.0"), // TODO wjm support spring-boot version to 3.0.4
    BOM_ECLIPSE_PAHO("org.eclipse.paho:java-parent", "1.2.5"), // support spring-boot version to 3.4.5, need to synchronize with "spring-integration-mqtt"
    BOM_FRAMEWORK_ALIBABA_SPRING_CLOUD("com.alibaba.cloud:spring-cloud-alibaba-dependencies", "2023.0.3.2"), // TODO wjm support spring-boot version to 3.2.9
    BOM_FRAMEWORK_SPRING("org.springframework:spring-framework-bom", "6.2.6"), // support spring-boot version to 3.4.5
    BOM_FRAMEWORK_SPRING_BOOT("org.springframework.boot:spring-boot-dependencies", "3.4.5"),
    BOM_FRAMEWORK_SPRING_CLOUD("org.springframework.cloud:spring-cloud-dependencies", "2024.0.1"), // TODO wjm support spring-boot version to 3.4.3
    BOM_FRAMEWORK_SPRING_INTEGRATION("org.springframework.integration:spring-integration-bom", "6.4.4"), // support spring-boot version to 3.4.5
    BOM_JDBC_POSTGIS("net.postgis:postgis-java-aggregator", "2024.1.0"),
    BOM_METRIC_DROPWIZARD("io.dropwizard.metrics:metrics-bom", "4.2.28"), // support spring-boot version to 3.4.5
    BOM_METRIC_MICROMETER("io.micrometer:micrometer-bom", "1.14.6"), // support spring-boot version to 3.4.5
    BOM_ORM_MYBATIS_FLEX("com.mybatis-flex:mybatis-flex-dependencies", "1.10.9"), // TODO wjm support spring-boot version to 2.7.11
    BOM_ORM_MYBATIS_PLUS("com.baomidou:mybatis-plus-bom", "3.5.10.1"), // TODO wjm support spring-boot version to 3.4.1
    BOM_OSS_ALL_FACED("org.dromara.x-file-storage:x-file-storage-parent", "2.2.1"), // TODO wjm support spring-boot version to 2.7.2
    BOM_SECURITY_DEV33_SA_TOKEN("cn.dev33:sa-token-bom", "1.42.0"), // TODO wjm support spring-boot version to 3.0.1
    BOM_TEST_MOCKITO("org.mockito:mockito-bom", "5.14.2"), // support spring-boot version to 3.4.5
    BOM_TEST_TESTCONTAINERS("org.testcontainers:testcontainers-bom", "1.20.6"),
    BOM_TOOL_CONTENT_ANALYSIS_APACHE_TIKA("org.apache.tika:tika-bom", "2.4.1"), // need to synchronize with "org.dromara.x-file-storage:x-file-storage-parent"
    BOM_TOOL_DYNAMIC_TP("org.dromara.dynamictp:dynamic-tp-dependencies", "1.2.0"), // TODO wjm support spring-boot version to 2.7.18、support spring-cloud-alibaba version to 2021.0.5.0
    BOM_TOOL_GEOMETRY_JTS("org.locationtech.jts:jts", "1.20.0"),
    BOM_TOOL_MAPSTRUCT_PLUS("io.github.linpeilie:mapstruct-plus-pom", "1.4.8"), // TODO wjm support spring-boot version to 2.7.18
    BOM_TOOL_MAPSTRUCT_PROTOBUF("no.entur.mapstruct.spi:spi-protobuf-parent", "1.44"), // TODO wjm support com.google.protobuf:protobuf-bom version to 3.23.0
    BOM_TOOL_SERIALIZATION_FASTERXML_JACKSON("com.fasterxml.jackson:jackson-bom", "2.18.3"), // support spring-boot version to 3.4.5
    BOM_TOOL_SERIALIZATION_GOOGLE_PROTOBUF("com.google.protobuf:protobuf-bom", "3.25.1"), // need to synchronize with "io.grpc:grpc-bom", "net.devh:grpc-spring-boot-starter"
    BOM_TOOL_SERIALIZATION_SQUAREUP_WIRE("com.squareup.wire:wire-bom", "5.1.0"),
    BOM_WEB_FEIGN("io.github.openfeign:feign-bom", "13.5"), // support spring-cloud version to 2024.0.1
    BOM_WEB_GRPC("io.grpc:grpc-bom", "1.63.0"), // need to synchronize with "net.devh:grpc-spring-boot-starter"
    BOM_WEB_NETTY("io.netty:netty-bom", "4.1.119.Final"), // support spring-boot version to 3.4.5
    BOM_WEB_REACTOR("io.projectreactor:reactor-bom", "2024.0.5"), // support spring-boot version to 3.4.5

    CACHE_CAFFEINE("com.github.ben-manes.caffeine:caffeine", "3.1.8"), // support spring-boot version to 3.4.5
    CACHE_LETTUCE("io.lettuce:lettuce-core", "6.4.2.RELEASE"), // support spring-boot version to 3.4.5
    CACHE_REDISSON("org.redisson:redisson-spring-boot-starter", BOM_CACHE_REDISSON.version), // TODO wjm bom is not invalid

    CLOUD_COMMUNICATION_DROMARA_EMAIL("org.dromara.sms4j:sms4j-email-jakarta-core", BOM_CLOUD_COMMUNICATION_DROMARA_SMS4J.version),
    CLOUD_COMMUNICATION_DROMARA_SMS("org.dromara.sms4j:sms4j-spring-boot-starter", BOM_CLOUD_COMMUNICATION_DROMARA_SMS4J.version),
    CLOUD_NATIVE_DOCKER_CORE("com.github.docker-java:docker-java-core", ""),
    CLOUD_NATIVE_DOCKER_TRANSPORT_OKHTTP("com.github.docker-java:docker-java-transport-okhttp", ""),
    CLOUD_NATIVE_KUBERNETES("org.springframework.cloud:spring-cloud-starter-kubernetes-fabric8-all", ""),

    DOC_SPRING_OPENAPI_COMMON("org.springdoc:springdoc-openapi-starter-common", ""),
    DOC_XIAOYMIN_KNIFE4J_OPENAPI3_JAKARTA_SPRING_WEBFLUX("com.github.xiaoymin:knife4j-openapi3-webflux-jakarta-spring-boot-starter", ""),
    DOC_XIAOYMIN_KNIFE4J_OPENAPI3_JAKARTA_SPRING_WEBMVC("com.github.xiaoymin:knife4j-openapi3-jakarta-spring-boot-starter", ""),
    DOC_XIAOYMIN_KNIFE4J_SPRING_GATEWAY("com.github.xiaoymin:knife4j-gateway-spring-boot-starter", ""),

    FRAMEWORK_SPRING_BOOT("org.springframework.boot:spring-boot-starter", ""),
    FRAMEWORK_SPRING_BOOT_WEBFLUX("org.springframework.boot:spring-boot-starter-webflux", ""),
    FRAMEWORK_SPRING_BOOT_WEBMVC("org.springframework.boot:spring-boot-starter-web", ""),

    GATEWAY_SPRING_CLOUD("org.springframework.cloud:spring-cloud-starter-gateway", ""),

    JDBC_CASSANDRA("org.springframework.boot:spring-boot-starter-data-cassandra", ""),
    JDBC_DA_MENG("com.dameng:DmJdbcDriver18", "8.1.3.140"),
    JDBC_ELASTICSEARCH("org.springframework.boot:spring-boot-starter-data-elasticsearch", ""),
    JDBC_HSQLDB("org.hsqldb:hsqldb", "2.7.3"), // support spring-boot version to 3.4.5
    JDBC_MARIADB("org.mariadb.jdbc:mariadb-java-client", "3.5.2"),
    JDBC_MYSQL("com.mysql:mysql-connector-j", "9.1.0"), // support spring-boot version to 3.4.5
    JDBC_POSTGIS("net.postgis:postgis-jdbc-jts", BOM_JDBC_POSTGIS.version), // TODO wjm bom is not invalid
    JDBC_POSTGRESQL("org.postgresql:postgresql", "42.7.5"), // support spring-boot version to 3.4.5
    JDBC_SQL_SERVER("com.microsoft.sqlserver:mssql-jdbc", "12.8.1.jre11"),
    JDBC_TDENGINE("com.taosdata.jdbc:taos-jdbcdriver", "3.5.1"),

    LOADBALANCER_SPRING_CLOUD("org.springframework.cloud:spring-cloud-loadbalancer", ""),
    LOG_SPRING_BOOT("org.springframework.boot:spring-boot-starter-logging", ""),

    MESSAGE_KAFKA_SPRING_INTEGRATION("org.springframework.integration:spring-integration-kafka", ""),
    MESSAGE_MQTT_SPRING_INTEGRATION("org.springframework.integration:spring-integration-mqtt", ""),
    MESSAGE_MQTT_V5_ECLIPSE_PAHO("org.eclipse.paho:org.eclipse.paho.mqttv5.client", BOM_ECLIPSE_PAHO.version), // TODO wjm bom is not invalid
    MESSAGE_RABBITMQ_SPRING_CLOUD_STREAM("org.springframework.cloud:spring-cloud-starter-stream-rabbit", ""),
    MESSAGE_ROCKETMQ_SPRING_CLOUD_STREAM("com.alibaba.cloud:spring-cloud-starter-stream-rocketmq", ""),
    MESSAGE_ROCKETMQ_TODO("org.apache.rocketmq:rocketmq-spring-boot-starter", "2.2.3"), // TODO wjm need to remove
    MESSAGE_SPRING_INTEGRATION_STREAM("org.springframework.integration:spring-integration-stream", ""),

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
    ORM_MYBATIS_PLUS_DYNAMIC_DATASOURCE("com.baomidou:dynamic-datasource-spring-boot3-starter", "4.3.1"),  // TODO wjm support spring-boot version to 2.7.18, using bom
    ORM_SPRING_BOOT_JPA("org.springframework.boot:spring-boot-starter-data-jpa", ""),
    ORM_SPRING_JDBC("org.springframework:spring-jdbc", ""),
    ORM_SPRING_INTEGRATION_CASSANDRA("org.springframework.integration:spring-integration-cassandra", ""),

    OSS_ALL_FACED("org.dromara.x-file-storage:x-file-storage-spring", BOM_OSS_ALL_FACED.version), // TODO wjm bom is not invalid
    OSS_MINIO("io.minio:minio", "8.5.2"), // need to synchronize with "org.dromara.x-file-storage:x-file-storage-parent"

    PLUGGABLE_ANNOTATION_PROCESSING_API_LOMBOK("org.projectlombok:lombok", "1.18.38"), // support spring-boot version to 3.4.5
    PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_LOMBOK_MAPSTRUCT_BINDING("org.projectlombok:lombok-mapstruct-binding", "0.2.0"),
    PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_MAPSTRUCT_PLUS("io.github.linpeilie:mapstruct-plus-processor", BOM_TOOL_MAPSTRUCT_PLUS.version),
    PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_MAPSTRUCT_PROTOBUF("no.entur.mapstruct.spi:protobuf-spi-impl", BOM_TOOL_MAPSTRUCT_PROTOBUF.version),
    PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_MICA_AUTO("net.dreamlu:mica-auto", "3.1.2"),
    PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_MYBATIS_FLEX("com.mybatis-flex:mybatis-flex-processor", ""),
    PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_OPENJDK_JMH("org.openjdk.jmh:jmh-generator-annprocess", "1.37"),
    PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_SPRING_AUTOCONFIGURE("org.springframework.boot:spring-boot-autoconfigure-processor", BOM_FRAMEWORK_SPRING_BOOT.version),
    PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_SPRING_CONFIGURATION("org.springframework.boot:spring-boot-configuration-processor", BOM_FRAMEWORK_SPRING_BOOT.version),

    POOL_DATABASE_HIKARICP("com.zaxxer:HikariCP", "5.1.0"), // support spring-boot version to 3.4.5
    POOL_APACHE("org.apache.commons:commons-pool2", "2.12.1"), // support spring-boot version to 3.4.5

    PROTOCOL_JAVAX_SIP("javax.sip:jain-sip-ri", "1.3.0-91"),

    REGISTRATION_SPRING_CLOUD_ALIBABA_NACOS_CONFIG("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config", ""),
    REGISTRATION_SPRING_CLOUD_ALIBABA_NACOS_DISCOVERY("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery", ""),
    REGISTRATION_SPRING_CLOUD_ZOOKEEPER("org.springframework.cloud:spring-cloud-starter-zookeeper-discovery", ""),

    SECURITY_SPRING_BOOT("org.springframework.boot:spring-boot-starter-security", ""),
    SECURITY_SA_TOKEN_CORE("cn.dev33:sa-token-core", ""),
    SECURITY_SA_TOKEN_REDISSON("cn.dev33:sa-token-redisson-spring-boot-starter", ""),
    SECURITY_SA_TOKEN_OAUTH2("cn.dev33:sa-token-oauth2", ""),
    SECURITY_SA_TOKEN_SPRING_BOOT_WEBMVC_STARTER("cn.dev33:sa-token-spring-boot3-starter", ""),
    SECURITY_SA_TOKEN_SPRING_BOOT_WEBFLUX_STARTER("cn.dev33:sa-token-reactor-spring-boot3-starter", ""),

    TRANSACTION_SPRING("org.springframework:spring-tx", ""),

    TOOL_ALIBABA_EASY_EXCEL("com.alibaba:easyexcel", "4.0.3"),
    TOOL_ALIBABA_TRANSMITTABLE_THREAD_LOCAL("com.alibaba:transmittable-thread-local", "2.14.5"),
    TOOL_ANNOTATION_API_JAVAX("javax.annotation:javax.annotation-api", "1.3.2"),
    TOOL_AOP_SPRING_BOOT("org.springframework.boot:spring-boot-starter-aop", ""),
    TOOL_CONTENT_ANALYSIS_APACHE_TIKA_CORE("org.apache.tika:tika-core", ""),
    TOOL_CONTENT_ANALYSIS_APACHE_TIKA_PARSER("org.apache.tika:tika-parsers", BOM_TOOL_CONTENT_ANALYSIS_APACHE_TIKA.version), // TODO wjm bom is not invalid
    TOOL_AUTOWIRED_SMART_SPRING("io.github.burukeyou:spring-smart-di-all", "0.2.0"),
    TOOL_GEOMETRY_LOCATION_TECH_JTS("org.locationtech.jts:jts-core", BOM_TOOL_GEOMETRY_JTS.version), // need to synchronize with "net.postgis:postgis-jdbc-jts" TODO wjm bom is not invalid
    TOOL_GEOMETRY_LOCATION_TECH_SPATIAL4J("org.locationtech.spatial4j:spatial4j", "0.8"), // need to synchronize with "net.postgis:postgis-jdbc-jts"
    TOOL_GOOGLE_GUAVA("com.google.guava:guava", "33.3.0-jre"),
    TOOL_HUTOOL("org.dromara.hutool:hutool-all", "6.0.0-M19"),
    TOOL_IO_VAVR("io.vavr:vavr", "0.10.4"),
    TOOL_JDK_BURNING_WAVE_CORE("org.burningwave:core", "12.66.2"),
    TOOL_JNA_VERSION("", "5.13.0"),
    TOOL_JNA("net.java.dev.jna:jna", TOOL_JNA_VERSION.version),
    TOOL_JNA_JPMS("net.java.dev.jna:jna-jpms", TOOL_JNA_VERSION.version),
    TOOL_JOB_QUARTZ("org.quartz-scheduler:quartz", "2.3.2"),
    TOOL_MAPSTRUCT_PLUS("io.github.linpeilie:mapstruct-plus-spring-boot-starter", ""),
    TOOL_PASSAY("org.passay:passay", "1.6.5"),
    TOOL_REFLECT_ASM("com.esotericsoftware:reflectasm", "1.11.9"),
    TOOL_REFLECT_RONMAMO("org.reflections:reflections", "0.10.2"),
    TOOL_RATE_LIMITING_BUCKET4J("com.github.vladimir-bukhtoyarov:bucket4j-core", "7.6.0"),
    TOOL_SERIALIZATION_FASTJSON("com.alibaba:fastjson", "1.2.83"),
    TOOL_SERIALIZATION_FASTJSON2("com.alibaba.fastjson2:fastjson2", "2.0.52"),
    TOOL_SERIALIZATION_FST("de.ruedigermoeller:fst", "3.0.4-jdk17"),
    TOOL_SERIALIZATION_JACKSON_CORE("com.fasterxml.jackson.core:jackson-core", ""),
    TOOL_SERIALIZATION_JACKSON_DATABIND("com.fasterxml.jackson.core:jackson-databind", ""),
    TOOL_SERIALIZATION_JACKSON_ANNOTATION("com.fasterxml.jackson.core:jackson-annotations", ""),
    TOOL_SERIALIZATION_PROTOBUF_GOOGLE_JAVA("com.google.protobuf:protobuf-java", ""),
    TOOL_SERIALIZATION_PROTOBUF_GOOGLE_JAVA_UTIL("com.google.protobuf:protobuf-java-util", ""),
    TOOL_SERIALIZATION_PROTOBUF_GOOGLE_PROTOC("com.google.protobuf:protoc", BOM_TOOL_SERIALIZATION_GOOGLE_PROTOBUF.version), // TODO wjm bom is not invalid
    TOOL_SERIALIZATION_PROTOBUF_SQUAREUP_WIRE("com.squareup.wire:wire-schema", ""),
    TOOL_SPI_GOOGLE_AUTO_SERVICE("com.google.auto.service:auto-service", "1.1.1"),
    TOOL_STREAM_JDFRAME("io.github.burukeyou:jdframe", "0.0.2"),
    TOOL_SQUAREUP_JAVAPOET("com.squareup:javapoet", "1.13.0"),
    TOOL_TEMPLATE_ENGINE_APACHE_VELOCITY("org.apache.velocity:velocity-engine-core", "2.4.1"),
    TOOL_THREAD_POOL_DYNAMIC_TP_COMMON("org.dromara.dynamictp:dynamic-tp-spring-boot-starter-common", ""),
    TOOL_THREAD_POOL_DYNAMIC_TP_CONFIG_NACOS("org.dromara.dynamictp:dynamic-tp-spring-cloud-starter-nacos", ""),
    TOOL_THREAD_POOL_DYNAMIC_TP_INTEGRATION_GRPC("org.dromara.dynamictp:dynamic-tp-spring-boot-starter-adapter-grpc", ""),
    TOOL_THREAD_POOL_DYNAMIC_TP_INTEGRATION_OKHTTP3("org.dromara.dynamictp:dynamic-tp-spring-boot-starter-adapter-okhttp3", ""),
    TOOL_THREAD_POOL_DYNAMIC_TP_INTEGRATION_TOMCAT("org.dromara.dynamictp:dynamic-tp-spring-boot-starter-adapter-webserver", ""),
    TOOL_VALIDATION_HIBERNATE("org.hibernate.validator:hibernate-validator", "8.0.2.Final"), // support spring-boot version to 3.4.5
    TOOL_VALIDATION_JAKARTA("jakarta.validation:jakarta.validation-api", "3.0.2"), // support spring-boot version to 3.4.5
    TOOL_VALIDATION_JSON_SCHEMA("com.networknt:json-schema-validator", "1.5.0"),
    TOOL_VALIDATION_SPRING("org.springframework.boot:spring-boot-starter-validation", ""),
    TOOL_VJTOOL("com.vip.vjtools:vjkit", "1.0.8"),
    TOOL_XML_DOM4J("org.dom4j:dom4j", "2.1.4"),
    TOOL_XML_JACKSON("com.fasterxml.jackson.dataformat:jackson-dataformat-xml", ""),
    TOOL_XML_JAVAX_JAXB_API("javax.xml.bind:jaxb-api", "2.3.1"),
    TOOL_YITTER_SNOWFLAKE_ID("com.github.yitter:yitter-idgenerator", "1.0.6"),

    TEST_CONTAINER("org.testcontainers:testcontainers", ""),
    TEST_JUNIT("org.junit.jupiter:junit-jupiter-api", "5.11.4"), // support spring-boot version to 3.4.5
    TEST_MOCKITO_CORE("org.mockito:mockito-core", ""),
    TEST_OPENJDK_JMH_CORE("org.openjdk.jmh:jmh-core", PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_OPENJDK_JMH.version),
    TEST_SPRING_BOOT_TEST("org.springframework.boot:spring-boot-starter-test", ""),
    TEST_SPRING_INTEGRATION_TEST("org.springframework.integration:spring-integration-test", ""),

    WEB_GRPC_API("io.grpc:grpc-api", ""),
    WEB_GRPC_PROTOBUF("io.grpc:grpc-protobuf", ""),
    WEB_GRPC_PROTO_GEN("io.grpc:protoc-gen-grpc-java", BOM_WEB_GRPC.version),
    WEB_GRPC_STUB("io.grpc:grpc-stub", ""),
    WEB_GRPC_SPRING_BOOT_VERSION("", "3.1.0.RELEASE"), // TODO wjm support spring-boot version to 3.2.4, spring-cloud version to 2023.0.0
    WEB_GRPC_SPRING_BOOT_CLIENT("net.devh:grpc-client-spring-boot-starter", WEB_GRPC_SPRING_BOOT_VERSION.version),
    WEB_GRPC_SPRING_BOOT_SERVER("net.devh:grpc-server-spring-boot-starter", WEB_GRPC_SPRING_BOOT_VERSION.version),
    WEB_IO_NETTY_ALL("io.netty:netty-all", ""),
    WEB_IO_NETTY_CODEC_HAPROXY("io.netty:netty-codec-haproxy", ""),
    WEB_IO_NETTY_CODEC_MQTT("io.netty:netty-codec-mqtt", ""),
    WEB_IO_NETTY_HANDLER("io.netty:netty-handler", ""),
    WEB_IO_NETTY_TRANSPORT("io.netty:netty-transport", ""),
    WEB_IO_REACTOR("io.projectreactor:reactor-core", ""),
    WEB_OKHTTPS("cn.zhxu:okhttps-jackson", "4.1.0"),
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
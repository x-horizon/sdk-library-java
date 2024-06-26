// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

import java.io.File
import java.util.concurrent.ConcurrentHashMap

object GradleModule {

    const val BOM = "bom"

    const val CACHE_ALL = "cache-all"
    const val CACHE_CAFFEINE = "cache-caffeine"
    const val CACHE_CONTRACT = "cache-contract"
    const val CACHE_LETTUCE = "cache-lettuce"
    const val CACHE_MAP = "cache-map"
    const val CACHE_REDIS = "cache-redis"

    const val CLOUD_COMMUNICATION_DROMARA_SMS = "cloud-communication-dromara-sms"
    const val CLOUD_COMMUNICATION_JAKARTA = "cloud-communication-jakarta"
    const val CLOUD_NATIVE_DOCKER = "cloud-native-docker"
    const val CLOUD_NATIVE_KUBERNETES = "cloud-native-kubernetes"

    const val CONCURRENT_ACTOR = "concurrent-actor"
    const val CONCURRENT_REDIS = "concurrent-redis"

    const val CONTRACT_COMPONENT_REDIS = "contract-component-redis"
    const val CONTRACT_CONSTANT = "contract-constant"
    const val CONTRACT_MODEL = "contract-model"

    const val DATA_CASSANDRA = "data-cassandra"
    const val DATA_ELASTICSEARCH = "data-elasticsearch"
    const val DATA_HSQLDB = "data-hsqldb"
    const val DATA_MYSQL = "data-mysql"
    const val DATA_POSTGRESQL = "data-postgresql"
    const val DATA_TDENGINE = "data-tdengine"

    const val DOC_KNIFE4J_CONTRACT = "doc-knife4j-contract"
    const val DOC_KNIFE4J_SPRING_WEBMVC = "doc-knife4j-spring-webmvc"
    const val DOC_KNIFE4J_SPRING_WEBFLUX = "doc-knife4j-spring-webflux"

    const val GATEWAY_SPRING = "gateway-spring"

    const val MESSAGE_ENGINE_ALL = "message-engine-all"
    const val MESSAGE_ENGINE_CONTRACT = "message-engine-contract"
    const val MESSAGE_ENGINE_KAFKA = "message-engine-kafka"
    const val MESSAGE_ENGINE_MQTT_CONTRACT = "message-engine-mqtt-contract"
    const val MESSAGE_ENGINE_MQTT_V3 = "message-engine-mqtt-v3"
    const val MESSAGE_ENGINE_MQTT_V5 = "message-engine-mqtt-v5"
    const val MESSAGE_ENGINE_RABBITMQ = "message-engine-rabbitmq"
    const val MESSAGE_ENGINE_REDIS_STREAM = "message-engine-redis-stream"
    const val MESSAGE_ENGINE_ROCKETMQ = "message-engine-rocketmq"

    const val METRIC_DROP_WIZARD = "metric-drop-wizard"
    const val METRIC_MICROMETER_PROMETHEUS = "metric-micrometer-prometheus"
    const val METRIC_SPRING = "metric-spring"
    const val METRIC_SYSTEM = "metric-system"

    const val ORM_CONTRACT = "orm-contract"
    const val ORM_CONTRACT_MYBATIS_BASE = "orm-contract-mybatis-base"
    const val ORM_CONTRACT_MYBATIS_BASE_POSTGRESQL = "orm-contract-mybatis-base-postgresql"
    const val ORM_CONTRACT_MYBATIS_FLEX = "orm-contract-mybatis-flex"
    const val ORM_MYBATIS_FLEX_BASE = "orm-mybatis-flex-base"
    const val ORM_MYBATIS_FLEX_POSTGRESQL = "orm-mybatis-flex-postgresql"
    const val ORM_MYBATIS_FLEX_TDENGINE = "orm-mybatis-flex-tdengine"
    const val ORM_MYBATIS_PLUS = "orm-mybatis-plus"
    const val ORM_SPRING_JDBC = "orm-spring-jdbc"
    const val ORM_SPRING_JPA = "orm-spring-jpa"

    const val OSS_CONTRACT = "oss-contract"
    const val OSS_MINIO = "oss-minio"

    const val PLUGGABLE_ANNOTATION_API_LOMBOK = "pluggable-annotation-api-lombok"
    const val PLUGGABLE_ANNOTATION_API_MICA_AUTO = "pluggable-annotation-api-mica-auto"
    const val PLUGGABLE_ANNOTATION_API_PROCESSOR_JMH = "pluggable-annotation-api-processor-jmh"
    const val PLUGGABLE_ANNOTATION_API_PROCESSOR_LOMBOK_MAPSTRUCT_BINDING = "pluggable-annotation-api-processor-lombok-mapstruct-binding"
    const val PLUGGABLE_ANNOTATION_API_PROCESSOR_MAPSTRUCT_PLUS = "pluggable-annotation-api-processor-mapstruct-plus"
    const val PLUGGABLE_ANNOTATION_API_PROCESSOR_MYBATIS_FLEX = "pluggable-annotation-api-processor-mybatis-flex"
    const val PLUGGABLE_ANNOTATION_API_PROCESSOR_SPRING = "pluggable-annotation-api-processor-spring"

    const val POOL_APACHE = "pool-apache"
    const val POOL_DATABASE_HIKARICP = "pool-database-hikaricp"

    const val PROTOCOL_JAVAX_SIP = "protocol-javax-sip"

    const val REGISTRATION_ZOOKEEPER = "registration-zookeeper"

    const val SECURITY_CONTRACT = "security-contract"
    const val SECURITY_SPRING = "security-spring"
    const val SECURITY_SA_TOKEN = "security-sa-token"

    const val STUDIO_LOW_CODE = "studio-low-code"

    const val TEST_CONTAINER = "test-container"
    const val TEST_JMH = "test-jmh"
    const val TEST_JUNIT = "test-junit"
    const val TEST_MOCKITO = "test-mockito"
    const val TEST_SPRING_BOOT = "test-spring-boot"
    const val TEST_SPRING_INTEGRATION = "test-spring-integration"

    const val TOOL_CONTENT_ANALYSIS = "tool-content-analysis"
    const val TOOL_CONVERT_ALL = "tool-convert-all"
    const val TOOL_CONVERT_JACKSON = "tool-convert-jackson"
    const val TOOL_CONVERT_MAPSTRUCT = "tool-convert-mapstruct"
    const val TOOL_CONVERT_PROTOBUF = "tool-convert-protobuf"
    const val TOOL_CONVERT_SPRING = "tool-convert-spring"
    const val TOOL_ENUMS = "tool-enums"
    const val TOOL_EXCEL = "tool-excel"
    const val TOOL_GEOMETRY = "tool-geometry"
    const val TOOL_ID_SNOWFLAKE = "tool-id-snowflake"
    const val TOOL_ID_UUID = "tool-id-uuid"
    const val TOOL_JDK = "tool-jdk"
    const val TOOL_JNA = "tool-jna"
    const val TOOL_JOB_QUARTZ = "tool-job-quartz"
    const val TOOL_LANG = "tool-lang"
    const val TOOL_LOG = "tool-log"
    const val TOOL_RATE_LIMITING = "tool-rate-limiting"
    const val TOOL_SERIALIZATION_FASTJSON = "tool-serialization-fastjson"
    const val TOOL_SERIALIZATION_FASTJSON2 = "tool-serialization-fastjson2"
    const val TOOL_SERIALIZATION_FST = "tool-serialization-fst"
    const val TOOL_SERIALIZATION_JACKSON = "tool-serialization-jackson"
    const val TOOL_SERIALIZATION_PROTOBUF_GOOGLE = "tool-serialization-protobuf-google"
    const val TOOL_SERIALIZATION_PROTOBUF_SQUAREUP = "tool-serialization-protobuf-squareup"
    const val TOOL_SPRING_CONTRACT = "tool-spring-contract"
    const val TOOL_SPRING_WEBFLUX = "tool-spring-webflux"
    const val TOOL_SPRING_WEBMVC = "tool-spring-webmvc"
    const val TOOL_TEMPLATE_ENGINE = "tool-template-engine"
    const val TOOL_VALIDATION_HIBERNATE = "tool-validation-hibernate"
    const val TOOL_VALIDATION_JAKARTA = "tool-validation-jakarta"
    const val TOOL_VALIDATION_JSON_SCHEMA = "tool-validation-json-schema"
    const val TOOL_VALIDATION_SPRING_BOOT = "tool-validation-spring"
    const val TOOL_XML = "tool-xml"

    const val TRANSACTION_SPRING = "transaction-spring"

    const val WEB_GRPC_CLIENT = "web-grpc-client"
    const val WEB_GRPC_CONTRACT = "web-grpc-contract"
    const val WEB_GRPC_SERVER = "web-grpc-server"
    const val WEB_NETTY = "web-netty"
    const val WEB_HTTP_OKHTTPS = "web-http-okhttps"
    const val WEB_OPEN_FEIGN = "web-openfeign"
    const val WEB_REACTOR = "web-reactor"
    const val WEB_WEBSOCKET = "web-websocket"

    @JvmStatic
    fun toReferenceName(moduleName: String): String {
        // this function can convert module name like "parent-child1-name-child2-name" to the reference module name like ":parent:child1-name:child2-name"
        return ModuleHelper.toReferenceName(moduleName)
    }

    @JvmStatic
    fun toModuleName(projectName: String): String {
        // the input is project.toString(), for example: "project ':parent:child1:child2'"
        // this function can convert project.toString() to the module name: "parent-child1-child2"
        return projectName.removePrefix("project ':").removeSuffix("'").replace(":", "-")
    }

}

internal object ModuleHelper {

    private const val SYSTEM_PROPERTY_ROOT_PROJECT_ABSOLUTE_PATH = "rootProjectAbsolutePath"

    private val ignoreModuleNames = setOf(
        ".idea",
        ".git",
        ".gradle",
        "gradle",
        "build",
        "target",
        "out",
        "dist",
        "src",
        "config",
        "buildSrc",
    )

    private data class ModuleTreeNode(val name: String, val children: MutableList<ModuleTreeNode> = mutableListOf())

    private val moduleNameMappingReferenceModuleNameMap = ConcurrentHashMap<String, String>().apply {
        // cache the special reference module name that cannot parse
        put(GradleModule.DOC_KNIFE4J_SPRING_WEBFLUX, ":doc:knife4j:spring-webflux")
        put(GradleModule.DOC_KNIFE4J_SPRING_WEBMVC, ":doc:knife4j:spring-webmvc")
        put(GradleModule.MESSAGE_ENGINE_REDIS_STREAM, ":message-engine:redis-stream")
        put(GradleModule.ORM_CONTRACT, ":orm:contract")
        put(GradleModule.ORM_CONTRACT_MYBATIS_BASE, ":orm:contract-mybatis-base")
        put(GradleModule.ORM_CONTRACT_MYBATIS_BASE_POSTGRESQL, ":orm:contract-mybatis-base-postgresql")
        put(GradleModule.ORM_CONTRACT_MYBATIS_FLEX, ":orm:contract-mybatis-flex")
        put(GradleModule.ORM_MYBATIS_FLEX_BASE, ":orm:mybatis-flex-base")
        put(GradleModule.ORM_MYBATIS_FLEX_POSTGRESQL, ":orm:mybatis-flex-postgresql")
        put(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_MAPSTRUCT_PLUS, ":pluggable-annotation-api:processor:mapstruct-plus")
        put(GradleModule.TOOL_SERIALIZATION_FASTJSON2, ":tool:serialization:fastjson2")
    }

    private var rootModuleTreeNode: ModuleTreeNode? = null

    internal fun toReferenceName(moduleName: String): String {
        return moduleNameMappingReferenceModuleNameMap.getOrPut(moduleName) {
            toReferenceName(
                getRootModuleTree().children.firstOrNull { subModuleTreeNode -> moduleName.startsWith(subModuleTreeNode.name) } ?: throw NoSuchElementException("could not find module node by module name [$moduleName], please check!"),
                moduleName,
                StringBuilder()
            )
        }
    }

    private fun toReferenceName(moduleTreeNode: ModuleTreeNode, moduleName: String, referenceModuleName: StringBuilder): String {
        if (moduleName.startsWith(moduleTreeNode.name)) {
            referenceModuleName.append(moduleTreeNode.name).append(":")
            moduleTreeNode.children.forEach { child -> toReferenceName(child, moduleName.removePrefix(moduleTreeNode.name + "-"), referenceModuleName) }
        }
        return ":" + referenceModuleName.toString().removeSuffix(":")
    }

    private fun getRootModuleTree(): ModuleTreeNode {
        if (null == rootModuleTreeNode) {
            val rootPath = System.getProperty(SYSTEM_PROPERTY_ROOT_PROJECT_ABSOLUTE_PATH)
            val moduleTreeNode = ModuleTreeNode(rootPath)
            generateModuleTree(moduleTreeNode, rootPath)
            rootModuleTreeNode = moduleTreeNode
        }
        return rootModuleTreeNode as ModuleTreeNode
    }

    private fun generateModuleTree(parent: ModuleTreeNode, modulePath: String) {
        return File(modulePath).listFiles()
            ?.filter { file -> file.isDirectory }
            ?.filter { directory -> !ignoreModuleNames.contains(directory.name) }
            ?.forEach continuing@{ directory ->
                val moduleNode = ModuleTreeNode(directory.name)
                parent.children.add(moduleNode)
                generateModuleTree(moduleNode, directory.absolutePath)
            }
            ?: return
    }

}
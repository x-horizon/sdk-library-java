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
    const val CONTRACT_COMPONENT_OSS = "contract-component-oss"
    const val CONTRACT_CONSTANT = "contract-constant"
    const val CONTRACT_MODEL = "contract-model"

    const val DOC_KNIFE4J_CONTRACT = "doc-knife4j-contract"
    const val DOC_KNIFE4J_SPRING_GATEWAY = "doc-knife4j-spring-gateway"
    const val DOC_KNIFE4J_SPRING_WEBMVC = "doc-knife4j-spring-webmvc"
    const val DOC_KNIFE4J_SPRING_WEBFLUX = "doc-knife4j-spring-webflux"

    const val GATEWAY_SPRING = "gateway-spring"

    const val JDBC_CASSANDRA = "jdbc-cassandra"
    const val JDBC_ELASTICSEARCH = "jdbc-elasticsearch"
    const val JDBC_HSQLDB = "jdbc-hsqldb"
    const val JDBC_MYSQL = "jdbc-mysql"
    const val JDBC_POSTGIS = "jdbc-postgis"
    const val JDBC_POSTGRESQL = "jdbc-postgresql"
    const val JDBC_TDENGINE = "jdbc-tdengine"

    const val MESSAGE_ENGINE_CLIENT_ALL = "message-engine-client-all"
    const val MESSAGE_ENGINE_CLIENT_CONTRACT = "message-engine-client-contract"
    const val MESSAGE_ENGINE_CLIENT_KAFKA = "message-engine-client-kafka"
    const val MESSAGE_ENGINE_CLIENT_MQTT_CONTRACT = "message-engine-client-mqtt-contract"
    const val MESSAGE_ENGINE_CLIENT_MQTT_V3 = "message-engine-client-mqtt-v3"
    const val MESSAGE_ENGINE_CLIENT_MQTT_V5 = "message-engine-client-mqtt-v5"
    const val MESSAGE_ENGINE_CLIENT_RABBITMQ = "message-engine-client-rabbitmq"
    const val MESSAGE_ENGINE_CLIENT_REDIS_STREAM = "message-engine-client-redis-stream"
    const val MESSAGE_ENGINE_CLIENT_ROCKETMQ = "message-engine-client-rocketmq"
    const val MESSAGE_ENGINE_SERVER_CONTRACT = "message-engine-server-contract"
    const val MESSAGE_ENGINE_SERVER_MQTT = "message-engine-server-mqtt"

    const val METRIC_DROP_WIZARD = "metric-drop-wizard"
    const val METRIC_MICROMETER_PROMETHEUS = "metric-micrometer-prometheus"
    const val METRIC_SPRING = "metric-spring"
    const val METRIC_SYSTEM = "metric-system"

    const val ORM_CONTRACT = "orm-contract"
    const val ORM_CONTRACT_MYBATIS_BASE = "orm-contract-mybatis-base"
    const val ORM_CONTRACT_MYBATIS_BASE_POSTGIS = "orm-contract-mybatis-base-postgis"
    const val ORM_CONTRACT_MYBATIS_BASE_POSTGRESQL = "orm-contract-mybatis-base-postgresql"
    const val ORM_CONTRACT_MYBATIS_FLEX = "orm-contract-mybatis-flex"
    const val ORM_MYBATIS_FLEX_BASE = "orm-mybatis-flex-base"
    const val ORM_MYBATIS_FLEX_POSTGIS = "orm-mybatis-flex-postgis"
    const val ORM_MYBATIS_FLEX_POSTGRESQL = "orm-mybatis-flex-postgresql"
    const val ORM_MYBATIS_FLEX_TDENGINE = "orm-mybatis-flex-tdengine"
    const val ORM_MYBATIS_PLUS = "orm-mybatis-plus"
    const val ORM_SPRING_JDBC = "orm-spring-jdbc"
    const val ORM_SPRING_JPA = "orm-spring-jpa"

    const val OSS_API = "oss-api"
    const val OSS_CONTRACT = "oss-contract"
    const val OSS_LOCAL = "oss-local"
    const val OSS_MINIO = "oss-minio"

    const val PLUGGABLE_ANNOTATION_API_LOMBOK = "pluggable-annotation-api-lombok"
    const val PLUGGABLE_ANNOTATION_API_MICA_AUTO = "pluggable-annotation-api-mica-auto"
    const val PLUGGABLE_ANNOTATION_API_PROCESSOR_JMH = "pluggable-annotation-api-processor-jmh"
    const val PLUGGABLE_ANNOTATION_API_PROCESSOR_LOMBOK_MAPSTRUCT_BINDING = "pluggable-annotation-api-processor-lombok-mapstruct-binding"
    const val PLUGGABLE_ANNOTATION_API_PROCESSOR_MAPSTRUCT_PLUS = "pluggable-annotation-api-processor-mapstruct-plus"
    const val PLUGGABLE_ANNOTATION_API_PROCESSOR_MAPSTRUCT_PROTOBUF = "pluggable-annotation-api-processor-mapstruct-protobuf"
    const val PLUGGABLE_ANNOTATION_API_PROCESSOR_MYBATIS_FLEX = "pluggable-annotation-api-processor-mybatis-flex"
    const val PLUGGABLE_ANNOTATION_API_PROCESSOR_SPRING = "pluggable-annotation-api-processor-spring"

    const val POOL_APACHE = "pool-apache"
    const val POOL_DATABASE_HIKARICP = "pool-database-hikaricp"

    const val PROTOCOL_JAVAX_SIP = "protocol-javax-sip"

    const val REGISTRATION_NACOS = "registration-nacos"
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
    const val TOOL_CONVERT_API = "tool-convert-api"
    const val TOOL_CONVERT_JACKSON = "tool-convert-jackson"
    const val TOOL_CONVERT_JACKSON_GEOMETRY = "tool-convert-jackson-geometry"
    const val TOOL_CONVERT_MAPSTRUCT_PLUS = "tool-convert-mapstruct-plus"
    const val TOOL_CONVERT_MAPSTRUCT_PROTOBUF = "tool-convert-mapstruct-protobuf"
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
    const val TOOL_THREAD_POOL_CONTRACT = "tool-thread-pool-contract"
    const val TOOL_THREAD_POOL_CONFIG_NACOS = "tool-thread-pool-config-nacos"
    const val TOOL_THREAD_POOL_INTEGRATION_GRPC = "tool-thread-pool-integration-grpc"
    const val TOOL_THREAD_POOL_INTEGRATION_OKHTTP3 = "tool-thread-pool-integration-okhttp3"
    const val TOOL_THREAD_POOL_INTEGRATION_TOMCAT = "tool-thread-pool-integration-tomcat"
    const val TOOL_VALIDATION_HIBERNATE = "tool-validation-hibernate"
    const val TOOL_VALIDATION_JAKARTA = "tool-validation-jakarta"
    const val TOOL_VALIDATION_JSON_SCHEMA = "tool-validation-json-schema"
    const val TOOL_VALIDATION_SPRING_BOOT = "tool-validation-spring"
    const val TOOL_XML = "tool-xml"

    const val TRANSACTION_SPRING = "transaction-spring"

    const val WEB_GRPC_CLIENT = "web-grpc-client"
    const val WEB_GRPC_CONTRACT = "web-grpc-contract"
    const val WEB_GRPC_SERVER = "web-grpc-server"
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
        put(GradleModule.DOC_KNIFE4J_SPRING_GATEWAY, ":doc:knife4j:spring-gateway")
        put(GradleModule.DOC_KNIFE4J_SPRING_WEBFLUX, ":doc:knife4j:spring-webflux")
        put(GradleModule.DOC_KNIFE4J_SPRING_WEBMVC, ":doc:knife4j:spring-webmvc")
        put(GradleModule.MESSAGE_ENGINE_CLIENT_REDIS_STREAM, ":message-engine:client:redis-stream")
        put(GradleModule.ORM_CONTRACT, ":orm:contract")
        put(GradleModule.ORM_CONTRACT_MYBATIS_BASE, ":orm:contract-mybatis-base")
        put(GradleModule.ORM_CONTRACT_MYBATIS_BASE_POSTGIS, ":orm:contract-mybatis-base-postgis")
        put(GradleModule.ORM_CONTRACT_MYBATIS_BASE_POSTGRESQL, ":orm:contract-mybatis-base-postgresql")
        put(GradleModule.ORM_CONTRACT_MYBATIS_FLEX, ":orm:contract-mybatis-flex")
        put(GradleModule.ORM_MYBATIS_FLEX_BASE, ":orm:mybatis-flex-base")
        put(GradleModule.ORM_MYBATIS_FLEX_POSTGIS, ":orm:mybatis-flex-postgis")
        put(GradleModule.ORM_MYBATIS_FLEX_POSTGRESQL, ":orm:mybatis-flex-postgresql")
        put(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_MAPSTRUCT_PLUS, ":pluggable-annotation-api:processor:mapstruct-plus")
        put(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_MAPSTRUCT_PROTOBUF, ":pluggable-annotation-api:processor:mapstruct-protobuf")
        put(GradleModule.TOOL_CONVERT_JACKSON, ":tool:convert:jackson")
        put(GradleModule.TOOL_CONVERT_JACKSON_GEOMETRY, ":tool:convert:jackson-geometry")
        put(GradleModule.TOOL_CONVERT_MAPSTRUCT_PLUS, ":tool:convert:mapstruct-plus")
        put(GradleModule.TOOL_CONVERT_MAPSTRUCT_PROTOBUF, ":tool:convert:mapstruct-protobuf")
        put(GradleModule.TOOL_SERIALIZATION_FASTJSON2, ":tool:serialization:fastjson2")
        put(GradleModule.TOOL_THREAD_POOL_CONTRACT, ":tool:thread-pool:contract")
        put(GradleModule.TOOL_THREAD_POOL_CONFIG_NACOS, ":tool:thread-pool:config:nacos")
        put(GradleModule.TOOL_THREAD_POOL_INTEGRATION_GRPC, ":tool:thread-pool:integration:grpc")
        put(GradleModule.TOOL_THREAD_POOL_INTEGRATION_OKHTTP3, ":tool:thread-pool:integration:okhttp3")
        put(GradleModule.TOOL_THREAD_POOL_INTEGRATION_TOMCAT, ":tool:thread-pool:integration:tomcat")
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
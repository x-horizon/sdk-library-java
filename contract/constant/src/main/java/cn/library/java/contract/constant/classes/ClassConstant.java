package cn.library.java.contract.constant.classes;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * class constant
 *
 * @author wjm
 * @since 2023-10-09 09:11
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClassConstant {

    public static final String CLASS_PATH_PREFIX = "classpath*:";

    public static final String REFERENCE_JAR_PATH = ".jar!/";

    public static final String JAVA_TEST_PATH = "/java/test/";

    public static final String JAVA_MAIN_PATH = "/java/main/";

    public static final String RESOURCE_MAIN_PATH = "/resources/main/";

    public static final String DOCKER_APP_CLASS_PATH = "/app/classes/";

    public static final String DOCKER_APP_RESOURCE_PATH = "/app/resources/";

    public static final Set<String> SOURCE_ROOT_PATHS = Set.of(JAVA_MAIN_PATH, RESOURCE_MAIN_PATH, DOCKER_APP_CLASS_PATH, DOCKER_APP_RESOURCE_PATH);

}
package org.horizon.sdk.library.java.tool.enums;

import org.horizon.sdk.library.java.tool.enums.autoconfigure.EnableEnumAutowired;
import org.horizon.sdk.library.java.tool.enums.autoconfigure.EnumAutoConfigurer;
import org.horizon.sdk.library.java.tool.enums.autoconfigure.EnumAutowiredRegistrar;
import org.horizon.sdk.library.java.tool.enums.strategy.EnumAutowiredCollector;
import org.horizon.sdk.library.java.tool.enums.strategy.EnumAutowiredFieldMatchByMostSimilarRule;
import org.horizon.sdk.library.java.tool.enums.strategy.EnumAutowiredFieldMatchRule;

import java.lang.annotation.*;

/**
 * <p>autowired spring bean to enum field.</p>
 *
 * <p>example code:</p>
 * <pre>{@code
 * // define an root interface
 * public interface GenderStrategy {}
 *
 * // a spring ioc subclass bean implement by root interface
 * @Component
 * public class GenderManStrategy implements GenderStrategy {}
 *
 * // a spring ioc subclass bean implement by root interface
 * @Component
 * public class GenderWomanStrategy implements GenderStrategy {}
 *
 * // a spring ioc subclass bean implement by root interface
 * @Component
 * public class GenderUnknownStrategy implements GenderStrategy {}
 *
 * // define an enum and set the root interface
 * @Getter
 * @EnumAutowired(rootClass = GenderStrategy.class)
 * public enum GenderType {
 *     WOMAN(1, "woman"),
 *     MAN(2, "man"),
 *     UNKNOWN(3, "unknown");
 *
 *     GenderType(int code, String description) {
 *         this.code = code;
 *         this.description = description;
 *     }
 *
 *     private final int code;
 *     private final String description;
 *     private GenderStrategy strategy;
 * }
 *
 * // define the spring boot main function
 * @EnableEnumAutowired
 * @SpringBootApplication
 * public class Test {
 *     public static void main(String[] args) {
 *         SpringApplication.run(Test.class, args);
 *     }
 * }
 * }</pre>
 *
 * <p>when run the main function, it will happen:</p>
 * <ol>
 *     <li>merge the main function root package path and the package paths define in
 *         {@link org.horizon.sdk.library.java.tool.enums.autoconfigure.EnableEnumAutowired#scanPackagePaths()}</li>
 *     <li>scan all package paths to find enum classes marked with
 *         {@link EnumAutowired}, such as [GenderType] marked with
 *         {@code @}{@link EnumAutowired}{@code (rootClass = GenderStrategy.class)}</li>
 *     <li>set {@link EnumAutowired#autowiredFiledName()} to [strategy] when:
 *         <ul>
 *             <li>not explicitly specified</li>
 *             <li>only one data type GenderStrategy field matches
 *                 {@link EnumAutowired#rootClasses()}</li>
 *         </ul>
 *         (multiple matching fields require explicit specification of autowiredFiledName)</li>
 *     <li>scan subclass simple names of
 *         {@link EnumAutowired#rootClasses()},
 *         e.g. ["GenderManStrategy", "GenderWomanStrategy", "GenderUnknownStrategy"]</li>
 *     <li>find the most similar name between enum field name and subclass simple name,
 *         e.g. enum field "WOMAN" matches "GenderWomanStrategy"</li>
 *     <li>autowired GenderWomanStrategy instance to enum field [WOMAN]</li>
 * </ol>
 *
 * @author wjm
 * @see EnableEnumAutowired
 * @see EnumAutowiredRegistrar
 * @see EnumAutoConfigurer#enumAutowiredCollector()
 * @see EnumAutowiredCollector
 * @since 2021-09-08 16:07
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnumAutowired {

    /**
     * the root interface to autowired into enum field
     *
     * @return the root interface to autowired into enum field
     */
    Class<?>[] rootClasses();

    /**
     * <pre>
     * the specified field name to autowired,
     * it will find field name with the same data type as {@link #rootClasses()} in the enum marked with {@link EnumAutowired} if it is blank.
     * </pre>
     *
     * @return the specified field name to autowired
     */
    String autowiredFiledName() default "";

    /**
     * if set it to true, will never autowired the instance to spring ioc when the {@link #matchRule()} cannot find the suitable class.
     *
     * @return the specified field name to autowired
     */
    boolean allowNull() default false;

    /**
     * the rule to match the subclass name of {@link #rootClasses()} and the field name in the enum marked with {@link EnumAutowired}
     *
     * @return the specified rule
     */
    Class<? extends EnumAutowiredFieldMatchRule> matchRule() default EnumAutowiredFieldMatchByMostSimilarRule.class;

}
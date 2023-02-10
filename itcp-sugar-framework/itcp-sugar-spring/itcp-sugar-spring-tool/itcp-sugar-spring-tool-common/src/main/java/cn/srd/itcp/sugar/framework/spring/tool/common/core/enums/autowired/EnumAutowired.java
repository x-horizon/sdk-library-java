package cn.srd.itcp.sugar.framework.spring.tool.common.core.enums.autowired;

import cn.srd.itcp.sugar.framework.spring.tool.common.core.enums.autowired.exception.EnumAutowiredMultiMatchedFieldException;
import cn.srd.itcp.sugar.framework.spring.tool.common.core.enums.autowired.exception.EnumAutowiredUnmatchedFieldException;
import cn.srd.itcp.sugar.framework.spring.tool.common.core.enums.autowired.exception.EnumAutowiredUnsupportedException;

import java.lang.annotation.*;

/**
 * 为枚举中相应的字段自动注入 Spring Bean
 * <pre>
 *     public interface Handler {}
 *
 *     &#064;Component
 *     public class Xxx1Handler implements Handler {}
 *
 *     &#064;Component
 *     public class Xxx2Handler implements Handler {}
 *
 *     &#064;Component
 *     public class Xxx3Handler implements Handler {}
 *
 *     &#064;Getter
 *     public enum HandlerEnum {
 *         XXX1,
 *         XXX2,
 *         XXX3;
 *         private Handler handler;
 *     }
 *
 *     上述代码中：
 *      一、无法声明单例的类难以绑定到枚举：Enum 的修饰符为 public static final，这意味着在类加载时就需要获取到实例绑定到枚举中，
 *         正常来说，声明一个 Handler 实现类的单例直接绑定到枚举中即可，而 Handler 的实现类被 Spring 管理，一般不会显式声明单例，
 *         此时需要绑定到枚举就必须要特殊处理，例如使用 Spring 的钩子进行注入，其缺点是每个这样的枚举都要写一连串重复的代码，且每增加一个枚举就必须要手动注入一次，容易出错；
 *      二、本注解可以实现扫描要绑定的接口的实现类，并自动注入到枚举的某个字段中；
 *      三、用法：直接在枚举上声明被 Spring 管理的实现类的父级接口即可，示例代码：
 *
 *               &#064;Getter
 *               &#064;{@link EnumAutowired}(autowiredBeanClass = Handler.class)
 *               public enum HandlerEnum {
 *                   XXX1,
 *                   XXX2,
 *                   XXX3;
 *                   private Handler handler;
 *               }
 *
 *          此时将获取 Handler 的实现类，并根据一定的规则，分别注入到 XXX1、XXX2、XXX3 的 handler 字段中；
 *          注：本注解只能标记在 Enum 类型的类上，否则抛出 {@link EnumAutowiredUnsupportedException} 异常；
 *      四、参数说明：
 *          1、{@link #autowiredFiledName()}：
 *              显式指定接口实现类要注入到枚举的哪个字段中，若不指定，则获取枚举中类型为指定接口的字段进行注入，例如：
 *
 *                   &#064;Getter
 *                   &#064;{@link EnumAutowired}(autowiredBeanClass = Handler.class)
 *                   public enum HandlerEnum {
 *                       XXX1,
 *                       XXX2,
 *                       XXX3;
 *                       private Handler handler;
 *                   }
 *
 *              {@link #autowiredBeanClass()} 指定的父级接口为 Handler，则获取到枚举中类型为 Handler 的字段进行注入，即 private Handler handler 字段；
 *              注：
 *                若类型为指定接口的字段没有或有多个，此时无法注入，将会抛出 {@link EnumAutowiredUnmatchedFieldException} 或 {@link EnumAutowiredMultiMatchedFieldException} 异常；
 *                若类型为指定接口的字段有多个，此时可以使用 {@link #autowiredFiledName()} 显式指定具体要注入到哪个字段中；
 *          2、{@link #findBeanNamesMayAutowiredRule()}：
 *              获取到指定的父级接口所有实现类后，根据该字段指定的规则，匹配到枚举可能要注入的实现类，匹配到的实现类可能有多个，例如：
 *
 *                   public interface Handler {}
 *
 *                   &#064;Component
 *                   public class Xxx1Handler implements Handler {}
 *
 *                   &#064;Component
 *                   public class Xxx2Xxx1Handler implements Handler {}
 *
 *                   &#064;Getter
 *                   &#064;{@link EnumAutowired}(autowiredBeanClass = Handler.class)
 *                   public enum HandlerEnum {
 *                       XXX1,
 *                       XXX2,
 *                       private Handler handler;
 *                   }
 *
 *              默认使用的匹配规则为：
 *                 {@link FindBeanNamesMayAutowiredByBeanNameContainsIgnoreCaseEnumNameRule}：若实现类的类名包含枚举名（忽略大小写），则可以匹配；
 *                 上述代码中：
 *                   实现类的类名 Xxx1Handler、Xxx2Xxx1Handler 都包含了枚举名 XXX1，此时 XXX1 将被匹配到两个要注入的实现类，即 Xxx1Handler、Xxx2Xxx1Handler；
 *                   实现类的类名 Xxx2Xxx1Handler 包含了枚举名 XXX2，此时 XXX2 将被匹配到一个要注入的实现类，即 Xxx2Xxx1Handler；
 *          3、{@link #findBeanNameToAutowiredRule()}：
 *              根据 {@link #findBeanNamesMayAutowiredRule()} 获取到枚举可能要注入的实现类后，根据该字段指定的规则，筛选出最终要注入的实现类；
 *              默认使用的匹配规则为：
 *                 {@link FindBeanNameToAutowiredByShortestBeanNameRule}：将最短的类名作为最终要注入的实现类；
 *              在第 2 点的说明中：
 *                 XXX1 将被匹配到两个要注入的实现类，即 Xxx1Handler、Xxx2Xxx1Handler -> 这两个实现类的类名中最短的为 Xxx1Handler，则将 Xxx1Handler 作为最终要注入的实现类；
 *                 XXX2 将被匹配到一个要注入的实现类，即 Xxx2Xxx1Handler -> 只有一个，直接将其作为最终要注入的实现类；
 * </pre>
 *
 * @author wjm
 * @see EnumAutowiredSupport
 * @since 2021/9/8 16:07
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnumAutowired {

    /**
     * 指定要注入的实现类的父级接口
     *
     * @return 父级接口
     */
    Class<?> autowiredBeanClass();

    /**
     * 指定具体要将实现类注入到枚举的哪个属性中
     *
     * @return 枚举属性名
     */
    String autowiredFiledName() default "";

    /**
     * 获取到指定的父级接口所有实现类后，根据该字段指定的规则，匹配到枚举可能要注入的实现类
     *
     * @return 匹配枚举可能要注入的实现类规则
     */
    Class<? extends FindBeanNamesMayAutowiredRule> findBeanNamesMayAutowiredRule() default FindBeanNamesMayAutowiredByBeanNameContainsIgnoreCaseEnumNameRule.class;

    /**
     * 获取到枚举可能要注入的实现类后，根据该字段指定的规则，筛选出最终要注入的实现类
     *
     * @return 匹配枚举最终要注入的实现类规则
     */
    Class<? extends FindBeanNameToAutowiredRule> findBeanNameToAutowiredRule() default FindBeanNameToAutowiredByShortestBeanNameRule.class;

}

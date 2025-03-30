package org.horizon.sdk.library.java.tool.spring.contract.support;

import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.springframework.core.StandardReflectionParameterNameDiscoverer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * spring aop capture
 *
 * @author wjm
 * @since 2023-06-09 15:06
 */
public interface AopCaptor {

    /**
     * get method signature marked on aop annotation
     *
     * @param joinPoint {@link ProceedingJoinPoint}
     * @return method signature marked on aop annotation
     */
    default MethodSignature getMethodSignature(ProceedingJoinPoint joinPoint) {
        return ((MethodSignature) joinPoint.getSignature());
    }

    /**
     * get method marked on aop annotation
     *
     * @param joinPoint {@link ProceedingJoinPoint}
     * @return {@link Method} marked on aop annotation
     */
    default Method getMethod(ProceedingJoinPoint joinPoint) {
        return getMethodSignature(joinPoint).getMethod();
    }

    /**
     * get class where method marked on aop annotation located
     *
     * @param joinPoint {@link ProceedingJoinPoint}
     * @return {@link Class} where method marked on aop annotation located
     */
    default Class<?> getClass(ProceedingJoinPoint joinPoint) {
        return joinPoint.getTarget().getClass();
    }

    /**
     * get method parameters marked on aop annotation
     *
     * @param joinPoint {@link ProceedingJoinPoint}
     * @return method {@link Parameter}[] marked on aop annotation
     */
    default Parameter[] getMethodParameters(ProceedingJoinPoint joinPoint) {
        return getMethod(joinPoint).getParameters();
    }

    /**
     * <p>get method parameter names marked on AOP annotation.</p>
     *
     * <p>note the following scenarios:</p>
     * <pre>{@code
     * @Bean
     * public SomeBean someBean(FooBar foo, FooBar bar) {
     *     return new SomeBean(foo, bar);
     * }
     *
     * @Bean
     * public FooBar foo() {
     *     return new FooBar();
     * }
     *
     * @Bean
     * public FooBar bar() {
     *     return new FooBar();
     * }
     * }</pre>
     *
     * <ol>
     *   <li>
     *     after Java compilation, method parameter names are not preserved. Spring resolves this through:
     *     <ul>
     *       <li>
     *         before Spring 4.x: uses {@link org.springframework.core.LocalVariableTableParameterNameDiscoverer}
     *         (ASM bytecode analysis)
     *       </li>
     *       <li>
     *         Spring 4.x+: uses {@link StandardReflectionParameterNameDiscoverer} (JDK8 reflection)
     *         <p>requires {@code -parameters} compiler argument</p>
     *       </li>
     *       <li>
     *         Spring 6.x+: {@link org.springframework.core.LocalVariableTableParameterNameDiscoverer} is deprecated
     *         <p>fallback behavior: returns "arg0", "arg1" without {@code -parameters} flag</p>
     *       </li>
     *     </ul>
     *   </li>
     *
     *   <li>
     *     configuration examples:
     *     <p>maven:</p>
     *     <pre>{@code
     *     <build>
     *         <plugins>
     *             <plugin>
     *                 <groupId>org.apache.maven.plugins</groupId>
     *                 <artifactId>maven-compiler-plugin</artifactId>
     *                 <configuration>
     *                     <compilerArgs>
     *                         <arg>-parameters</arg>
     *                     </compilerArgs>
     *                 </configuration>
     *             </plugin>
     *         </plugins>
     *     </build>
     *     }</pre>
     *
     *     <p>gradle (Kotlin DSL):</p>
     *     <pre>{@code
     *     tasks.withType<JavaCompile> {
     *         options.compilerArgs.add("-parameters")
     *     }
     *     }</pre>
     *   </li>
     * </ol>
     *
     * @param joinPoint AOP {@link ProceedingJoinPoint}
     * @return method parameter names from AOP annotations
     */
    default String[] getMethodParameterNames(ProceedingJoinPoint joinPoint) {
        return getMethodSignature(joinPoint).getParameterNames();
    }

    /**
     * get aop annotation marked on method
     *
     * @param joinPoint       {@link ProceedingJoinPoint}
     * @param annotationClass aop annotation
     * @param <T>             aop annotation type
     * @return aop annotation marked on method
     */
    default <T extends Annotation> T getAnnotationMarkedOnMethod(ProceedingJoinPoint joinPoint, Class<T> annotationClass) {
        return getMethod(joinPoint).getAnnotation(annotationClass);
    }

    /**
     * get aop annotation where method marked on aop annotation located
     *
     * @param joinPoint       {@link ProceedingJoinPoint}
     * @param annotationClass aop annotation
     * @param <T>             aop annotation type
     * @return aop annotation where method marked on aop annotation located
     */
    default <T extends Annotation> T getAnnotationMarkedOnClass(ProceedingJoinPoint joinPoint, Class<T> annotationClass) {
        Class<?> joinPointClass = getClass(joinPoint);
        // first find the annotation in current class
        T annotation = joinPointClass.getAnnotation(annotationClass);
        // then find the annotation from parent interface of current class if it could not be found annotation in current class.
        // once found, then break.
        if (Nil.isNull(annotation)) {
            for (Class<?> superJoinPointClass : joinPointClass.getInterfaces()) {
                annotation = superJoinPointClass.getAnnotation(annotationClass);
                if (Nil.isNotNull(annotation)) {
                    break;
                }
            }
        }
        // final find the annotation from parent class of current class if it could not be found annotation in all parent interfaces.
        if (Nil.isNull(annotation)) {
            annotation = joinPointClass.getSuperclass().getAnnotation(annotationClass);
        }
        return annotation;
    }

    /**
     * auto handle exception by {@link ProceedingJoinPoint#proceed()}
     *
     * @param joinPoint {@link ProceedingJoinPoint}
     * @return {@link ProceedingJoinPoint#proceed()} return
     */
    @SneakyThrows
    default Object doProceed(ProceedingJoinPoint joinPoint) {
        return joinPoint.proceed();
    }

    /**
     * auto handle exception by {@link ProceedingJoinPoint#proceed(Object[])}
     *
     * @param joinPoint {@link ProceedingJoinPoint}
     * @return {@link ProceedingJoinPoint#proceed(Object[])}  return
     */
    @SneakyThrows
    default Object doProceed(ProceedingJoinPoint joinPoint, Object[] parameters) {
        return joinPoint.proceed(parameters);
    }

    /**
     * support convert a specified class type from {@link #doProceed(ProceedingJoinPoint)}
     *
     * @param joinPoint {@link ProceedingJoinPoint}
     * @param classType the class type
     * @param <T>       the class type
     * @return {@link ProceedingJoinPoint#proceed()} return
     */
    default <T> T doProceed(ProceedingJoinPoint joinPoint, Class<T> classType) {
        return classType.cast(doProceed(joinPoint));
    }

}
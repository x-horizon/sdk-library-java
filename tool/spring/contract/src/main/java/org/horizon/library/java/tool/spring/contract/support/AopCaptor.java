package org.horizon.library.java.tool.spring.contract.support;

import org.horizon.library.java.tool.lang.object.Nil;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.ParameterNameDiscoverer;
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
     * <pre>
     * get method parameter names marked on aop annotation.
     *
     * note:
     * let's see the following code:
     *  {@code
     *     @Bean
     *     public SomeBean someBean(FooBar foo, FooBar bar) {
     *         return new SomeBean(foo, bar);
     *     }
     *
     *     @Bean
     *     public FooBar foo() {
     *         return new FooBar();
     *     }
     *
     *     @Bean
     *     public FooBar bar() {
     *         return new FooBar();
     *     }
     *  }
     * <li>after java compilation, method parameter names are not preserved, so, how does Spring know whether to inject foo or bar when injecting FooBar?</li><br>
     * <li>before spring 4.x version, the class {@link org.springframework.core.LocalVariableTableParameterNameDiscoverer} implement by {@link ParameterNameDiscoverer} use asm bytecode to obtain parameter names.</li><br>
     * <li>after spring 4.x version, the class {@link StandardReflectionParameterNameDiscoverer} was used instead of {@link org.springframework.core.LocalVariableTableParameterNameDiscoverer},
     *     it use the standard implementation of jdk8, which uses reflection to obtain variable names.
     *     however, this method requires adding the -parameters parameter during compilation to preserve variable names.</li><br>
     * <li>after spring 6.x version, the class {@link org.springframework.core.LocalVariableTableParameterNameDiscoverer} was marked as @{@link Deprecated},
     *     when there is a situation where the variable name needs to be resolved,
     *     but the -parameters parameter was not added during compilation,
     *     {@link org.springframework.core.LocalVariableTableParameterNameDiscoverer} will still be called,
     *     and a warning will be printed:
     *     Using deprecated '-debug' fallback for parameter name resolution. Compile the affected code with '-parameters' instead or avoid its introspection.</li><br>
     * <li>based on the above description, if your project does not include the -parameters parameter during compilation,
     *     the parameter name by calling {@link #getMethodParameters(ProceedingJoinPoint)} result will be arg0, arg1...
     *     you need to use function {@link #getMethodParameterNames(ProceedingJoinPoint)} to get the actual parameter name,
     *     also you considering adding the -parameters parameter to compilation.<br><br>
     *
     *     in maven, you can adding the -parameters parameter to compilation like this:<br>
     *     {@code
     *        <build>
     *            <plugins>
     *                <plugin>
     *                    <groupId>org.apache.maven.plugins</groupId>
     *                    <artifactId>maven-compiler-plugin</artifactId>
     *                    <version>{maven-compiler-plugin.version}</version>
     *                    <configuration>
     *                        <compilerArgs>
     *                            <arg>-parameters</arg>
     *                        </compilerArgs>
     *                    </configuration>
     *                </plugin>
     *            </plugins>
     *        </build>
     *     }
     *
     *     <br><br>
     *     in gradle kotlin dsl, you can adding the -parameters parameter to compilation like this:<br>
     *     {@code
     *        tasks.withType<JavaCompile> {
     *            options.compilerArgs.plusAssign("-parameters")
     *        }
     *     }
     *     </li><br>
     * </pre>
     *
     * @param joinPoint {@link ProceedingJoinPoint}
     * @return method parameter names marked on aop annotation
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
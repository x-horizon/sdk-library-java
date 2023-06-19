package cn.srd.itcp.sugar.framework.spring.tool.common.core;

import cn.srd.itcp.sugar.tool.core.object.Objects;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * spring aop capture
 *
 * @author wjm
 * @since 2023-06-09 15:06:14
 */
public interface AopCaptor {

    /**
     * get method marked on aop annotation
     *
     * @param joinPoint {@link ProceedingJoinPoint}
     * @return {@link Method} marked on aop annotation
     */
    default Method getMethod(ProceedingJoinPoint joinPoint) {
        return ((MethodSignature) joinPoint.getSignature()).getMethod();
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
        if (Objects.isNull(annotation)) {
            for (Class<?> superJoinPointClass : joinPointClass.getInterfaces()) {
                annotation = superJoinPointClass.getAnnotation(annotationClass);
                if (Objects.isNotNull(annotation)) {
                    break;
                }
            }
        }
        // final find the annotation from parent class of current class if it could not be found annotation in all parent interfaces.
        if (Objects.isNull(annotation)) {
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

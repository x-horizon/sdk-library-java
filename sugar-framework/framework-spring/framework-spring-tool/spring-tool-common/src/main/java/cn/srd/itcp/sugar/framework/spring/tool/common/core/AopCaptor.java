package cn.srd.itcp.sugar.framework.spring.tool.common.core;

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
     * get class where method marked on aop annotation located
     *
     * @param joinPoint {@link ProceedingJoinPoint}
     * @return {@link Class} where method marked on aop annotation located
     */
    default Class<?> getClass(ProceedingJoinPoint joinPoint) {
        return joinPoint.getTarget().getClass();
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
        return getClass(joinPoint).getAnnotation(annotationClass);
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

}

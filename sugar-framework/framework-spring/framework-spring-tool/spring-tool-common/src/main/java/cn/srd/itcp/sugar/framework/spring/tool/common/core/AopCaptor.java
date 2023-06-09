package cn.srd.itcp.sugar.framework.spring.tool.common.core;

import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.cache.support.NullValue;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * {@link NullValue} tool
 *
 * @author wjm
 * @since 2023-06-09 15:06:14
 */
public interface AopCaptor {

    default Method getMethod(ProceedingJoinPoint joinPoint) {
        return ((MethodSignature) joinPoint.getSignature()).getMethod();
    }

    default Parameter[] getMethodParameters(ProceedingJoinPoint joinPoint) {
        return getMethod(joinPoint).getParameters();
    }

    default <T extends Annotation> T getAnnotationMarkedOnMethod(ProceedingJoinPoint joinPoint, Class<T> annotationClass) {
        return getMethod(joinPoint).getAnnotation(annotationClass);
    }

    default Class<?> getClass(ProceedingJoinPoint joinPoint) {
        return joinPoint.getTarget().getClass();
    }

    default <T extends Annotation> T getAnnotationMarkedOnClass(ProceedingJoinPoint joinPoint, Class<T> annotationClass) {
        return getClass(joinPoint).getAnnotation(annotationClass);
    }

    @SneakyThrows
    default Object doProceed(ProceedingJoinPoint joinPoint) {
        return joinPoint.proceed();
    }

}

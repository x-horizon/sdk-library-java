package cn.srd.itcp.sugar.component.lock.redis.common.support;

import cn.hutool.core.util.IdUtil;
import cn.srd.itcp.sugar.component.lock.redis.common.core.RedisFairLock;
import cn.srd.itcp.sugar.component.lock.redis.common.core.RedisLockTemplate;
import cn.srd.itcp.sugar.component.lock.redis.common.exception.RedisGenerateLockNameFailedException;
import cn.srd.itcp.sugar.component.lock.redis.common.exception.RedisLockIllegalArgumentException;
import cn.srd.itcp.sugar.framework.spring.tool.common.core.SpringsUtil;
import cn.srd.itcp.sugar.tool.core.ReflectsUtil;
import cn.srd.itcp.sugar.tool.core.asserts.Assert;
import cn.srd.itcp.sugar.tool.core.convert.Converts;
import cn.srd.itcp.sugar.tool.core.object.Objects;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Redis 分布式锁注解切面支持
 *
 * @author wjm
 * @since 2020/12/12 18:06
 */
public class RedisLockAspectSupporter {

    /**
     * instance
     */
    private static RedisLockAspectSupporter instance = null;

    /**
     * instance init
     */
    @PostConstruct
    public void init() {
        instance = this;
    }

    /**
     * get singleton instance
     *
     * @return instance
     */
    public static RedisLockAspectSupporter getInstance() {
        return instance;
    }

    /**
     * {@link RedisLockTemplate} 实现类的缓存，避免每次都要从 Spring 容器中获取
     */
    private static final Map<Class<? extends RedisLockTemplate>, RedisLockTemplate> REDIS_LOCK_TEMPLATE_CACHE = new ConcurrentHashMap<>();

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param lockName               参考 {@link RedisFairLock#lockName()}
     * @param fieldName              参考 {@link RedisFairLock#fieldName()}
     * @param fieldOrder             参考 {@link RedisFairLock#fieldOrder()}
     * @param waitTime               参考 {@link RedisFairLock#waitTime()}
     * @param leaseTime              参考 {@link RedisFairLock#leaseTime()}
     * @param timeUnit               参考 {@link RedisFairLock#timeUnit()}
     * @param redisLockTemplateClass 参考 {@link RedisFairLock#redisLockTemplate()}
     * @param joinPoint              切点
     * @param <T>                    implement by {@link RedisLockTemplate}
     * @return 临界区响应值
     */
    protected <T extends RedisLockTemplate> Object lock(String lockName, String fieldName, int fieldOrder, long waitTime, long leaseTime, TimeUnit timeUnit, Class<T> redisLockTemplateClass, ProceedingJoinPoint joinPoint) {
        return doLock(generateLockName(lockName, fieldName, fieldOrder, joinPoint), waitTime, leaseTime, timeUnit, redisLockTemplateClass, joinPoint);
    }

    /**
     * 生成锁名
     *
     * @param lockName   参考 {@link RedisFairLock#lockName()}
     * @param fieldName  参考 {@link RedisFairLock#fieldName()}
     * @param fieldOrder 参考 {@link RedisFairLock#fieldOrder()}
     * @param joinPoint  切点
     * @return 锁名
     */
    private String generateLockName(String lockName, String fieldName, int fieldOrder, ProceedingJoinPoint joinPoint) {
        if (Objects.isBlank(lockName)) {
            if (Objects.isBlank(fieldName)) {
                return IdUtil.getSnowflake().nextIdStr();
            }
            // 被注解方法的形参列表
            Object[] lockAnnotationMethodParameters = joinPoint.getArgs();
            if (lockAnnotationMethodParameters.length > 0) {
                // 根据 fieldOrder 获取方法形参列表上的第 fieldOrder 个参数，fieldOrder 为 0 时获取第 1 个，为 n 时获取第 n 个；
                Object lockAnnotationMethodParameter = fieldOrder > 0 ? lockAnnotationMethodParameters[fieldOrder - 1] : lockAnnotationMethodParameters[0];
                return Objects.requireNotBlank(
                        new RedisGenerateLockNameFailedException("无法根据给定参数生成锁名：获取到的方法参数上对应的字段值为空，请检查！"),
                        Converts.toStr(ReflectsUtil.getFieldValue(lockAnnotationMethodParameter, fieldName))
                );
            }
        }
        return lockName;
    }

    /**
     * 对临界区完成添加和释放分布式锁的操作
     *
     * @param lockName               参考 {@link RedisFairLock#lockName()}
     * @param waitTime               参考 {@link RedisFairLock#waitTime()}
     * @param leaseTime              参考 {@link RedisFairLock#leaseTime()}
     * @param timeUnit               参考 {@link RedisFairLock#timeUnit()}
     * @param redisLockTemplateClass 参考 {@link RedisFairLock#redisLockTemplate()}
     * @param joinPoint              切点
     * @param <T>                    {@link RedisLockTemplate} 实现类类型
     * @return 临界区响应值
     */
    private <T extends RedisLockTemplate> Object doLock(String lockName, long waitTime, long leaseTime, TimeUnit timeUnit, Class<T> redisLockTemplateClass, ProceedingJoinPoint joinPoint) {
        Assert.INSTANCE.set(new RedisLockIllegalArgumentException("非法的 waitTime 值，请检查！")).throwsIfTrue(waitTime < 0);
        RedisLockTemplate redisLockTemplate = getRedisLockTemplate(redisLockTemplateClass);
        return redisLockTemplate.tryLock(() -> this.proceed(joinPoint), lockName, waitTime, leaseTime, timeUnit);
    }

    /**
     * 获取 {@link RedisLockTemplate} 实现类
     *
     * @param redisLockTemplateClass {@link RedisLockTemplate}类
     * @param <T>                    {@link RedisLockTemplate} 实现类类型
     * @return implement by {@link RedisLockTemplate}
     */
    private <T extends RedisLockTemplate> RedisLockTemplate getRedisLockTemplate(Class<T> redisLockTemplateClass) {
        RedisLockTemplate redisLockTemplate = REDIS_LOCK_TEMPLATE_CACHE.get(redisLockTemplateClass);
        if (Objects.isNull(redisLockTemplate)) {
            redisLockTemplate = SpringsUtil.getBean(redisLockTemplateClass);
            REDIS_LOCK_TEMPLATE_CACHE.put(redisLockTemplateClass, redisLockTemplate);
        }
        return redisLockTemplate;
    }

    /**
     * 执行临界区
     *
     * @param joinPoint 切点
     * @return 切面响应值
     */
    @SneakyThrows
    private Object proceed(ProceedingJoinPoint joinPoint) {
        return joinPoint.proceed();
    }

}

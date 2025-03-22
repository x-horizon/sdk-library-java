package org.horizon.sdk.library.java.cache.all;

import org.horizon.sdk.library.java.cache.all.manager.Cache;
import org.horizon.sdk.library.java.cache.all.model.enums.CacheMode;
import org.horizon.sdk.library.java.cache.all.model.enums.CacheType;
import org.horizon.sdk.library.java.cache.all.strategy.CacheDefaultKeyGenerator;
import org.horizon.sdk.library.java.cache.all.strategy.CacheKeyGenerator;
import org.springframework.cache.support.NullValue;

import java.lang.annotation.*;

/**
 * <p>global configuration for following cache operations:</p>
 *
 * <pre>
 * {@link CacheRead}
 * {@link CacheReadAll}
 * {@link CacheWrite}
 * {@link CacheEvict}
 * {@link Caching}
 * </pre>
 *
 * <ol>
 *   <li><p>when use {@link CacheRead}:</p>
 *     <ul>
 *       <li>get cache in {@link #cacheTypes()} declared order</li>
 *       <li>if {@link #cacheTypes()} size is 1 or hit first-level cache:
 *         <ul>
 *           <li>return cache value (may be {@code null} or {@code NullValue} or actual value)</li>
 *         </ul>
 *       </li>
 *       <li>if cannot hit first-level cache and {@link #cacheTypes()} size > 1:
 *         <ul>
 *           <li>use local/distributed lock based on current cache type</li>
 *           <li>continue getting cache in {@link #cacheTypes()} order</li>
 *           <li>first non-null value will be set to all caches and lock released</li>
 *           <li>if all caches miss:
 *             <ul>
 *               <li>execute pointcut and return its value</li>
 *               <li>if null and {@link #allowEmptyValue()} is true: set {@code NullValue} to all caches</li>
 *               <li>caller always receives {@code null} (whether {@code null} or {@code NullValue})</li>
 *             </ul>
 *           </li>
 *         </ul>
 *       </li>
 *     </ul>
 *   </li>
 *
 *   <li><p>when use {@link CacheWrite}:</p>
 *     <ul>
 *       <li>execute pointcut first</li>
 *       <li>delete all caches</li>
 *     </ul>
 *   </li>
 *
 *   <li><p>when use {@link CacheEvict}:</p>
 *     <ul>
 *       <li>execute pointcut first</li>
 *       <li>delete all caches</li>
 *     </ul>
 *   </li>
 *
 *   <li><p>when use {@link Caching}:</p>
 *     <ul>
 *       <li>execute batch {@link CacheRead} first</li>
 *       <li>then execute batch {@link CacheWrite}</li>
 *       <li>finally execute batch {@link CacheEvict}</li>
 *     </ul>
 *   </li>
 * </ol>
 *
 * @author wjm
 * @since 2023-06-08 10:14
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheConfig {

    /**
     * <p>Specifies cache namespace with following characteristics:</p>
     *
     * <ol>
     *   <li>one namespace represents a single {@link Cache} instance</li>
     *   <li>in local cache type: uses key name directly as cache key</li>
     *   <li>in distributed cache type: combines namespace and key name as cache key
     *       (e.g. {@code "my-cache:1"})</li>
     * </ol>
     *
     * <p>namespace inheritance rules:</p>
     * <ul>
     *   <li>applies to all methods in classes annotated with {@link CacheConfig}</li>
     *   <li>methods without explicit namespace declaration inherit class-level namespace</li>
     *   <li>method-level namespace declarations override class-level namespace</li>
     * </ul>
     *
     * @return the cache namespace
     */
    String[] namespaces() default {};

    /**
     * <p>Configures multi-level cache with specified execution order. Example:</p>
     *
     * <pre>{@code
     * cacheTypes = {{@link CacheType#MAP}, {@link CacheType#REDIS}}
     * }</pre>
     *
     * <ol>
     *   <li><p>With @{@link CacheRead}:</p>
     *     <ul>
     *       <li><b>Read order:</b> map → redis → method</li>
     *       <li>Stops at first successful cache hit</li>
     *       <li><b>Write-back order:</b> redis → map (on cache miss)</li>
     *     </ul>
     *   </li>
     *
     *   <li><p>With @{@link CacheWrite}:</p>
     *     <ul>
     *       <li>Deletes all caches in order: redis → map</li>
     *     </ul>
     *   </li>
     *
     *   <li><p>With @{@link CacheEvict}:</p>
     *     <ul>
     *       <li>Evicts from lowest to highest level: redis → map</li>
     *       <li>Prevents short-term cache pollution between levels</li>
     *     </ul>
     *   </li>
     * </ol>
     *
     * <p>inheritance rules:</p>
     * <ul>
     *   <li>applies to {@link CacheConfig}-annotated classes</li>
     *   <li>method-level configuration overrides class-level</li>
     * </ul>
     *
     * @return ordered cache types
     */
    CacheType[] cacheTypes() default {};

    /**
     * <p>Specifies cache operation mode. Key considerations:</p>
     *
     * <ul>
     *   <li>When using {@link CacheReadAll}, <b>must</b> set mode to {@code READ_WRITE}:
     *     <ul>
     *       <li>read-only mode ({@code READ_ONLY}) causes cache deletion during updates</li>
     *       <li>subsequent cache reads may return incorrect values in read-only mode</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * <p>inheritance rules:</p>
     * <ul>
     *   <li>applies to all methods in {@link CacheConfig}-annotated classes</li>
     *   <li>method-level configuration overrides class-level settings</li>
     * </ul>
     *
     * @return the configured cache mode
     * @see CacheMode
     */
    CacheMode cacheMode() default CacheMode.READ_ONLY;

    /**
     * <p>configures cache key generation strategy:</p>
     *
     * <ul>
     *   <li>when {@code cacheKey} is not empty: uses specified key directly</li>
     *   <li>when {@code cacheKey} is empty: uses {@link #keyGenerator()} to generate key</li>
     * </ul>
     *
     * <p>inheritance rules:</p>
     * <ul>
     *   <li>applies to all methods in {@link CacheConfig}-annotated classes</li>
     *   <li>method-level configuration overrides class-level when:
     *     <ul>
     *       <li>method explicitly specifies key generator</li>
     *       <li>method's generator is not {@link CacheDefaultKeyGenerator}</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * @return the configured key generator
     */
    Class<? extends CacheKeyGenerator> keyGenerator() default CacheDefaultKeyGenerator.class;

    /**
     * <p>controls null value caching behavior:</p>
     *
     * <ul>
     *   <li>when {@code true}: stores {@link NullValue} in cache when:
     *     <ul>
     *       <li>{@link CacheRead} annotated method returns {@code null}</li>
     *     </ul>
     *   </li>
     *   <li>when {@code false}: prevents null value caching</li>
     * </ul>
     *
     * <p>security implications:</p>
     * <ul>
     *   <li>prevents cache penetration by serving cached {@code NullValue}</li>
     *   <li>null results will bypass method execution</li>
     * </ul>
     *
     * <p>critical constraints:</p>
     * <ul>
     *   <li>namespace-level configuration lock:
     *     <ul>
     *       <li>must maintain consistent values for the same namespace</li>
     *       <li>configuration becomes immutable after {@link Cache} instance initialization</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * <p>inheritance rules:</p>
     * <ul>
     *   <li>applies to all methods in {@link CacheConfig}-annotated classes</li>
     *   <li>identical configuration enforcement for same namespace</li>
     * </ul>
     *
     * @return allow or not to set a {@link NullValue} in cache
     */
    boolean allowEmptyValue() default false;

}
package cn.srd.itcp.sugar.cache.all.support.strategy;

import java.lang.reflect.Parameter;

/**
 * @author wjm
 * @since 2023-06-10 11:42:19
 */
public interface CacheKeyGenerator {

    Class<? extends CacheKeyGenerator> DEFAULT_KEY_GENERATOR = CacheDefaultKeyGenerator.class;

    /**
     * Generate a key for the given method and its parameters.
     *
     * @param target the target instance
     * @param method the method being called
     * @param params the method parameters (with any var-args expanded)
     * @return a generated key
     */
    String generate(Parameter[] parameters, Object[] parameterValues);

}



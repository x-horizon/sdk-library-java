package cn.srd.sugar.cache.all.support.strategy;

import java.lang.reflect.Parameter;

/**
 * the key generator
 *
 * @author wjm
 * @since 2023-06-10 11:42:19
 */
public interface CacheKeyGenerator {

    /**
     * the default key generator
     */
    Class<? extends CacheKeyGenerator> DEFAULT_KEY_GENERATOR = CacheDefaultKeyGenerator.class;

    /**
     * generate a key for the given method and its parameters.
     *
     * @param parameters      the method parameters
     * @param parameterValues the method parameter values
     * @return the generated key
     */
    String generate(Parameter[] parameters, Object[] parameterValues);

}



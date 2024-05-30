package cn.srd.library.java.cache.all.strategy;

/**
 * the key generator
 *
 * @author wjm
 * @since 2023-06-10 11:42
 */
public interface CacheKeyGenerator {

    /**
     * the default key generator
     */
    Class<? extends CacheKeyGenerator> DEFAULT_KEY_GENERATOR = CacheDefaultKeyGenerator.class;

    /**
     * generate a key for the given method and its parameter names.
     *
     * @param parameterNames  the method parameter names
     * @param parameterValues the method parameter values
     * @return the generated key
     */
    String generate(String[] parameterNames, Object[] parameterValues);

}
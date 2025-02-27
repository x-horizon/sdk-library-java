package org.horizon.sdk.library.java.cache.all.strategy;

import org.springframework.cache.interceptor.SimpleKeyGenerator;

/**
 * the key generator in default
 *
 * @author wjm
 * @since 2023-06-10 11:42
 */
public class CacheDefaultKeyGenerator implements CacheKeyGenerator {

    @Override
    public String generate(String[] parameterNames, Object[] parameterValues) {
        return SimpleKeyGenerator.generateKey(parameterValues).toString();
    }

}
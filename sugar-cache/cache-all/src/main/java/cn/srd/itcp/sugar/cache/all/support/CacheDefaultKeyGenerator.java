package cn.srd.itcp.sugar.cache.all.support;

import org.springframework.cache.interceptor.SimpleKeyGenerator;

import java.lang.reflect.Parameter;

/**
 * @author wjm
 * @since 2023-06-10 11:42:19
 */
public class CacheDefaultKeyGenerator implements CacheKeyGenerator {

    @Override
    public String generate(Parameter[] parameters, Object[] parameterValues) {
        return SimpleKeyGenerator.generateKey(parameterValues).toString();
    }
}



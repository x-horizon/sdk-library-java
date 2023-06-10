package cn.srd.itcp.sugar.cache.all.support;

import cn.srd.itcp.sugar.cache.all.support.strategy.CacheKeyGenerator;

import java.lang.reflect.Parameter;

public class CacheTestKeyGenerator implements CacheKeyGenerator {

    @Override
    public String generate(Parameter[] parameters, Object[] parameterValues) {
        return null;
    }
    
}



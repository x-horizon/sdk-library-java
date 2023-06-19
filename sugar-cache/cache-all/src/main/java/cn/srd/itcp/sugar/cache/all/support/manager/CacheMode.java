package cn.srd.itcp.sugar.cache.all.support.manager;

/**
 * the cache mode
 *
 * @author wjm
 * @since 2023-06-19 21:26:47
 */
public enum CacheMode {

    /**
     * when use this mode, will never update cache value
     */
    READ_ONLY,

    /**
     * when use this mode, will update cache value
     */
    READ_WRITE,

    ;

}



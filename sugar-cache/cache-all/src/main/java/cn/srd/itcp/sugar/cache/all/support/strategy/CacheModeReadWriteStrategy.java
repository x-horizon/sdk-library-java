package cn.srd.itcp.sugar.cache.all.support.strategy;

/**
 * the read write cache mode strategy
 *
 * @author wjm
 * @since 2023-06-19 21:26:47
 */
public class CacheModeReadWriteStrategy implements CacheModeStrategy {

    /**
     * the singleton instance
     */
    private static final CacheModeReadWriteStrategy INSTANCE = new CacheModeReadWriteStrategy();

    /**
     * get singleton instance
     *
     * @return singleton instance
     */
    public static CacheModeReadWriteStrategy getInstance() {
        return INSTANCE;
    }

}



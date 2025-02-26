package org.horizon.library.java.cache.all.strategy;

/**
 * the read only cache mode strategy
 *
 * @author wjm
 * @since 2023-06-19 21:26
 */
public class CacheModeReadOnlyStrategy implements CacheModeStrategy {

    /**
     * the singleton instance
     */
    private static final CacheModeReadOnlyStrategy INSTANCE = new CacheModeReadOnlyStrategy();

    /**
     * get singleton instance
     *
     * @return singleton instance
     */
    public static CacheModeReadOnlyStrategy getInstance() {
        return INSTANCE;
    }

}
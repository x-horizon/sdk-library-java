package cn.srd.itcp.sugar.cache.all.support.strategy;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * the local cache mode strategy implement
 *
 * @author wjm
 * @since 2023-06-12 20:49:21
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CacheModeLocalStrategy implements CacheModeStrategy {

    /**
     * the singleton instance
     */
    private static final CacheModeLocalStrategy INSTANCE = new CacheModeLocalStrategy();

    /**
     * get singleton instance
     *
     * @return singleton instance
     */
    public static CacheModeLocalStrategy getInstance() {
        return INSTANCE;
    }

}



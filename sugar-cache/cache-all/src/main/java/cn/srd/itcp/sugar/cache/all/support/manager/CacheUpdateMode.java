// package cn.srd.itcp.sugar.cache.all.support.manager;
//
// import cn.srd.itcp.sugar.cache.all.support.strategy.CacheModeDistributedStrategy;
// import cn.srd.itcp.sugar.cache.all.support.strategy.CacheModeLocalStrategy;
// import cn.srd.itcp.sugar.cache.all.support.strategy.CacheModeStrategy;
// import lombok.AllArgsConstructor;
// import lombok.Getter;
//
// /**
//  * the cache update mode
//  *
//  * @author wjm
//  * @since 2023-06-19 21:26:47
//  */
// @Getter
// @AllArgsConstructor
// public enum CacheUpdateMode {
//
//     /**
//      * use cache by map
//      */
//     UPDATE(CacheModeLocalStrategy.getInstance()),
//
//     /**
//      * use cache by caffeine
//      */
//     DELETE(CacheModeLocalStrategy.getInstance()),
//
//     /**
//      * use cache by redis
//      */
//     REDIS(CacheModeDistributedStrategy.getInstance()),
//
//     ;
//
//     /**
//      * the cache mode strategy
//      */
//     private final CacheModeStrategy strategy;
//
// }
//
//

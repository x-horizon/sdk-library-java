// package cn.commons.redisson.core.support;
//
// import cn.commons.redisson.core.RedissonFairLockHandler;
// import cn.commons.redisson.core.RedissonNonFairLockHandler;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
//
// /**
//  * 自动装配该模块用到的工具
//  *
//  * @author wjm
//  * @date 2020/12/12 18:06
//  */
// @Configuration
// public class RedissonAutoConfiguration {
//
//     @Bean
//     public RedissonFairLockHandler redissonFairLockHandler() {
//         return RedissonFairLockHandler.getInstance();
//     }
//
//     @Bean
//     public RedissonNonFairLockHandler redissonNonFairLockHandler() {
//         return RedissonNonFairLockHandler.getInstance();
//     }
//
//     @Bean
//     public RedissonFairLockAspect redissonFairLockAspect() {
//         return RedissonFairLockAspect.getInstance();
//     }
//
//     @Bean
//     public RedissonNonFairLockAspect redissonNonFairLockAspect() {
//         return RedissonNonFairLockAspect.getInstance();
//     }
//
//     @Bean
//     public RedissonLockAspectSupporter redissonLockAspectSupporter() {
//         return RedissonLockAspectSupporter.getInstance();
//     }
//
// }

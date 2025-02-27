package org.horizon.sdk.library.java.cache.caffeine.model.property;

import org.horizon.sdk.library.java.contract.constant.java.JavaObjectReferenceLevel;
import org.horizon.sdk.library.java.tool.lang.time.TimeUnitHandler;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties for Library Cache Caffeine
 * TODO wjm optimize the prefix path like {@link com.github.benmanes.caffeine.cache.CaffeineSpec#configure(String, String)}
 *
 * @author wjm
 * @since 2023-06-05 17:01
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "library-java.cache.caffeine")
public class CaffeineCacheProperty {

    /**
     * instance
     */
    @Getter private static CaffeineCacheProperty instance = null;

    /**
     * instance init
     */
    @PostConstruct
    public void initialize() {
        instance = this;
    }

    /**
     * 初始缓存空间大小
     */
    private int initialCapacity = 256;

    /**
     * 用于限制缓存大小的参数：缓存最大条数，超过则失效之前放入的缓存，与 {@link #maximumWeight} 不可同时配置
     */
    private long maximumSize;

    /**
     * 用于限制缓存大小的参数：缓存最大权重，与 {@link #maximumSize} 不可同时配置
     */
    private long maximumWeight;

    /**
     * 最后一次“访问”某条缓存后，经过此时间后过期，可指定为 “2s”、“200ms” 等，see {@link TimeUnitHandler}
     */
    private String expireAfterAccess;

    /**
     * 最后一次“写入”某条缓存后，经过此时间后过期，可指定为 “2s”、“200ms” 等，see {@link TimeUnitHandler}
     */
    private String expireAfterWrite;

    /**
     * “写入”某条缓存后，若经过此时间没有更新该条缓存，则在下一次获取该缓存时，异步刷新缓存，如果新的缓存值还未加载时，返回旧值（可指定为 “2s”、“200ms” 等，see {@link TimeUnitHandler}
     */
    private String refreshAfterWrite;

    /**
     * 缓存 - key 引用类型，可指定为 {@link JavaObjectReferenceLevel#STRONG} / {@link JavaObjectReferenceLevel#WEAK} 或不指定，默认为 {@link JavaObjectReferenceLevel#STRONG}
     */
    private JavaObjectReferenceLevel keyReferenceLevel;

    /**
     * 缓存 - value 引用类型，可指定为 {@link JavaObjectReferenceLevel#STRONG} / {@link JavaObjectReferenceLevel#SOFT} / {@link JavaObjectReferenceLevel#WEAK} 或不指定，默认为 {@link JavaObjectReferenceLevel#STRONG}
     */
    private JavaObjectReferenceLevel valueReferenceLevel;

}
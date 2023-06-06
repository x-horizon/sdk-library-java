package cn.srd.itcp.sugar.cache.caffeine.config.properties;

import cn.srd.itcp.sugar.tool.constant.JavaObjectReferenceLevel;
import cn.srd.itcp.sugar.tool.core.time.TimeUnitHandler;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties for Sugar Cache Caffeine
 *
 * @author wjm
 * @since 2023-06-05 17:01:12
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "sugar.cache.caffeine")
public class CaffeineCacheProperties {

    /**
     * instance
     */
    private static CaffeineCacheProperties instance = null;

    /**
     * instance init
     */
    @PostConstruct
    public void init() {
        instance = this;
    }

    /**
     * get singleton instance
     *
     * @return instance
     */
    public static CaffeineCacheProperties getInstance() {
        return instance;
    }

    /**
     * 初始缓存空间大小
     */
    private int initialCapacity = 16;

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

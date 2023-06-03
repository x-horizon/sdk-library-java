package cn.srd.itcp.sugar.cache.all.test.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.NUMBER)
public enum CacheOperation {

    /**
     * 清除 key
     */
    EVICT,

    /**
     * 批量清除
     */
    EVICT_BATCH

}

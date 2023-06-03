package cn.srd.itcp.sugar.cache.all.test.support;

import cn.srd.itcp.sugar.cache.all.test.enums.CacheOperation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CacheMessage implements Serializable {

    @Serial private static final long serialVersionUID = -6935671630692135123L;

    private Object serverId;

    private String cacheName;

    private CacheOperation operation;

    private Object key;

}

package cn.srd.itcp.sugar.tool.core.object;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * an object to define what is null
 *
 * @author wjm
 * @since 2023-06-14 8:49:19
 */
public interface NullableObject extends Serializable {

    /**
     * is it null
     *
     * @return is it null
     */
    @JsonIgnore
    boolean isNull();

    /**
     * is it not null
     *
     * @return is it not null
     */
    @JsonIgnore
    default boolean isNotNull() {
        return !isNull();
    }

}

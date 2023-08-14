package cn.srd.library.java.tool.lang.core.object;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * an object to define what is null
 *
 * @author wjm
 * @since 2023-06-14 08:49:19
 */
public interface NullableObject {

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

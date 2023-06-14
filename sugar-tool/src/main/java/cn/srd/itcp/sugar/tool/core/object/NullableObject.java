package cn.srd.itcp.sugar.tool.core.object;

/**
 * an object to define what is null
 *
 * @author wjm
 * @since 2023-06-14 8:49:19
 */
public interface NullableObject {

    /**
     * is it null
     *
     * @return is it null
     */
    boolean isNull();

    /**
     * is it not null
     *
     * @return is it not null
     */
    default boolean isNotNull() {
        return !isNull();
    }

}

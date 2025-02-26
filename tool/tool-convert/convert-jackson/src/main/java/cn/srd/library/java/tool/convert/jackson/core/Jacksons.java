package cn.srd.library.java.tool.convert.jackson.core;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * jackson util
 *
 * @author wjm
 * @since 2023-06-17 09:37:17
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Jacksons {

    /**
     * new {@link TypeReference}
     *
     * @param <T> the {@link TypeReference} type
     * @return {@link TypeReference}
     */
    public static <T> TypeReference<T> newTypeReference() {
        return new TypeReference<>() {
        };
    }

}


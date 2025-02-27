package org.horizon.sdk.library.java.contract.constant.enums;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * enum constant
 *
 * @author wjm
 * @since 2023-09-19 19:44
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnumConstant {

    /**
     * enum internal field name
     */
    public static final Set<String> INTERNAL_FIELD_NAMES = Set.of("ENUM$VALUES", "$VALUES", "name", "ordinal", "hash");

}
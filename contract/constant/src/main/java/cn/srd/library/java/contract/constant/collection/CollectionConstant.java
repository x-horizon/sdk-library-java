// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.contract.constant.collection;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * collection constant
 *
 * @author wjm
 * @since 2023-09-19 19:44
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CollectionConstant {

    /**
     * the first index: {@code 0}
     */
    public static final int FIRST_INDEX = 0;

    /**
     * the second index: {@code 1}
     */
    public static final int SECOND_INDEX = 1;

    /**
     * the third index: {@code 2}
     */
    public static final int THIRD_INDEX = 1;

    /**
     * not found index: {@code -1}
     */
    public static final int NOT_FOUND_INDEX = -1;

    /**
     * the empty capacity: {@code 0}
     */
    public static final int EMPTY_CAPACITY = 0;

    /**
     * default map initial capacity: {@code 16}
     */
    public static final int DEFAULT_MAP_INITIAL_CAPACITY = 16;

    /**
     * define an empty array string: {@code "[]"}
     */
    public static final String EMPTY_ARRAY_STRING = "[]";

    /**
     * define an empty map string: {@code "{}"}
     */
    public static final String EMPTY_MAP_STRING = "{}";

    /**
     * default enable stream parallel
     */
    public static final boolean DEFAULT_ENABLE_STREAM_PARALLEL = false;

}

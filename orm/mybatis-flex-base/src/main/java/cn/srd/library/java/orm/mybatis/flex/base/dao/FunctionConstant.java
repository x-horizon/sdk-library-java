// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.dao;

import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.FlexConsts;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author wjm
 * @since 2023-11-26 01:30
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FunctionConstant {

    static final String SAVE_BATCH_FUNCTION_NAME = FlexConsts.METHOD_INSERT_BATCH;

    static final String SAVE_FUNCTION_NAME = "insert";

    static final String SAVE_WITH_PK_FUNCTION_NAME = "insertWithPk";

    /**
     * see <a href="https://mybatis-flex.com/zh/base/batch.html#basemapper-insertbatch-%E6%96%B9%E6%B3%95">"the batch operation core guide"</a>.
     */
    static final int SMALL_BATCH_OPERATION_SIZE = 100;

    static final int DEFAULT_BATCH_OPERATION_SIZE_EACH_TIME = BaseMapper.DEFAULT_BATCH_SIZE;

}

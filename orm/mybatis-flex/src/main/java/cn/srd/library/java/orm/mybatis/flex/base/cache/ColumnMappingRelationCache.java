// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.cache;

import com.mybatisflex.annotation.Column;

import java.lang.annotation.Annotation;

/**
 * @author wjm
 * @since 2023-11-07 15:57
 */
public abstract class ColumnMappingRelationCache extends cn.library.java.orm.mybatis.contract.base.cache.ColumnMappingRelationCache {

    @Override
    protected Class<? extends Annotation> getTypeHandlerLocatedAnnotation() {
        return Column.class;
    }

}

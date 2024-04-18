// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.postgresql.dao;

import cn.srd.library.java.contract.component.database.postgresql.PostgresqlJsonbSQLs;
import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.orm.contract.constant.GenericColumnName;
import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.orm.mybatis.flex.base.adapter.MybatisFlexAdapter;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.query.RawQueryCondition;

import java.util.List;

/**
 * the generic dao
 *
 * @param <P> the entity extends {@link PO}
 * @author wjm
 * @since 2024-04-18 21:54
 */
public interface GenericDao<P extends PO> extends cn.srd.library.java.orm.mybatis.flex.base.dao.GenericDao<P> {

    default List<P> listLikeByName(String name) {
        return getBaseMapper().selectListByCondition(new RawQueryCondition(PostgresqlJsonbSQLs.getListStringLike(GenericColumnName.NAME, name)));
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    private BaseMapper<P> getBaseMapper() {
        return (BaseMapper<P>) MybatisFlexAdapter.getInstance().getBaseMapper(this.getClass());
    }

}
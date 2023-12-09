// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.dao;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.orm.contract.model.base.PO;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.mybatisflex.core.FlexConsts;
import com.mybatisflex.core.provider.EntitySqlProvider;
import com.mybatisflex.core.query.QueryWrapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@CanIgnoreReturnValue
@SuppressWarnings(SuppressWarningConstant.ALL)
public interface GenericCurdDao2<T extends PO> {

    @SelectProvider(type = EntitySqlProvider.class, method = "selectListByQuery")
    List<T> selectListByQuery(@Param(FlexConsts.QUERY) QueryWrapper queryWrapper);

}

// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.postgresql.repository;

import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.orm.contract.model.base.POJO;
import cn.srd.library.java.orm.mybatis.flex.base.cache.MybatisFlexSystemCache;
import cn.srd.library.java.orm.mybatis.flex.base.cache.MybatisFlexSystemCacheDTO;
import cn.srd.library.java.orm.mybatis.flex.base.chain.QueryChain;
import cn.srd.library.java.orm.mybatis.flex.postgresql.chain.JsonbQueryChainer;
import cn.srd.library.java.orm.mybatis.flex.postgresql.chain.NormalQueryChainer;
import com.mybatisflex.core.BaseMapper;

/**
 * the generic repository
 *
 * @param <P> the entity extends {@link PO}
 * @author wjm
 * @since 2024-04-18 21:54
 */
public interface GenericRepository<P extends PO> extends cn.srd.library.java.orm.mybatis.flex.base.repository.GenericRepository<P> {

    default <PJ extends POJO, R extends GenericRepository<P>> NormalQueryChainer<P, PJ> openNormalQuery() {
        BaseMapper<P> baseMapper = getBaseMapper();
        MybatisFlexSystemCacheDTO<P, R> systemCache = MybatisFlexSystemCache.getInstance().getByRepositoryProxyClass(this.getClass());
        String tableName = systemCache.getTableName();
        Class<P> poClass = systemCache.getPoClass();
        return new NormalQueryChainer<>(QueryChain.of(baseMapper), tableName, poClass);
    }

    default <PJ extends POJO, R extends GenericRepository<P>> JsonbQueryChainer<P, PJ> openJsonbQuery() {
        // warm up first to cache the proxy class
        this.getBaseMapper();
        MybatisFlexSystemCacheDTO<P, R> systemCache = MybatisFlexSystemCache.getInstance().getByRepositoryProxyClass(this.getClass());
        String tableName = systemCache.getTableName();
        Class<P> poClass = systemCache.getPoClass();
        return new JsonbQueryChainer<>(openNormalQuery(), tableName, poClass);
    }

}
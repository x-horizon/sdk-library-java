package org.horizon.library.java.orm.mybatis.flex.postgresql.repository;

import org.horizon.library.java.contract.model.base.PO;
import org.horizon.library.java.contract.model.base.POJO;
import org.horizon.library.java.orm.mybatis.flex.base.cache.MybatisFlexSystemCache;
import org.horizon.library.java.orm.mybatis.flex.base.cache.MybatisFlexSystemCacheDTO;
import org.horizon.library.java.orm.mybatis.flex.base.chain.QueryChain;
import org.horizon.library.java.orm.mybatis.flex.postgresql.chain.JsonbQueryChainer;
import org.horizon.library.java.orm.mybatis.flex.postgresql.chain.NormalQueryChainer;
import com.mybatisflex.core.BaseMapper;

/**
 * the generic repository
 *
 * @param <P> the entity extends {@link PO}
 * @author wjm
 * @since 2024-04-18 21:54
 */
public interface GenericRepository<P extends PO> extends org.horizon.library.java.orm.mybatis.flex.base.repository.GenericRepository<P> {

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
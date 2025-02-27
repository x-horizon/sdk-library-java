package org.horizon.sdk.library.java.orm.mybatis.flex.base.chain;

import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.adapter.QueryWrapperAdapter;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.mybatis.Mappers;
import com.mybatisflex.core.query.CPI;
import com.mybatisflex.core.query.MapperQueryChain;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.table.TableInfo;
import com.mybatisflex.core.table.TableInfoFactory;

/**
 * @author wjm
 * @since 2024-04-20 15:08
 */
@SuppressWarnings(SuppressWarningConstant.SERIAL)
public class QueryChain<T> extends QueryWrapperAdapter<QueryChain<T>> implements MapperQueryChain<T> {

    private final BaseMapper<T> baseMapper;

    public QueryChain(BaseMapper<T> baseMapper) {
        this.baseMapper = baseMapper;
    }

    public static <T> QueryChain<T> of(Class<T> entityClass) {
        BaseMapper<T> baseMapper = Mappers.ofEntityClass(entityClass);
        return new QueryChain<>(baseMapper);
    }

    public static <E> QueryChain<E> of(BaseMapper<E> baseMapper) {
        return new QueryChain<>(baseMapper);
    }

    @Override
    public BaseMapper<T> baseMapper() {
        return baseMapper;
    }

    @Override
    public QueryWrapper toQueryWrapper() {
        return this;
    }

    @Override
    public String toSQL() {
        TableInfo tableInfo = TableInfoFactory.ofMapperClass(baseMapper.getClass());
        CPI.setFromIfNecessary(this, tableInfo.getSchema(), tableInfo.getTableName());
        return super.toSQL();
    }

    public String toSQLIgnoreTable() {
        return super.toSQL();
    }

}
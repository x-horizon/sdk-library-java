package org.horizon.library.java.orm.mybatis.flex.base.chain;

import org.horizon.library.java.contract.constant.page.PageConstant;
import org.horizon.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.library.java.contract.model.base.PO;
import org.horizon.library.java.contract.model.base.POJO;
import org.horizon.library.java.contract.model.base.VO;
import org.horizon.library.java.orm.contract.model.page.PageParam;
import org.horizon.library.java.orm.contract.model.page.PageResult;
import org.horizon.library.java.orm.mybatis.flex.base.converter.PageConverter;
import org.horizon.library.java.orm.mybatis.flex.base.support.ColumnNameGetter;
import com.mybatisflex.core.logicdelete.LogicDeleteManager;
import com.mybatisflex.core.paginate.Page;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;

/**
 * @param <P> the entity extends {@link PO}
 * @author wjm
 * @since 2023-11-28 22:57
 */
@Getter(AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class QueryChainer<P extends PO> extends BaseQueryChainer<P> {

    @Getter(AccessLevel.PROTECTED) private final QueryChain<P> nativeQueryChain;

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public <P1 extends PO> QueryChainer<P> select(ColumnNameGetter<P1>... columnNameGetters) {
        getNativeQueryChain().select(columnNameGetters);
        return this;
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public <P1 extends PO> QueryJoiner<P, ? extends QueryChainer<P>> innerJoin(Class<P1> entityClass) {
        return innerJoin(entityClass, true);
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public <P1 extends PO> QueryJoiner<P, ? extends QueryChainer<P>> innerJoin(Class<P1> entityClass, BooleanSupplier condition) {
        return innerJoin(entityClass, condition.getAsBoolean());
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public <P1 extends PO> QueryJoiner<P, ? extends QueryChainer<P>> innerJoin(Class<P1> entityClass, boolean condition) {
        return new QueryJoiner<>(getNativeQueryChain().innerJoin(entityClass, condition), this);
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public <P1 extends PO> QueryJoiner<P, ? extends QueryChainer<P>> leftJoin(Class<P1> entityClass) {
        return leftJoin(entityClass, true);
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public <P1 extends PO> QueryJoiner<P, ? extends QueryChainer<P>> leftJoin(Class<P1> entityClass, BooleanSupplier condition) {
        return leftJoin(entityClass, condition.getAsBoolean());
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public <P1 extends PO> QueryJoiner<P, ? extends QueryChainer<P>> leftJoin(Class<P1> entityClass, boolean condition) {
        return new QueryJoiner<>(getNativeQueryChain().leftJoin(entityClass, condition), this);
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public <P1 extends PO> QueryJoiner<P, ? extends QueryChainer<P>> rightJoin(Class<P1> entityClass) {
        return rightJoin(entityClass, true);
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public <P1 extends PO> QueryJoiner<P, ? extends QueryChainer<P>> rightJoin(Class<P1> entityClass, BooleanSupplier condition) {
        return rightJoin(entityClass, condition.getAsBoolean());
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public <P1 extends PO> QueryJoiner<P, ? extends QueryChainer<P>> rightJoin(Class<P1> entityClass, boolean condition) {
        return new QueryJoiner<>(getNativeQueryChain().rightJoin(entityClass, condition), this);
    }

    // TODO wjm 关于 cross join，不是在 cross join table name 后拼接 on 的连接条件的，而是在 where 后拼接表的连接条件，mybatis-flex 目前的实现有 bug，此处先屏蔽 cross join 的相关函数
    // @SuppressWarnings(SuppressWarningConstant.ALL)
    // public <P1 extends PO> QueryJoiner<P, ? extends QueryChainer<P>> crossJoin(Class<P1> entityClass) {
    //     return crossJoin(entityClass, true);
    // }
    // @SuppressWarnings(SuppressWarningConstant.ALL)
    // public <P1 extends PO> QueryJoiner<P, ? extends QueryChainer<P>> crossJoin(Class<P1> entityClass, BooleanSupplier condition) {
    //     return crossJoin(entityClass, condition.getAsBoolean());
    // }
    // @SuppressWarnings(SuppressWarningConstant.ALL)
    // public <P1 extends PO> QueryJoiner<P, ? extends QueryChainer<P>> crossJoin(Class<P1> entityClass, boolean condition) {
    //     return new QueryJoiner<>(getNativeQueryChainer().crossJoin(entityClass, condition), this);
    // }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public <P1 extends PO> QueryJoiner<P, ? extends QueryChainer<P>> fullJoin(Class<P1> entityClass) {
        return fullJoin(entityClass, true);
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public <P1 extends PO> QueryJoiner<P, ? extends QueryChainer<P>> fullJoin(Class<P1> entityClass, BooleanSupplier condition) {
        return fullJoin(entityClass, condition.getAsBoolean());
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public <P1 extends PO> QueryJoiner<P, ? extends QueryChainer<P>> fullJoin(Class<P1> entityClass, boolean condition) {
        return new QueryJoiner<>(getNativeQueryChain().fullJoin(entityClass, condition), this);
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public <PJ extends POJO> QueryConditional<? extends QueryChainer<P>, QueryChain<P>> where(ColumnNameGetter<PJ> columnNameGetter) {
        return new QueryConditional<>(this, getNativeQueryChain().where(columnNameGetter));
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public <PJ extends POJO> QueryConditional<? extends QueryChainer<P>, QueryChain<P>> and(ColumnNameGetter<PJ> columnNameGetter) {
        return new QueryConditional<>(this, getNativeQueryChain().and(columnNameGetter));
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public <PJ extends POJO> QueryConditional<? extends QueryChainer<P>, QueryChain<P>> or(ColumnNameGetter<PJ> columnNameGetter) {
        return new QueryConditional<>(this, getNativeQueryChain().or(columnNameGetter));
    }

    public QueryChainer<P> as(String aliasName) {
        getNativeQueryChain().as(aliasName);
        return this;
    }

    @SafeVarargs
    public final <P1 extends PO> QueryChainer<P> groupBy(ColumnNameGetter<P1>... columnNameGetters) {
        getNativeQueryChain().groupBy(columnNameGetters);
        return this;
    }

    @SafeVarargs
    public final <P1 extends PO> QueryChainer<P> orderByAsc(ColumnNameGetter<P1>... columnNameGetters) {
        for (ColumnNameGetter<P1> columnNameGetter : columnNameGetters) {
            getNativeQueryChain().orderBy(columnNameGetter, true);
        }
        return this;
    }

    @SafeVarargs
    public final <P1 extends PO> QueryChainer<P> orderByDesc(ColumnNameGetter<P1>... columnNameGetters) {
        for (ColumnNameGetter<P1> columnNameGetter : columnNameGetters) {
            getNativeQueryChain().orderBy(columnNameGetter, false);
        }
        return this;
    }

    public QueryChainer<P> ignoreLogicDelete() {
        this.needToIgnoreLogicDelete = true;
        return this;
    }

    public Optional<P> get() {
        return this.needToIgnoreLogicDelete ?
                LogicDeleteManager.execWithoutLogicDelete(this::doGet) :
                doGet();
    }

    public <V extends VO> Optional<V> getToVO() {
        return this.needToIgnoreLogicDelete ?
                LogicDeleteManager.execWithoutLogicDelete(() -> doGetToVO()) :
                doGetToVO();
    }

    public List<P> list() {
        return this.needToIgnoreLogicDelete ?
                LogicDeleteManager.execWithoutLogicDelete(this::doList) :
                doList();
    }

    public <V extends VO> List<V> listToVOs() {
        return this.needToIgnoreLogicDelete ?
                LogicDeleteManager.execWithoutLogicDelete(() -> doListToVOs()) :
                doListToVOs();
    }

    public PageResult<P> page() {
        return page(PageConstant.DEFAULT_PAGE_INDEX, PageConstant.DEFAULT_PAGE_SIZE);
    }

    public PageResult<P> page(PageParam pageParam) {
        return page(pageParam.getPageIndex(), pageParam.getPageSize(), pageParam.getTotalNumber());
    }

    public PageResult<P> page(Number pageIndex, Number pageSize) {
        return page(new Page<>(pageIndex, pageSize));
    }

    public PageResult<P> page(Number pageIndex, Number pageSize, Number totalNumber) {
        return page(new Page<>(pageIndex, pageSize, totalNumber));
    }

    private PageResult<P> page(Page<P> page) {
        return this.needToIgnoreLogicDelete ?
                LogicDeleteManager.execWithoutLogicDelete(() -> doPage(page)) :
                doPage(page);
    }

    public <V extends VO> PageResult<V> pageToVO() {
        return pageToVO(PageConstant.DEFAULT_PAGE_INDEX, PageConstant.DEFAULT_PAGE_SIZE);
    }

    public <V extends VO> PageResult<V> pageToVO(PageParam pageParam) {
        return pageToVO(pageParam.getPageIndex(), pageParam.getPageSize(), pageParam.getTotalNumber());
    }

    public <V extends VO> PageResult<V> pageToVO(Number pageIndex, Number pageSize) {
        return pageToVO(new Page<>(pageIndex, pageSize));
    }

    public <V extends VO> PageResult<V> pageToVO(Number pageIndex, Number pageSize, Number totalNumber) {
        return pageToVO(new Page<>(pageIndex, pageSize, totalNumber));
    }

    private <V extends VO> PageResult<V> pageToVO(Page<P> page) {
        return this.needToIgnoreLogicDelete ?
                LogicDeleteManager.execWithoutLogicDelete(() -> doPageToVO(page)) :
                doPageToVO(page);
    }

    public long count() {
        return this.needToIgnoreLogicDelete ?
                LogicDeleteManager.execWithoutLogicDelete(this::doCount) :
                doCount();
    }

    public boolean exists() {
        return this.needToIgnoreLogicDelete ?
                LogicDeleteManager.execWithoutLogicDelete(this::doExists) :
                doExists();
    }

    public boolean notExists() {
        return !exists();
    }

    private Optional<P> doGet() {
        return getNativeQueryChain().oneOpt();
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    private <V extends VO> Optional<V> doGetToVO() {
        Optional<P> po = get();
        if (po.isPresent()) {
            return Optional.ofNullable((V) po.orElseThrow().toVO());
        }
        return Optional.empty();
    }

    private List<P> doList() {
        return getNativeQueryChain().list();
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    private <V extends VO> List<V> doListToVOs() {
        return list().stream().map(po -> (V) po.toVO()).collect(Collectors.toList());
    }

    private PageResult<P> doPage(Page<P> page) {
        return PageConverter.INSTANCE.toPageResult(getNativeQueryChain().page(page));
    }

    private <V extends VO> PageResult<V> doPageToVO(Page<P> page) {
        return PageConverter.INSTANCE.toPageResultVO(getNativeQueryChain().page(page));
    }

    private long doCount() {
        return getNativeQueryChain().count();
    }

    private boolean doExists() {
        return getNativeQueryChain().exists();
    }

    public String toSQL() {
        return getNativeQueryChain().toSQL();
    }

}
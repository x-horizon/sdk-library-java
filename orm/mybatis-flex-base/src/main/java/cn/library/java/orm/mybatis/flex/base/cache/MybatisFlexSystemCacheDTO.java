package cn.library.java.orm.mybatis.flex.base.cache;

import cn.library.java.contract.model.base.PO;
import cn.library.java.orm.mybatis.flex.base.repository.GenericRepository;
import com.mybatisflex.core.BaseMapper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * @author wjm
 * @since 2024-04-26 15:03
 */
@Getter
@Setter(AccessLevel.PACKAGE)
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class MybatisFlexSystemCacheDTO<P extends PO, R extends GenericRepository<P>> {

    private String tableName;

    private Class<P> poClass;

    private Class<R> repositoryClass;

    private Class<R> repositoryProxyClass;

    private BaseMapper<P> baseMapper;

}
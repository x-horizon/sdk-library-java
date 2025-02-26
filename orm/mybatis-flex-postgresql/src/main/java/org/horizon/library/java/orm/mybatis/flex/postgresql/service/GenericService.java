package org.horizon.library.java.orm.mybatis.flex.postgresql.service;

import org.horizon.library.java.contract.model.base.PO;
import org.horizon.library.java.contract.model.base.VO;
import org.horizon.library.java.orm.mybatis.flex.base.repository.GenericRepository;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * the generic service
 *
 * @param <P> the entity extends {@link PO}
 * @param <V> the entity extends {@link VO}
 * @param <R> the repository extends {@link GenericRepository}
 * @author wjm
 * @since 2024-04-18 23:44
 */
@CanIgnoreReturnValue
public class GenericService<P extends PO, V extends VO, R extends GenericRepository<P>> extends org.horizon.library.java.orm.mybatis.flex.base.service.GenericService<P, V, R> {

    @Autowired private R repository;

}
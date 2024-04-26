// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.postgresql.service;

import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.orm.contract.model.base.VO;
import cn.srd.library.java.orm.mybatis.flex.base.repository.GenericRepository;
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
public class GenericService<P extends PO, V extends VO, R extends GenericRepository<P>> extends cn.srd.library.java.orm.mybatis.flex.base.service.GenericService<P, V, R> {

    @Autowired private R repository;

}
// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.service;

import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.orm.contract.model.base.VO;
import cn.srd.library.java.orm.mybatis.flex.base.dao.GenericCurdDao;

/**
 * the generic service
 *
 * @author wjm
 * @since 2024-04-16 14:31
 */
public class GenericService<P extends PO, V extends VO, D extends GenericCurdDao<P>> extends GenericCurdService<P, V, D> {

}
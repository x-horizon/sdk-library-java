// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.tdengine.service;

import cn.srd.library.java.orm.mybatis.flex.base.service.GenericService;
import cn.srd.library.java.orm.mybatis.flex.tdengine.model.po.PeoplePO;
import cn.srd.library.java.orm.mybatis.flex.tdengine.model.vo.PeopleVO;
import cn.srd.library.java.orm.mybatis.flex.tdengine.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wjm
 * @since 2024-04-16 15:26
 */
@Service
public class PeopleService extends GenericService<PeoplePO, PeopleVO, PeopleRepository> {

    @Autowired private PeopleRepository peopleRepository;

}
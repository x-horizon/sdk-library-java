// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.postgresql.service;

import cn.srd.library.java.orm.mybatis.flex.postgresql.model.po.PeoplePO;
import cn.srd.library.java.orm.mybatis.flex.postgresql.model.vo.PeopleVO;
import cn.srd.library.java.orm.mybatis.flex.postgresql.repository.PeopleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author wjm
 * @since 2024-04-16 15:26
 */
@AllArgsConstructor
@Service
public class PeopleService extends GenericService<PeoplePO, PeopleVO, PeopleRepository> {

    private final PeopleRepository peopleRepository;

}
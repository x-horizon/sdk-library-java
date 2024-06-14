// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.postgresql.service;

import cn.srd.library.java.orm.mybatis.flex.postgresql.model.po.HomePO;
import cn.srd.library.java.orm.mybatis.flex.postgresql.model.vo.HomeVO;
import cn.srd.library.java.orm.mybatis.flex.postgresql.repository.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wjm
 * @since 2024-04-16 15:26
 */
@Service
public class HomeService extends GenericService<HomePO, HomeVO, HomeRepository> {

    @Autowired private HomeRepository homeRepository;

}
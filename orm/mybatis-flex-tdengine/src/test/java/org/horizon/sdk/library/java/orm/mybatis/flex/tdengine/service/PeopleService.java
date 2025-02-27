package org.horizon.sdk.library.java.orm.mybatis.flex.tdengine.service;

import org.horizon.sdk.library.java.orm.mybatis.flex.base.service.GenericService;
import org.horizon.sdk.library.java.orm.mybatis.flex.tdengine.model.po.PeoplePO;
import org.horizon.sdk.library.java.orm.mybatis.flex.tdengine.model.vo.PeopleVO;
import org.horizon.sdk.library.java.orm.mybatis.flex.tdengine.repository.PeopleRepository;
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
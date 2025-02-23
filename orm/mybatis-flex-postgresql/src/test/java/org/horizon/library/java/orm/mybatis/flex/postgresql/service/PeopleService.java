package org.horizon.library.java.orm.mybatis.flex.postgresql.service;

import org.horizon.library.java.orm.mybatis.flex.postgresql.model.po.PeoplePO;
import org.horizon.library.java.orm.mybatis.flex.postgresql.model.vo.PeopleVO;
import org.horizon.library.java.orm.mybatis.flex.postgresql.repository.PeopleRepository;
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
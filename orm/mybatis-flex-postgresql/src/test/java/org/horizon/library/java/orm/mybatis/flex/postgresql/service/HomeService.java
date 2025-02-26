package org.horizon.library.java.orm.mybatis.flex.postgresql.service;

import org.horizon.library.java.orm.mybatis.flex.postgresql.model.po.HomePO;
import org.horizon.library.java.orm.mybatis.flex.postgresql.model.vo.HomeVO;
import org.horizon.library.java.orm.mybatis.flex.postgresql.repository.HomeRepository;
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
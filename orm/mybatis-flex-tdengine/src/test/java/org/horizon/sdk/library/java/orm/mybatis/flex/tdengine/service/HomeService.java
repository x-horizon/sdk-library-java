package org.horizon.sdk.library.java.orm.mybatis.flex.tdengine.service;

import org.horizon.sdk.library.java.orm.mybatis.flex.base.service.GenericService;
import org.horizon.sdk.library.java.orm.mybatis.flex.tdengine.model.po.HomePO;
import org.horizon.sdk.library.java.orm.mybatis.flex.tdengine.model.vo.HomeVO;
import org.horizon.sdk.library.java.orm.mybatis.flex.tdengine.repository.HomeRepository;
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
package cn.library.java.message.engine.server.mqtt.service;

import cn.library.java.contract.model.base.VO;
import cn.library.java.message.engine.server.mqtt.model.po.JunctionSignalStepPO;
import cn.library.java.message.engine.server.mqtt.repository.JunctionSignalStepRepository;
import cn.library.java.orm.mybatis.flex.base.service.GenericService;
import org.springframework.stereotype.Service;

/**
 * @author wjm
 * @since 2025-01-12 20:16
 */
@Service
public class JunctionSignalStepService extends GenericService<JunctionSignalStepPO, VO, JunctionSignalStepRepository> {

}
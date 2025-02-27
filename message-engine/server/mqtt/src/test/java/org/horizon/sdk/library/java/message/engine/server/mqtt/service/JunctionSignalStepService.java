package org.horizon.sdk.library.java.message.engine.server.mqtt.service;

import org.horizon.sdk.library.java.contract.model.base.VO;
import org.horizon.sdk.library.java.message.engine.server.mqtt.model.po.JunctionSignalStepPO;
import org.horizon.sdk.library.java.message.engine.server.mqtt.repository.JunctionSignalStepRepository;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.service.GenericService;
import org.springframework.stereotype.Service;

/**
 * @author wjm
 * @since 2025-01-12 20:16
 */
@Service
public class JunctionSignalStepService extends GenericService<JunctionSignalStepPO, VO, JunctionSignalStepRepository> {

}
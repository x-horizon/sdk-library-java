package org.horizon.sdk.library.java.message.engine.server.mqtt.repository;

import org.horizon.sdk.library.java.message.engine.server.mqtt.model.po.JunctionSignalStepPO;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.repository.GenericRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wjm
 * @since 2025-01-12 20:17
 */
@Repository
public interface JunctionSignalStepRepository extends GenericRepository<JunctionSignalStepPO> {

}
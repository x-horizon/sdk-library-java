package org.horizon.library.java.orm.mybatis.flex.base.audit;

import com.mybatisflex.core.audit.AuditMessage;
import com.mybatisflex.core.audit.MessageFactory;

/**
 * the {@link AuditMessage} constructor
 *
 * @author wjm
 * @since 2023-11-14 14:53
 */
public interface AuditLogConstructor extends MessageFactory {

    @Override
    default AuditMessage create() {
        AuditLogDTO auditLogDTO = build();
        AuditMessage auditMessage = new AuditMessage();
        auditMessage.setPlatform(auditLogDTO.getPlatformName());
        auditMessage.setModule(auditLogDTO.getModuleName());
        auditMessage.setUrl(auditLogDTO.getUrl());
        auditMessage.setBizId(auditLogDTO.getBusinessId());
        auditMessage.setUser(auditLogDTO.getUserName());
        auditMessage.setUserIp(auditLogDTO.getUserIp());
        auditMessage.setHostIp(auditLogDTO.getServerIp());
        auditMessage.setMetas(auditLogDTO.getExtraInfos());
        return auditMessage;
    }

    /**
     * build the expanded audit log based on {@link AuditMessage}
     *
     * @return the expanded audit log based on {@link AuditMessage}
     */
    AuditLogDTO build();

}
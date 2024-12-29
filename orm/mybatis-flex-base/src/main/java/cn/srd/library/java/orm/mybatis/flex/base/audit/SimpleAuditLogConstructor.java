package cn.srd.library.java.orm.mybatis.flex.base.audit;

import com.mybatisflex.core.audit.AuditMessage;

/**
 * the simple {@link AuditMessage} constructor, do not have the expanded audit log info
 *
 * @author wjm
 * @since 2023-11-14 14:53
 */
public class SimpleAuditLogConstructor implements AuditLogConstructor {

    @Override
    public AuditLogDTO build() {
        return AuditLogDTO.builder().build();
    }

}
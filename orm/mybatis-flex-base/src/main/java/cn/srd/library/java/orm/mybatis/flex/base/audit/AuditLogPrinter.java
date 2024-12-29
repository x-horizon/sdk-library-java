package cn.srd.library.java.orm.mybatis.flex.base.audit;

import com.mybatisflex.core.audit.AuditMessage;
import com.mybatisflex.core.audit.MessageCollector;

/**
 * the {@link AuditMessage} printer
 *
 * @author wjm
 * @since 2023-11-14 14:53
 */
public interface AuditLogPrinter extends MessageCollector {

    @Override
    default void collect(AuditMessage auditLog) {
        print(auditLog);
    }

    /**
     * the audit log print action
     *
     * @param auditLog the audit log
     */
    void print(AuditMessage auditLog);

}
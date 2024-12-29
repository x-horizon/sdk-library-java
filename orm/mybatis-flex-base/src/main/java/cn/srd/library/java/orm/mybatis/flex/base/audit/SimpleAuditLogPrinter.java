package cn.srd.library.java.orm.mybatis.flex.base.audit;

import com.mybatisflex.core.audit.AuditMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * the simple {@link AuditMessage} printer, just print the full sql and the sql elapsed time.
 *
 * @author wjm
 * @since 2023-11-14 14:53
 */
@Slf4j
public class SimpleAuditLogPrinter implements AuditLogPrinter {

    @Override
    public void print(AuditMessage auditLog) {
        log.debug("exec sql took {} ms >>> {}", auditLog.getElapsedTime(), auditLog.getFullSql());
    }

}
package org.horizon.sdk.library.java.orm.mybatis.flex.base.audit;

import com.mybatisflex.core.audit.AuditManager;
import com.mybatisflex.core.audit.AuditMessage;
import com.mybatisflex.core.audit.MessageReporter;
import org.horizon.sdk.library.java.contract.model.throwable.UnsupportedException;
import org.horizon.sdk.library.java.tool.lang.text.Strings;

import java.util.List;

/**
 * the invalid {@link AuditLogTelemeter} implement, will not add it to {@link AuditManager#setMessageReporter(MessageReporter)}.
 *
 * @author wjm
 * @since 2023-11-14 14:53
 */
public final class UnsupportedAuditLogTelemeter implements AuditLogTelemeter {

    @Override
    public void send(List<AuditMessage> auditLogs) {
        throw new UnsupportedException(Strings.format("{}unsupported audit log telemeter, please check!"));
    }

}
// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.audit;

import cn.srd.library.java.contract.model.throwable.UnsupportedException;
import cn.srd.library.java.tool.lang.text.Strings;
import com.mybatisflex.core.audit.AuditManager;
import com.mybatisflex.core.audit.AuditMessage;
import com.mybatisflex.core.audit.MessageReporter;

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
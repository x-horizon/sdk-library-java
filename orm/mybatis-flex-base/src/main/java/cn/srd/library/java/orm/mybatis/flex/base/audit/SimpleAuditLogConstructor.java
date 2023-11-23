// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

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
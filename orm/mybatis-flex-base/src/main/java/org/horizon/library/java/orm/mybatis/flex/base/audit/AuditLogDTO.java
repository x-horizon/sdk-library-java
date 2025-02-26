package org.horizon.library.java.orm.mybatis.flex.base.audit;

import com.mybatisflex.core.audit.AuditMessage;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.Map;

/**
 * the expanded audit log based on {@link AuditMessage}
 *
 * @author wjm
 * @since 2023-11-14 14:57
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class AuditLogDTO {

    /**
     * see {@link AuditMessage#getPlatform()}
     */
    private String platformName;

    /**
     * see {@link AuditMessage#getModule()}
     */
    private String moduleName;

    /**
     * see {@link AuditMessage#getUser()}
     */
    private String userName;

    /**
     * see {@link AuditMessage#getBizId()}
     */
    private String businessId;

    /**
     * see {@link AuditMessage#getUrl()}
     */
    private String url;

    /**
     * see {@link AuditMessage#getUserIp()}
     */
    private String userIp;

    /**
     * see {@link AuditMessage#getHostIp()}
     */
    private String serverIp;

    /**
     * see {@link AuditMessage#getMetas()}
     */
    private Map<String, Object> extraInfos;

}
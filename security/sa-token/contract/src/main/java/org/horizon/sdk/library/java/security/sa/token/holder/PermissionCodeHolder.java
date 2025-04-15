package org.horizon.sdk.library.java.security.sa.token.holder;

import cn.dev33.satoken.stp.StpInterface;

import java.util.List;

/**
 * @author wjm
 * @since 2025-04-15 15:31
 */
public interface PermissionCodeHolder extends StpInterface {

    @Override
    default List<String> getPermissionList(Object loginId, String loginType) {
        return getResourcePermissionCodes(loginId);
    }

    @Override
    default List<String> getRoleList(Object loginId, String loginType) {
        return getRolePermissionCodes(loginId);
    }

    List<String> getResourcePermissionCodes(Object loginId);

    List<String> getRolePermissionCodes(Object loginId);

}
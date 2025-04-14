package org.horizon.sdk.library.java.security.sa.token.support;

import cn.dev33.satoken.stp.StpUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;

import java.util.Optional;

/**
 * @author wjm
 * @since 2025-04-15 02:09
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SaTokens {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Authentication {

        public static void login(Object id) {
            StpUtil.login(id);
        }

    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Token {

        public static String getValue() {
            return StpUtil.getTokenValue();
        }

    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class AccountSession {

        @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
        public static <T> Optional<T> get(Object loginId, String key) {
            return (Optional<T>) Optional.ofNullable(StpUtil.getSessionByLoginId(loginId).get(key));
        }

        public static <T> void set(String key, T value) {
            StpUtil.getSession().set(key, value);
        }

    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Assert {

        public static void login() {
            StpUtil.checkLogin();
        }

        public static void permission(String resource) {
            StpUtil.checkPermission(resource);
        }

    }

}
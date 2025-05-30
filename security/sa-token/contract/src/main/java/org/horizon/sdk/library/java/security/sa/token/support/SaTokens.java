package org.horizon.sdk.library.java.security.sa.token.support;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.dev33.satoken.stp.StpUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.sdk.library.java.contract.model.throwable.UnauthenticatedException;
import org.horizon.sdk.library.java.contract.model.throwable.UnauthorizedException;

import java.util.Optional;

/**
 * @author wjm
 * @since 2025-04-15 02:09
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SaTokens {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class AccountSession {

        private static final String SESSION_KEY = "sessionKey";

        @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
        public static <T> Optional<T> get(Object loginId) {
            return (Optional<T>) Optional.ofNullable(StpUtil.getSessionByLoginId(loginId).get(SESSION_KEY));
        }

        public static <T> void set(T value) {
            StpUtil.getSession().set(SESSION_KEY, value);
        }

    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Authentication {

        public static void login(Object id) {
            StpUtil.login(id);
        }

    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Assert {

        public static void throwsIfNotLogin() {
            StpUtil.checkLogin();
        }

        public static void throwsIfNoPermission(String resource) {
            StpUtil.checkPermission(resource);
        }

        public static void throwsIfNoRole(String resource) {
            StpUtil.checkRole(resource);
        }

    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Exception {

        @SneakyThrows
        public static void doThrow(Throwable throwable) {
            switch (throwable) {
                case NotLoginException notLoginException -> throw new UnauthenticatedException(notLoginException.getMessage());
                case NotPermissionException notPermissionException -> throw new UnauthorizedException(notPermissionException.getMessage());
                case NotRoleException notRoleException -> throw new UnauthorizedException(notRoleException.getMessage());
                default -> throw throwable;
            }
        }

    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Request {

        public static String getPath() {
            return SaHolder.getRequest().getRequestPath();
        }

    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Token {

        public static String getValue() {
            return StpUtil.getTokenValue();
        }

    }

}
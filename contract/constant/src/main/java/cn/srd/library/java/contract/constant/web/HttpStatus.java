// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.contract.constant.web;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * http status
 *
 * @author wjm
 * @since 2020-06-09 14:25
 */
@Getter
@AllArgsConstructor
public enum HttpStatus {

    OK(200, "ok"),
    CREATED(201, "created"),
    ACCEPTED(202, "accepted"),
    NON_AUTHORITATIVE(203, "non-authoritative"),
    NO_CONTENT(204, "no content"),
    RESET_CONTENT(205, "reset content"),
    PARTIAL_CONTENT(206, "partial content"),

    MULTIPLE_CHOICES(300, "multiple choices"),
    MOVED_PERMANENTLY(301, "moved permanently"),
    TEMPORARY_REDIRECT(302, "temporary redirect"),
    SEE_OTHER(303, "see other"),
    NOT_MODIFIED(304, "not modified"),
    USE_PROXY(305, "use proxy"),
    TEMPORARY_REDIRECT_RFC_7231(307, "temporary redirect"), // HTTP 1.1 Status-Code, see: RFC-7231
    PERMANENT_REDIRECT(308, "permanent redirect"),// HTTP 1.1 Status-Code, see: RFC-7231

    BAD_REQUEST(400, "client error - bad request"),
    UNAUTHORIZED(401, "client error - unauthorized"),
    PAYMENT_REQUIRED(402, "client error - payment required"),
    FORBIDDEN(403, "client error - forbidden"),
    NOT_FOUND(404, "client error - not found"),
    BAD_METHOD(405, "client error - method not allowed"),
    NOT_ACCEPTABLE(406, "client error - not acceptable"),
    PROXY_AUTH(407, "client error - proxy authentication required"),
    CLIENT_REQUEST_TIMEOUT(408, "client error - request timeout"),
    CONFLICT(409, "client error - conflict"),
    GONE(410, "client error - gone"),
    LENGTH_REQUIRED(411, "client error - length required"),
    PRECONDITION_FAILED(412, "client error - precondition failed"),
    REQUEST_ENTITY_TOO_LARGE(413, "client error - request entity too large"),
    REQUEST_URI_TOO_LONG(414, "client error - request-uri too large"),
    UNSUPPORTED_MEDIA_TYPE(415, "client error - unsupported media type"),
    NOT_LOGIN(430, "client error - not login"),
    WITHOUT_PERMISSION(431, "client error - without permission"),
    WITHOUT_ROLE(432, "client error - without role"),

    INTERNAL_ERROR(500, "server error - internal server error"),
    NOT_IMPLEMENTED(501, "server error - not implemented"),
    BAD_GATEWAY(502, "server error - bad gateway"),
    UNAVAILABLE(503, "server error - service unavailable"),
    GATEWAY_TIMEOUT(504, "server error - gateway timeout"),
    NOT_SUPPORTED_VERSION(505, "server error - http version not supported"),

    ;

    /**
     * the status
     */
    private final int status;

    /**
     * the description
     */
    private final String description;

    /**
     * return true if it is a redirect status code
     *
     * @param statusCode checked status code
     * @return true if it is a redirect status code
     */
    public static boolean isRedirected(int statusCode) {
        return statusCode == MOVED_PERMANENTLY.getStatus()
                || statusCode == SEE_OTHER.getStatus()
                // issue#1504@Github，307 and 308 are specifications defined by HTTP 1.1 in RFC 7538
                || statusCode == TEMPORARY_REDIRECT.getStatus()
                || statusCode == TEMPORARY_REDIRECT_RFC_7231.getStatus()
                || statusCode == PERMANENT_REDIRECT.getStatus();
    }

}
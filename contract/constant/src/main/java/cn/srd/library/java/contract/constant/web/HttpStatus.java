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

    BAD_REQUEST(400, "bad request"),
    UNAUTHORIZED(401, "unauthorized"),
    PAYMENT_REQUIRED(402, "payment required"),
    FORBIDDEN(403, "forbidden"),
    RESOURCE_NOT_FOUND(404, "resource not found"),
    BAD_METHOD(405, "method not allowed"),
    NOT_ACCEPTABLE(406, "not acceptable"),
    PROXY_AUTH(407, "proxy authentication required"),
    CLIENT_REQUEST_TIMEOUT(408, "request timeout"),
    CONFLICT(409, "conflict"),
    GONE(410, "gone"),
    LENGTH_REQUIRED(411, "length required"),
    PRECONDITION_FAILED(412, "precondition failed"),
    REQUEST_ENTITY_TOO_LARGE(413, "request entity too large"),
    REQUEST_URI_TOO_LONG(414, "request-uri too large"),
    UNSUPPORTED_MEDIA_TYPE(415, "unsupported media type"),
    // extra - permission
    NOT_LOGGED_IN(430, "not logged in"),
    WITHOUT_PERMISSION(431, "without permission"),
    WITHOUT_ROLE(432, "without role"),
    // extra - request parameter
    MESSAGE_NOT_READABLE(450, "http message not readable"),
    MISSING_REQUEST_PARAMETER(451, "missing request param"),
    WRONG_REQUEST_PARAMETER_TYPE(452, "wrong request message type"),
    WRONG_REQUEST_MESSAGE_VALUE(453, "wrong request message value"),

    INTERNAL_ERROR(500, "internal server error"),
    NOT_IMPLEMENTED(501, "not implemented"),
    BAD_GATEWAY(502, "bad gateway"),
    UNAVAILABLE(503, "service unavailable"),
    GATEWAY_TIMEOUT(504, "gateway timeout"),
    NOT_SUPPORTED_VERSION(505, "http version not supported"),
    // extra
    UNSUPPORTED(530, "unsupported method"),
    DATA_NOT_FOUND(531, "data not found"),

    ;

    /**
     * the status code
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
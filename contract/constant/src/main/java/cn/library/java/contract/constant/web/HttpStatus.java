package cn.library.java.contract.constant.web;

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

    CONTINUE(100, "continue"),
    SWITCHING_PROTOCOL(101, "switching protocols"),
    PROCESSING(102, "processing"),
    EARLY_HINT(103, "early hints"),

    OK(200, "ok"),
    CREATED(201, "created"),
    ACCEPTED(202, "accepted"),
    NON_AUTHORITATIVE_INFORMATION(203, "non-authoritative information"),
    NO_CONTENT(204, "no content"),
    RESET_CONTENT(205, "reset content"),
    PARTIAL_CONTENT(206, "partial content"),
    MULTI_STATUS(207, "multi status"),
    ALREADY_REPORTED(208, "already reported"),
    TRANSFORMATION_APPLIED(214, "transformation applied"),
    IM_USED(226, "IM used"),

    MULTIPLE_CHOICES(300, "multiple choices"),
    MOVED_PERMANENTLY(301, "moved permanently"),
    FOUND(302, "found"),
    SEE_OTHER(303, "see other"),
    NOT_MODIFIED(304, "not modified"),
    USE_PROXY(305, "use proxy"),
    TEMPORARY_REDIRECT_RFC_7231(307, "temporary redirect"), // HTTP 1.1 Status-Code, see: RFC-7231
    PERMANENT_REDIRECT(308, "permanent redirect"),// HTTP 1.1 Status-Code, see: RFC-7231

    BAD_REQUEST(400, "bad request"),
    UNAUTHORIZED(401, "unauthorized"),
    PAYMENT_REQUIRED(402, "payment required"),
    FORBIDDEN(403, "forbidden"),
    NOT_FOUND(404, "not found"),
    BAD_METHOD(405, "method not allowed"),
    NOT_ACCEPTABLE(406, "not acceptable"),
    PROXY_AUTHENTICATION_REQUIRED(407, "proxy authentication required"),
    CLIENT_REQUEST_TIMEOUT(408, "request timeout"),
    CONFLICT(409, "conflict"),
    GONE(410, "gone"),
    LENGTH_REQUIRED(411, "length required"),
    PRECONDITION_FAILED(412, "precondition failed"),
    PAYLOAD_TOO_LARGE(413, "payload too large"),
    REQUEST_URI_TOO_LONG(414, "request-uri too large"),
    UNSUPPORTED_MEDIA_TYPE(415, "unsupported media type"),
    REQUEST_RANGE_NOT_SATISFIABLE(416, "request range not satisfiable"),
    EXPECTATION_FAILED(417, "expectation failed"),
    I_AM_A_TEAPOT(418, "I'm a teapot"),
    ENHANCE_YOUR_CALM(420, "enhance your calm"),
    MISDIRECTED_REQUEST(421, "misdirected request"),
    UNPROCESSABLE_ENTITY(422, "unprocessable entity"),
    LOCKED(423, "locked"),
    FAILED_DEPENDENCY(424, "failed dependency"),
    TOO_EARLY(425, "too early"),
    UPGRADE_REQUIRED(426, "upgrade required"),
    PRECONDITION_REQUIRED(428, "precondition required"),
    TOO_MANY_REQUESTS(429, "too many requests"),
    REQUEST_HEADER_FIELDS_TO_LARGE(431, "request header fields to large"),
    NO_RESPONSE(444, "no response"),
    BLOCKED_BY_WINDOWS_PARENTAL_CONTROL(450, "blocked by windows parental controls"),
    UNAVAILABLE_FOR_LEGAL_REASON(451, "unavailable for legal reasons"),
    HTTP_REQUEST_SENT_TO_HTTPS_PORT(497, "http request sent to https port"),
    TOKEN_EXPIRED_OR_INVALID(498, "token expired/invalid"),
    CLIENT_CLOSED_REQUEST(499, "client closed request"),
    // extra
    NOT_LOGGED_IN(460, "not logged in"),
    WITHOUT_PERMISSION(461, "without permission"),
    WITHOUT_ROLE(462, "without role"),
    MESSAGE_NOT_READABLE(463, "http message not readable"),
    MISSING_REQUEST_PARAMETER(464, "missing request param"),
    WRONG_REQUEST_PARAMETER_TYPE(465, "wrong request message type"),
    WRONG_REQUEST_MESSAGE_VALUE(466, "wrong request message value"),

    INTERNAL_ERROR(500, "internal server error"),
    NOT_IMPLEMENTED(501, "not implemented"),
    BAD_GATEWAY(502, "bad gateway"),
    UNAVAILABLE(503, "service unavailable"),
    GATEWAY_TIMEOUT(504, "gateway timeout"),
    VARIANT_ALSO_NEGOTIATES(506, "variant also negotiates"),
    INSUFFICIENT_STORAGE(507, "insufficient storage"),
    LOOP_DETECTED(508, "loop detected"),
    BANDWIDTH_LIMIT_EXCEEDED(509, "bandwidth limit exceeded"),
    NOT_EXTENDED(510, "not extended"),
    NETWORK_AUTHENTICATION_REQUIRED(511, "network authentication required"),
    WEB_SERVER_IS_DOWN(521, "web server is down"),
    CONNECTION_TIMED_OUT(522, "connection timed out"),
    ORIGIN_IS_UNREACHABLE(523, "origin is unreachable"),
    SSL_HANDSHAKE_FAILED(525, "ssl handshake failed"),
    SITE_FROZEN(530, "site frozen"),
    NETWORK_CONNECT_TIMEOUT_ERROR(599, "network connect timeout error"),
    // extra
    DATA_NOT_FOUND(540, "data not found"),

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
                || statusCode == FOUND.getStatus()
                || statusCode == TEMPORARY_REDIRECT_RFC_7231.getStatus()
                || statusCode == PERMANENT_REDIRECT.getStatus();
    }

}
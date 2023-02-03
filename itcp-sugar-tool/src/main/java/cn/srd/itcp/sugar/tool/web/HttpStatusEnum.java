package cn.srd.itcp.sugar.tool.web;

import cn.srd.itcp.sugar.tool.constant.HttpInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 响应码 + 响应码对应的描述
 *
 * @author wjm
 * @since 2020/6/29 14:25
 */
@Getter
@AllArgsConstructor
public enum HttpStatusEnum implements HttpStatus {

    // ========== 2xx success ==========

    /**
     * 200 - 成功
     */
    SUCCESS(HttpInfo.HTTP_OK, "ok"),

    /**
     * <pre>
     * 204 - 成功
     * 常用于 HTTP HEAD 请求，响应不包含 body 数据；
     * HEAD 常用于获取请求中隐含的元信息，而不用传输实体本身；
     * </pre>
     */
    NO_CONTENT(HttpInfo.HTTP_NO_CONTENT, "ok - no content"),

    /**
     * <pre>
     * 206 - 成功
     * 常用于 HTTP 分块下载或断点续传，表示响应返回的 body 数据不是资源的全部，而是其中的一部分；
     * </pre>
     */
    PARTIAL(HttpInfo.HTTP_PARTIAL, "ok - partial"),

    // ========== 3xx redirect =========

    /**
     * <pre>
     * 301 - 永久重定向
     * 请求的资源已经不存在，需要改用新的 URL 再次访问；
     * 一般会在响应头里使用字段 Location 来指明后续要跳转的 URL，浏览器会自动重定向新的 URL；
     * </pre>
     */
    MOVED_PERM(HttpInfo.HTTP_MOVED_PERM, "redirect - moved perm"),

    /**
     * <pre>
     * 302 - 临时重定向
     * 请求的资源还在，但暂时需要用另一个 URL 来访问；
     * 一般会在响应头里使用字段 Location 来指明后续要跳转的 URL，浏览器会自动重定向新的 URL；
     * </pre>
     */
    MOVED_TEMP(HttpInfo.HTTP_MOVED_TEMP, "redirect - moved temp"),

    // ========== 4xx client error =====

    /**
     * <pre>
     * 400 - 客户端的请求有错误（笼统的错误）
     * </pre>
     */
    BAD_REQUEST(HttpInfo.HTTP_BAD_REQUEST, "client error - bad request"),

    /**
     * <pre>
     * 401 - 客户端认证失败（笼统的未登录、鉴权失败等）
     * </pre>
     */
    UNAUTHORIZED(HttpInfo.HTTP_UNAUTHORIZED, "client error - unauthorized"),

    /**
     * <pre>
     * 430 - 客户端认证失败（未登录）
     * </pre>
     */
    NOT_LOGIN(HttpInfo.NOT_LOGIN, "client error - not login"),

    /**
     * <pre>
     * 431 - 客户端认证失败（未通过权限认证）
     * </pre>
     */
    NOT_PERMISSION(HttpInfo.NOT_PERMISSION, "client error - not permission"),

    /**
     * <pre>
     * 432 - 客户端认证失败（未通过角色认证）
     * </pre>
     */
    NOT_ROLE(HttpInfo.NOT_ROLE, "client error - not role"),

    /**
     * <pre>
     * 403 - 表示服务端禁止访问资源，而不是客户端的请求出错；
     * 与 {@link #UNAUTHORIZED} 的区别是：{@link #FORBIDDEN} 表示已经认证成功，但不允许访问；
     * </pre>
     */
    FORBIDDEN(HttpInfo.HTTP_FORBIDDEN, "client error - forbidden"),

    /**
     * <pre>
     * 404 - 客户端请求的资源在服务端上不存在或未找到，所以无法提供给客户端；
     * </pre>
     */
    NOT_FOUND(HttpInfo.HTTP_NOT_FOUND, "client error - not found"),

    /**
     * <pre>
     * 405 - 客户端请求的方法与服务端提供的不一致，例如提供了 POST，却使用了 GET 访问；
     * </pre>
     */
    METHOD_NOT_ALLOWED(HttpInfo.HTTP_BAD_METHOD, "client error - method not allowed"),

    // ========== 5xx server error =====

    /**
     * <pre>
     * 500 - 服务端错误（笼统的错误）
     * </pre>
     */
    INTERNAL_ERROR(HttpInfo.HTTP_INTERNAL_ERROR, "server error - internal error"),

    /**
     * <pre>
     * 502 - 当服务端作为网关，或转发、代理时，从访问的其他服务器接收到了无效的响应，但该服务端自身是工作正常的；
     * </pre>
     */
    BAD_GATEWAY(HttpInfo.HTTP_BAD_GATEWAY, "server error - bad gateway"),

    /**
     * <pre>
     * 503 - 表示服务器当前很忙（处于临时的维护或过载的情况），暂时无法响应服务器，类似“网络服务正忙，请稍后重试”的意思；
     * </pre>
     */
    UNAVAILABLE(HttpInfo.HTTP_UNAVAILABLE, "server error - unavailable"),

    /**
     * <pre>
     * 504 - 当服务端作为网关，或转发、代理时，从访问的其他服务端发生了超时，但该服务端自身是工作正常的
     * </pre>
     */
    GATEWAY_TIMEOUT(HttpInfo.HTTP_GATEWAY_TIMEOUT, "server error - gateway timeout");

    private final int code;
    private final String description;

}

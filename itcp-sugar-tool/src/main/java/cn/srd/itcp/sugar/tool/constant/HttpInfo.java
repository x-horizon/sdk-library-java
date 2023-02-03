package cn.srd.itcp.sugar.tool.constant;

import cn.hutool.http.HttpStatus;

/**
 * HTTP 相关常量
 *
 * @author wjm
 * @since 2020/6/29 14:25
 */
public class HttpInfo extends HttpStatus {

    // ******************************************************** http field name

    /**
     * 字段名称定义：状态码字段名
     */
    public static final String FIELD_NAME_STATUS = "status";

    /**
     * 字段名称定义：状态码字段名
     */
    public static final String FIELD_NAME_CODE = "code";

    /**
     * 字段名称定义：响应消息字段名
     */
    public static final String FIELD_NAME_MESSAGE = "message";

    /**
     * 字段名称定义：响应数据字段名
     */
    public static final String FIELD_NAME_DATA = "data";

    // ******************************************************** http 1.0

    /**
     * 请求方式：GET
     */
    public static final String REQUEST_METHOD_GET = "GET";

    /**
     * 请求方式：POST
     */
    public static final String REQUEST_METHOD_POST = "POST";

    /**
     * 请求方式：HEAD
     */
    public static final String REQUEST_METHOD_HEAD = "HEAD";

    // ******************************************************** http 1.1

    /**
     * 请求方式：OPTIONS
     */
    public static final String REQUEST_METHOD_OPTIONS = "OPTIONS";

    /**
     * 请求方式：PUT
     */
    public static final String REQUEST_METHOD_PUT = "PUT";

    /**
     * 请求方式：DELETE
     */
    public static final String REQUEST_METHOD_DELETE = "DELETE";

    /**
     * 请求方式：TRACE
     */
    public static final String REQUEST_METHOD_TRACE = "TRACE";

    /**
     * 请求方式：CONNECT
     */
    public static final String REQUEST_METHOD_CONNECT = "CONNECT";

    // ******************************************************** type of response data

    /**
     * 响应类型：text
     */
    public static final String RESPONSE_DATA_TYPE_TEXT = "text";

    /**
     * 响应类型：json
     */
    public static final String RESPONSE_DATA_TYPE_JSON = "json";

    /**
     * 响应类型：xml
     */
    public static final String RESPONSE_DATA_TYPE_XML = "xml";

    // ******************************************************** content type

    /**
     * content type：application/json
     */
    public static final String CONTENT_TYPE_JSON = "application/json";

    /**
     * content type：application/octet-stream
     */
    public static final String CONTENT_TYPE_OCTET_STREAM = "application/octet-stream";

    /**
     * content type：application/vnd.ms-excel
     */
    public static final String CONTENT_TYPE_EXCEL = "application/vnd.ms-excel";

    // ******************************************************** status code

    /**
     * 状态码：未登录
     */
    public static final int NOT_LOGIN = 430;

    /**
     * 状态码：未有权限
     */
    public static final int NOT_PERMISSION = 431;

    /**
     * 状态码：不具有权限角色
     */
    public static final int NOT_ROLE = 432;

    // ******************************************************** transport protocol

    /**
     * 协议类型：http
     */
    public static final String HTTP_PROTOCOL = "http://";

    /**
     * 协议类型：https
     */
    public static final String HTTPS_PROTOCOL = "https://";

    /**
     * 协议类型：rtsp
     */
    public static final String RTSP_PROTOCOL = "rtsp://";

}
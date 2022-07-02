package cn.sugar.tools.web;

import cn.hutool.http.HttpStatus;

/**
 * HTTP 相关常量
 *
 * @author wjm
 * @date 2020/6/29 14:25
 */
public class HttpInfo extends HttpStatus {

    /**
     * http field name
     */
    public static final String FIELD_NAME_STATUS = "status";
    public static final String FIELD_NAME_CODE = "code";
    public static final String FIELD_NAME_MESSAGE = "message";
    public static final String FIELD_NAME_DATA = "data";

    /**
     * http 1.0
     */
    public static final String REQUEST_METHOD_GET = "GET";
    public static final String REQUEST_METHOD_POST = "POST";
    public static final String REQUEST_METHOD_HEAD = "HEAD";

    /**
     * http 1.1
     */
    public static final String REQUEST_METHOD_OPTIONS = "OPTIONS";
    public static final String REQUEST_METHOD_PUT = "PUT";
    public static final String REQUEST_METHOD_DELETE = "DELETE";
    public static final String REQUEST_METHOD_TRACE = "TRACE";
    public static final String REQUEST_METHOD_CONNECT = "CONNECT";

    /**
     * type of response data
     */
    public static final String RESPONSE_DATA_TYPE_TEXT = "text";
    public static final String RESPONSE_DATA_TYPE_JSON = "json";
    public static final String RESPONSE_DATA_TYPE_XML = "xml";

    /**
     * content type
     */
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String CONTENT_TYPE_OCTET_STREAM = "application/octet-stream";

}
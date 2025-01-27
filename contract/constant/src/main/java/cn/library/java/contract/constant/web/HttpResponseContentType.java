package cn.library.java.contract.constant.web;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * http response content type
 *
 * @author wjm
 * @since 2020-06-09 14:25
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpResponseContentType {

    /**
     * {@code "text"}
     */
    public static final String TEXT = "text";

    /**
     * {@code "json"}
     */
    public static final String JSON = "json";

    /**
     * {@code "xml"}
     */
    public static final String XML = "xml";

}
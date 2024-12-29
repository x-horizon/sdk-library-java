package cn.srd.library.java.contract.constant.regex;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

/**
 * @author wjm
 * @since 2024-04-24 15:14
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegexConstant {

    /**
     * matching example: "-1.11", "-1", "0", "0.0", "1", "1.11"
     */
    public static final String NUMBER = "^-?\\d+(\\.\\d+)?$";

    public static final Pattern NUMBER_PATTERN = Pattern.compile(NUMBER);

    /**
     * matching example: "-1", "0", "1"
     */
    public static final String INTEGRAL_NUMBER = "^-?\\d+$";

    public static final Pattern INTEGRAL_NUMBER_PATTERN = Pattern.compile(INTEGRAL_NUMBER);

    /**
     * matching example: "-1.11", "0.0", "1.11"
     */
    public static final String FLOAT_NUMBER = "^-?\\d+(\\.\\d+)$";

    public static final Pattern FLOAT_NUMBER_PATTERN = Pattern.compile(FLOAT_NUMBER);

    /**
     * matching example: "0", "0.0", "1", "1.11"
     */
    public static final String POSITIVE_NUMBER = "^\\d+(\\.\\d+)?$";

    public static final Pattern POSITIVE_NUMBER_PATTERN = Pattern.compile(POSITIVE_NUMBER);

    /**
     * matching example: "0", "1"
     */
    public static final String POSITIVE_INTEGRAL_NUMBER = "^\\d+$";

    public static final Pattern POSITIVE_INTEGRAL_NUMBER_PATTERN = Pattern.compile(POSITIVE_INTEGRAL_NUMBER);

    /**
     * matching example: "0.0", "1.11"
     */
    public static final String POSITIVE_FLOAT_NUMBER = "^\\d+(\\.\\d+)$";

    public static final Pattern POSITIVE_FLOAT_NUMBER_PATTERN = Pattern.compile(POSITIVE_FLOAT_NUMBER);

    /**
     * matching example: "-1.11", "-1"
     */
    public static final String NEGATIVE_NUMBER = "^-\\d+(\\.\\d+)?$";

    public static final Pattern NEGATIVE_NUMBER_PATTERN = Pattern.compile(NEGATIVE_NUMBER);

    /**
     * matching example: "-1"
     */
    public static final String NEGATIVE_INTEGRAL_NUMBER = "^-\\d+$";

    public static final Pattern NEGATIVE_INTEGRAL_NUMBER_PATTERN = Pattern.compile(NEGATIVE_INTEGRAL_NUMBER);

    /**
     * matching example: "-1.11"
     */
    public static final String NEGATIVE_FLOAT_NUMBER = "^-\\d+(\\.\\d+)$";

    public static final Pattern NEGATIVE_FLOAT_NUMBER_PATTERN = Pattern.compile(NEGATIVE_FLOAT_NUMBER);

    /**
     * matching example: "abcdefg'11', '22','33'   ,'rr', 'pp' sdncsldbcls" -> matching the last '' -> pp
     */
    public static final String THE_LAST_DOUBLE_QUOTE = "'([^']*)'(?!.*')";

    public static final Pattern THE_LAST_DOUBLE_QUOTE_PATTERN = Pattern.compile(THE_LAST_DOUBLE_QUOTE);

    /**
     * <pre>
     * matching example: "http://example.com" -> can be matched
     * matching example: "ftp://example.com" -> can be matched
     * matching example: "example://example.com" -> can be matched
     * matching example: "invalid_url" -> could not be matched
     * </pre>
     */
    public static final String URL = "^[a-zA-Z][a-zA-Z0-9+.-]*://[^\\s/$.?#].\\S*$";

    public static final Pattern URL_PATTERN = Pattern.compile(URL);

    /**
     * <pre>
     * matching example: "http://example.com" -> matching 'http'
     * matching example: "ftp://example.com" -> matching 'ftp'
     * matching example: "example://example.com" -> matching 'example'
     * matching example: "example://example.com.example2://example.com" -> matching 'example'
     * matching example: "invalid_url" -> could not be matched
     * </pre>
     */
    public static final String URL_PROTOCOL = "^(.*?):\\/\\/";

    public static final Pattern URL_PROTOCOL_PATTERN = Pattern.compile(URL_PROTOCOL);

}
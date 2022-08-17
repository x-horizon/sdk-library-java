package cn.srd.itcp.sugar.tools.constant;

/**
 * 字符串常量池
 *
 * @author wjm
 * @since 2021/2/1 20:38
 */
public interface StringPool {

    // ================ 标点符号相关信息 ================

    /**
     * 空格符 {@code " "}
     */
    String SPACE = String.valueOf(CharPool.SPACE);

    /**
     * 反斜杠 {@code "\\"}
     */
    String BACKSLASH = String.valueOf(CharPool.BACKSLASH);

    /**
     * 回车符 {@code "\r"}
     */
    String CR = String.valueOf(CharPool.CR);

    /**
     * 减号 {@code "-"}
     */
    String DASHED = String.valueOf(CharPool.DASHED);

    /**
     * 下划线 {@code "_"}
     */
    String UNDERLINE = String.valueOf(CharPool.UNDERLINE);

    /**
     * 花括号（左） {@code "{"}
     */
    String DELIM_START = String.valueOf(CharPool.DELIM_START);

    /**
     * 花括号（右） {@code "}"}
     */
    String DELIM_END = String.valueOf(CharPool.DELIM_END);

    /**
     * 中括号（左） {@code "["}
     */
    String BRACKET_START = String.valueOf(CharPool.BRACKET_START);

    /**
     * 中括号（右） {@code "]"}
     */
    String BRACKET_END = String.valueOf(CharPool.BRACKET_END);

    /**
     * 双引号 {@code "\""}
     */
    String DOUBLE_QUOTES = String.valueOf(CharPool.DOUBLE_QUOTES);

    /**
     * 单引号 {@code "\'"}
     */
    String SINGLE_QUOTE = String.valueOf(CharPool.SINGLE_QUOTE);

    /**
     * 与 {@code "&"}
     */
    String AMP = String.valueOf(CharPool.AMP);

    /**
     * 艾特 {@code "@"}
     */
    String AT = String.valueOf(CharPool.AT);

    /**
     * 制表符 {@code "\t"}
     */
    String TAB = String.valueOf(CharPool.TAB);

    /**
     * 换行符 {@code "\n"}
     */
    String LF = String.valueOf(CharPool.LF);

    /**
     * 强制前端换行符: &lt;br/&gt;，若 {@link #LF} 在前端显示不生效，则可使用该换行符
     */
    String LABEL_BR = "<br/>";

    /**
     * 斜杠 {@code "/"}
     */
    String SLASH = String.valueOf(CharPool.SLASH);

    /**
     * 冒号 {@code ":"}
     */
    String COLON = String.valueOf(CharPool.COLON);

    /**
     * 星号 {@code "*"}
     */
    String ASTERISK = "*";

    /**
     * 空字符串 {@code ""}
     */
    String EMPTY = "";

    /**
     * 逗号 {@code ","}
     */
    String COMMA = String.valueOf(CharPool.COMMA);

    /**
     * 点 {@code "."}
     */
    String DOT = String.valueOf(CharPool.DOT);

    /**
     * 中文顿号 {@code "、"}
     */
    String CHINESE_DOT = "、";

    /**
     * 单书名号（左）{@code "<"}
     */
    String SINGLE_BOOK_NAME_LEFT = "<";

    /**
     * 单书名号（右）{@code ">"}
     */
    String SINGLE_BOOK_NAME_RIGHT = ">";

    /**
     * 单书名号 {@code "<>"}
     */
    String SINGLE_BOOK_NAME = "<>";

    /**
     * 双书名号（左）{@code "《"}
     */
    String DOUBLE_BOOK_NAME_LEFT = "《";

    /**
     * 双书名号（右）{@code "《"}
     */
    String DOUBLE_BOOK_NAME_RIGHT = "》";

    /**
     * 双书名号 {@code "《》"}
     */
    String DOUBLE_BOOK_NAME = "《》";

    // ================ 传输协议相关信息 ================

    String HTTP_PROTOCOL = "http://";

    String HTTPS_PROTOCOL = "https://";

}

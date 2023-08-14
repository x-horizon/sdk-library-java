package cn.srd.library.java.tool.constant.core;

/**
 * 字符串常量池
 *
 * @author wjm
 * @since 2021/2/1 20:38
 */
public interface StringPool {

    // =================== 数据类型 ===================

    /**
     * true
     */
    String TRUE = "true";

    /**
     * false
     */
    String FALSE = "false";

    // ================ 标点符号相关信息 ================

    /**
     * 空格符
     */
    String SPACE = String.valueOf(CharPool.SPACE);

    /**
     * 反斜杠 \
     */
    String BACKSLASH = String.valueOf(CharPool.BACKSLASH);

    /**
     * 回车符 \r
     */
    String CR = String.valueOf(CharPool.CR);

    /**
     * 减号 -
     */
    String DASHED = String.valueOf(CharPool.DASHED);

    /**
     * 下划线 _
     */
    String UNDERLINE = String.valueOf(CharPool.UNDERLINE);

    /**
     * 花括号（左） {
     */
    String DELIM_START = String.valueOf(CharPool.DELIM_START);

    /**
     * 花括号（右） }
     */
    String DELIM_END = String.valueOf(CharPool.DELIM_END);

    /**
     * 花括号 {}
     */
    String DELIM_ALL = DELIM_START + DELIM_END;

    /**
     * 中括号（左） [
     */
    String BRACKET_START = String.valueOf(CharPool.BRACKET_START);

    /**
     * 中括号（右） ]
     */
    String BRACKET_END = String.valueOf(CharPool.BRACKET_END);

    /**
     * 中括号 []
     */
    String BRACKET_ALL = BRACKET_START + BRACKET_END;

    /**
     * 双引号 "
     */
    String DOUBLE_QUOTES = String.valueOf(CharPool.DOUBLE_QUOTES);

    /**
     * 单引号 '
     */
    String SINGLE_QUOTE = String.valueOf(CharPool.SINGLE_QUOTE);

    /**
     * 与、且 &amp;
     */
    String AMP = String.valueOf(CharPool.AMP);

    /**
     * 艾特 &#064;
     */
    String AT = String.valueOf(CharPool.AT);

    /**
     * 制表符 \t
     */
    String TAB = String.valueOf(CharPool.TAB);

    /**
     * 换行符 \n
     */
    String LF = String.valueOf(CharPool.LF);

    /**
     * 强制前端换行符: &lt;br/&gt;，若 {@link #LF} 在前端显示不生效，则可使用该换行符
     */
    String LABEL_BR = "<br/>";

    /**
     * 斜杠 /
     */
    String SLASH = String.valueOf(CharPool.SLASH);

    /**
     * 冒号 :
     */
    String COLON = String.valueOf(CharPool.COLON);

    /**
     * 星号 *
     */
    String ASTERISK = "*";

    /**
     * 空字符串
     */
    String EMPTY = "";

    /**
     * 逗号 ,
     */
    String COMMA = String.valueOf(CharPool.COMMA);

    /**
     * 点 .
     */
    String DOT = String.valueOf(CharPool.DOT);

    /**
     * 中文顿号 、
     */
    String CHINESE_DOT = "、";

    /**
     * 单书名号（左）&lt;
     */
    String SINGLE_BOOK_NAME_LEFT = "<";

    /**
     * 单书名号（右）&gt;
     */
    String SINGLE_BOOK_NAME_RIGHT = ">";

    /**
     * 单书名号 &lt;&gt;
     */
    String SINGLE_BOOK_NAME = "<>";

    /**
     * 双书名号（左）《
     */
    String DOUBLE_BOOK_NAME_LEFT = "《";

    /**
     * 双书名号（右）《
     */
    String DOUBLE_BOOK_NAME_RIGHT = "》";

    /**
     * 双书名号 《》
     */
    String DOUBLE_BOOK_NAME = "《》";

    /**
     * 竖线（vertical bar） |
     */
    String VB = "|";

    /**
     * 加号 +
     */
    String PLUS_SIGN = "+";

    /**
     * 双斜杆 //
     */
    String DOUBLE_SLASH = "//";

    /**
     * 美元符 $
     */
    String DOLLAR = "$";

    /**
     * 美元符 + 花括号（左） ${
     */
    String DOLLAR_AND_DELIM_START = DOLLAR + DELIM_START;

}

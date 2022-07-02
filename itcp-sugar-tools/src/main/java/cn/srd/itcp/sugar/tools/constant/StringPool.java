package cn.srd.itcp.sugar.tools.constant;

/**
 * 字符串常量池
 *
 * @author wjm
 * @date 2021/2/1 20:38
 */
public interface StringPool {

    String SPACE = String.valueOf(CharPool.SPACE);
    String BACKSLASH = String.valueOf(CharPool.BACKSLASH);
    String CR = String.valueOf(CharPool.CR);
    String DASHED = String.valueOf(CharPool.DASHED);
    String UNDERLINE = String.valueOf(CharPool.UNDERLINE);
    String DELIM_START = String.valueOf(CharPool.DELIM_START);
    String DELIM_END = String.valueOf(CharPool.DELIM_END);
    String BRACKET_START = String.valueOf(CharPool.BRACKET_START);
    String BRACKET_END = String.valueOf(CharPool.BRACKET_END);
    String DOUBLE_QUOTES = String.valueOf(CharPool.DOUBLE_QUOTES);
    String SINGLE_QUOTE = String.valueOf(CharPool.SINGLE_QUOTE);
    String AMP = String.valueOf(CharPool.AMP);
    String AT = String.valueOf(CharPool.AT);

    /**
     * 制表符 "\t"
     */
    String TAB = String.valueOf(CharPool.TAB);

    /**
     * 换行符 "\n"
     */
    String LF = String.valueOf(CharPool.LF);

    /**
     * 斜杠 "/"
     */
    String SLASH = String.valueOf(CharPool.SLASH);

    /**
     * 冒号 ":"
     */
    String COLON = String.valueOf(CharPool.COLON);

    /**
     * 空字符串 ""
     */
    String EMPTY = "";

    /**
     * 逗号 ","
     */
    String COMMA = String.valueOf(CharPool.COMMA);

    /**
     * 点 "."
     */
    String DOT = String.valueOf(CharPool.DOT);

    /**
     * 中文顿号 "、"
     */
    String CHINESE_DOT = "、";

    /**
     * 单书名号（左）"<"
     */
    String SINGLE_BOOK_NAME_LEFT = "<";

    /**
     * 单书名号（右）">"
     */
    String SINGLE_BOOK_NAME_RIGHT = ">";

    /**
     * 单书名号
     */
    String SINGLE_BOOK_NAME = "<>";

    /**
     * 双书名号（左）"《"
     */
    String DOUBLE_BOOK_NAME_LEFT = "《";

    /**
     * 双书名号（右）"《"
     */
    String DOUBLE_BOOK_NAME_RIGHT = "》";

    /**
     * 双书名号
     */
    String DOUBLE_BOOK_NAME = "《》";

}

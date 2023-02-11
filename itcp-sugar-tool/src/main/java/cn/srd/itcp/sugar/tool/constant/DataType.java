package cn.srd.itcp.sugar.tool.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据类型
 *
 * @author wjm
 * @since 2023-02-11 11:36:22
 */
@Getter
@AllArgsConstructor
public enum DataType {

    /**
     * byte
     */
    BYTE("byte", "Byte", "BYTE"),

    /**
     * short
     */
    SHORT("short", "Short", "SHORT"),

    /**
     * int
     */
    INT("int", "Int", "INT"),

    /**
     * integer
     */
    INTEGER("integer", "Integer", "INTEGER"),

    /**
     * long
     */
    LONG("long", "Long", "LONG"),

    /**
     * float
     */
    FLOAT("float", "Float", "FLOAT"),

    /**
     * double
     */
    DOUBLE("double", "Double", "DOUBLE"),

    /**
     * boolean
     */
    BOOLEAN("boolean", "Boolean", "BOOLEAN"),

    /**
     * char
     */
    CHAR("char", "Char", "CHAR"),

    /**
     * string
     */
    STRING("string", "String", "STRING"),

    /**
     * array
     */
    ARRAY("array", "Array", "ARRAY"),

    /**
     * object
     */
    OBJECT("object", "Object", "OBJECT"),
    ;

    private final String lowerCaseAll;
    private final String upperCaseFirst;
    private final String upperCaseAll;

}

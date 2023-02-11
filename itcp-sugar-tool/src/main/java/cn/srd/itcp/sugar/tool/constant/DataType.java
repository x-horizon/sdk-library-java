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

    BYTE("byte", "Byte", "BYTE"),
    SHORT("short", "Short", "SHORT"),
    INT("int", "Int", "INT"),
    INTEGER("integer", "Integer", "INTEGER"),
    LONG("long", "Long", "LONG"),
    FLOAT("float", "Float", "FLOAT"),
    DOUBLE("double", "Double", "DOUBLE"),
    BOOLEAN("boolean", "Boolean", "BOOLEAN"),
    CHAR("char", "Char", "CHAR"),
    STRING("string", "String", "STRING"),
    ARRAY("array", "Array", "ARRAY"),
    OBJECT("object", "Object", "OBJECT"),
    ;

    private final String lowerCaseAll;
    private final String upperCaseFirst;
    private final String upperCaseAll;

}

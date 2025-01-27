package cn.library.java.contract.constant.java;

import lombok.Getter;

/**
 * java data type
 *
 * @author wjm
 * @since 2023-02-11 11:36
 */
@Getter
public enum JavaDataType {

    BYTE("byte", "Byte", "BYTE"),
    SHORT("short", "Short", "SHORT"),
    INT("int", "Int", "INT", "integer", "Integer", "INTEGER"),
    LONG("long", "Long", "LONG"),
    FLOAT("float", "Float", "FLOAT"),
    DOUBLE("double", "Double", "DOUBLE"),
    BOOLEAN("bool", "Bool", "BOOL", "boolean", "Boolean", "BOOLEAN"),
    CHAR("char", "Char", "CHAR"),
    STRING("str", "Str", "STR", "string", "String", "STRING"),
    ARRAY("array", "Array", "ARRAY"),
    OBJECT("object", "Object", "OBJECT"),

    ;

    JavaDataType(String... names) {
        this.names = names;
    }

    private final String[] names;

}
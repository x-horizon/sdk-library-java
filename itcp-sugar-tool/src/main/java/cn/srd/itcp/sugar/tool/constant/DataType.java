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
    BYTE(DataTypeConstant.LowerCaseAll.BYTE, DataTypeConstant.UpperCaseFirst.BYTE, DataTypeConstant.UpperCaseAll.BYTE),

    /**
     * short
     */
    SHORT(DataTypeConstant.LowerCaseAll.SHORT, DataTypeConstant.UpperCaseFirst.SHORT, DataTypeConstant.UpperCaseAll.SHORT),

    /**
     * int
     */
    INT(DataTypeConstant.LowerCaseAll.INT, DataTypeConstant.UpperCaseFirst.INT, DataTypeConstant.UpperCaseAll.INT),

    /**
     * integer
     */
    INTEGER(DataTypeConstant.LowerCaseAll.INTEGER, DataTypeConstant.UpperCaseFirst.INTEGER, DataTypeConstant.UpperCaseAll.INTEGER),

    /**
     * long
     */
    LONG(DataTypeConstant.LowerCaseAll.LONG, DataTypeConstant.UpperCaseFirst.LONG, DataTypeConstant.UpperCaseAll.LONG),

    /**
     * float
     */
    FLOAT(DataTypeConstant.LowerCaseAll.FLOAT, DataTypeConstant.UpperCaseFirst.FLOAT, DataTypeConstant.UpperCaseAll.FLOAT),

    /**
     * double
     */
    DOUBLE(DataTypeConstant.LowerCaseAll.DOUBLE, DataTypeConstant.UpperCaseFirst.DOUBLE, DataTypeConstant.UpperCaseAll.DOUBLE),

    /**
     * boolean
     */
    BOOLEAN(DataTypeConstant.LowerCaseAll.BOOLEAN, DataTypeConstant.UpperCaseFirst.BOOLEAN, DataTypeConstant.UpperCaseAll.BOOLEAN),

    /**
     * char
     */
    CHAR(DataTypeConstant.LowerCaseAll.CHAR, DataTypeConstant.UpperCaseFirst.CHAR, DataTypeConstant.UpperCaseAll.CHAR),

    /**
     * string
     */
    STRING(DataTypeConstant.LowerCaseAll.STRING, DataTypeConstant.UpperCaseFirst.STRING, DataTypeConstant.UpperCaseAll.STRING),

    /**
     * array
     */
    ARRAY(DataTypeConstant.LowerCaseAll.ARRAY, DataTypeConstant.UpperCaseFirst.ARRAY, DataTypeConstant.UpperCaseAll.ARRAY),

    /**
     * object
     */
    OBJECT(DataTypeConstant.LowerCaseAll.OBJECT, DataTypeConstant.UpperCaseFirst.OBJECT, DataTypeConstant.UpperCaseAll.OBJECT),

    ;

    private final String lowerCaseAll;
    private final String upperCaseFirst;
    private final String upperCaseAll;

}

// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.contract.component.database.postgresql;

import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.text.Strings;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <pre>
 * the postgresql jsonb sql generator.
 * you should add the function "cmd/sql/jsonb_function.sql" to your postgresql database before using it.
 * </pre>
 *
 * @author wjm
 * @since 2024-04-18 20:34
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostgresqlJsonbSQL {

    private static final String EMPTY_LIST_JSONB = "'[]'::JSONB";

    private static final String LIST_NUMBER_EQUAL_FUNCTION_NAME = "jsonb_list_number_equal";

    private static final String LIST_NUMBER_IN_FUNCTION_NAME = "jsonb_list_number_in";

    private static final String LIST_STRING_EQUAL_FUNCTION_NAME = "jsonb_list_string_equal";

    private static final String LIST_STRING_IN_FUNCTION_NAME = "jsonb_list_string_in";

    private static final String LIST_STRING_LIKE_FUNCTION_NAME = "jsonb_list_string_like";

    private static final String LIST_OBJECT_KEY_ID_EQUAL_FUNCTION_NAME = "jsonb_list_object_key_id_equal";

    private static final String LIST_OBJECT_KEY_ID_IN_FUNCTION_NAME = "jsonb_list_object_key_id_in";

    private static final String LIST_OBJECT_KEY_TYPE_EQUAL_FUNCTION_NAME = "jsonb_list_object_key_type_equal";

    private static final String LIST_OBJECT_KEY_TYPE_IN_FUNCTION_NAME = "jsonb_list_object_key_type_in";

    /**
     * the condition sql example: names = '[]'::JSONB
     *
     * @param columnName the column name
     * @return the condition sql
     */
    public static String getEmptyListEqual(String columnName) {
        return Strings.format("{} = {}", columnName, EMPTY_LIST_JSONB);
    }

    /**
     * the condition sql example: jsonb_list_number_equal(types, 1)
     *
     * @param columnName the column name
     * @param value      the value
     * @return the condition sql
     */
    public static <T extends Number> String getListNumberEqual(String columnName, T value) {
        return getListObjectEqualFunctionTemplate(LIST_NUMBER_EQUAL_FUNCTION_NAME, columnName, value);
    }

    /**
     * the condition sql example: jsonb_list_number_in(types, ARRAY [1,2])
     *
     * @param columnName the column name
     * @param values     the values
     * @return the condition sql
     */
    public static <T extends Number> String getListNumberIn(String columnName, List<T> values) {
        return geyListObjectInFunctionTemplate(LIST_NUMBER_IN_FUNCTION_NAME, columnName, values);
    }

    /**
     * the condition sql example: jsonb_list_string_equal(names, '"jimmy"')
     *
     * @param columnName the column name
     * @param value      the value
     * @return the condition sql
     */
    public static String getListStringEqual(String columnName, String value) {
        return getListObjectEqualFunctionTemplate(LIST_STRING_EQUAL_FUNCTION_NAME, columnName, Strings.format("'\"{}\"'", value));
    }

    /**
     * the condition sql example: jsonb_list_number_in(types, ARRAY [1,2])
     *
     * @param columnName the column name
     * @param values     the values
     * @return the condition sql
     */
    public static String getListStringIn(String columnName, List<String> values) {
        return geyListObjectInFunctionTemplate(LIST_STRING_IN_FUNCTION_NAME, columnName, values);
    }

    /**
     * the condition sql example: jsonb_list_string_like(names, 'jimmy')
     *
     * @param columnName the column name
     * @param value      the value
     * @return the condition sql
     */
    public static String getListStringLike(String columnName, String value) {
        return getListObjectEqualFunctionTemplate(LIST_STRING_LIKE_FUNCTION_NAME, columnName, Strings.format("'{}'", value));
    }

    /**
     * the condition sql example: jsonb_list_object_key_id_equal(id_info, 1)
     *
     * @param columnName the column name
     * @param value      the value
     * @return the condition sql
     */
    public static <T extends Number> String getListObjectKeyIdEqual(String columnName, T value) {
        return getListObjectEqualFunctionTemplate(LIST_OBJECT_KEY_ID_EQUAL_FUNCTION_NAME, columnName, value);
    }

    /**
     * <pre>
     * 1. if the values is empty, the condition sql example: id_infos = '[]'::JSONB
     * 2. if the values is not empty, the condition sql example: jsonb_list_object_key_id_in(id_infos, ARRAY [1,2])
     * </pre>
     *
     * @param columnName the column name
     * @param values     the values
     * @return the condition sql
     */
    public static <T extends Number> String getListObjectKeyIdIn(String columnName, List<T> values) {
        return geyListObjectInFunctionTemplate(LIST_OBJECT_KEY_ID_IN_FUNCTION_NAME, columnName, values);
    }

    /**
     * the condition sql example: jsonb_list_object_key_type_equal(type_info, 1)
     *
     * @param columnName the column name
     * @param value      the value
     * @return the condition sql
     */
    public static <T extends Number> String getListObjectKeyTypeEqual(String columnName, T value) {
        return getListObjectEqualFunctionTemplate(LIST_OBJECT_KEY_TYPE_EQUAL_FUNCTION_NAME, columnName, value);
    }

    /**
     * <pre>
     * 1. if the values is empty, the condition sql example: type_infos = '[]'::JSONB
     * 2. if the values is not empty, the condition sql example: jsonb_list_object_key_type_in(type_infos, ARRAY [1,2])
     * </pre>
     *
     * @param columnName the column name
     * @param values     the values
     * @return the condition sql
     */
    public static <T extends Number> String getListObjectKeyTypeIn(String columnName, List<T> values) {
        return geyListObjectInFunctionTemplate(LIST_OBJECT_KEY_TYPE_IN_FUNCTION_NAME, columnName, values);
    }

    private static <T> String getListObjectEqualFunctionTemplate(String functionName, String columnName, T value) {
        return Strings.format("{}({}, {})", functionName, columnName, value);
    }

    private static <T> String geyListObjectInFunctionTemplate(String functionName, String columnName, List<T> values) {
        if (Nil.isEmpty(values)) {
            return getEmptyListEqual(columnName);
        }
        return Strings.format("{}({}, ARRAY [{}])", functionName, columnName, Strings.joinWithComma(values));
    }

}
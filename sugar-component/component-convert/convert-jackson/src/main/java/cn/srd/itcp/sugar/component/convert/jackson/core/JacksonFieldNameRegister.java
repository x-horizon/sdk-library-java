package cn.srd.itcp.sugar.component.convert.jackson.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * the field name in json and the field name on class mapping
 *
 * @author wjm
 * @since 2023-06-17 14:42:11
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JacksonFieldNameRegister {

    /**
     * singleton instance
     */
    private static final JacksonFieldNameRegister INSTANCE = new JacksonFieldNameRegister();

    /**
     * the field name in json mapping the field name on class map
     */
    private static final Map<String, String> JSON_FIELD_NAME_MAPPING_CLASS_FIELD_NAME_MAP = new ConcurrentHashMap<>();

    /**
     * get singleton instance
     *
     * @return the singleton instance
     */
    public static JacksonFieldNameRegister getInstance() {
        return INSTANCE;
    }

    /**
     * register {@link #JSON_FIELD_NAME_MAPPING_CLASS_FIELD_NAME_MAP}
     *
     * @param jsonFieldName  the json field name
     * @param classFieldName the class field name
     * @return this
     */
    public JacksonFieldNameRegister register(String jsonFieldName, String classFieldName) {
        JSON_FIELD_NAME_MAPPING_CLASS_FIELD_NAME_MAP.put(jsonFieldName, classFieldName);
        return this;
    }

    /**
     * get the field name on class from the field name in json mapping the field name on class map
     *
     * @param jsonFieldName the field name in json
     * @return the field name on class
     */
    public String getClassFieldName(String jsonFieldName) {
        return JSON_FIELD_NAME_MAPPING_CLASS_FIELD_NAME_MAP.get(jsonFieldName);
    }

}

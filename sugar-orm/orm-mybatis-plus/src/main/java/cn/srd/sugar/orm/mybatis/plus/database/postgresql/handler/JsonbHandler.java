package cn.srd.sugar.orm.mybatis.plus.database.postgresql.handler;

import cn.srd.itcp.sugar.tool.core.CollectionsUtil;
import cn.srd.sugar.tool.convert.all.core.Converts;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import org.postgresql.util.PGobject;

import java.util.ArrayList;
import java.util.List;

/**
 * postgresql JSONB 类型处理器
 *
 * @author wjm
 * @since 2022-09-07 10:35:42
 */
public interface JsonbHandler<T> {

    /**
     * postgresql 的 JSONB 类型名字
     */
    String POSTGRESQL_TYPE_JSONB = "jsonb";

    /**
     * 获取转换目标类的类型
     *
     * @return 目标类的类型
     */
    default Class<T> getTargetClass() {
        return null;
    }

    /**
     * 将 java 的 {@link Object} 类型转换为 {@link PGobject}
     *
     * @param object 输入数据
     * @return 转换结果集
     */
    @SneakyThrows
    default PGobject convertObjectToJsonb(Object object) {
        PGobject pgObject = new PGobject();
        pgObject.setType(POSTGRESQL_TYPE_JSONB);
        pgObject.setValue(Converts.withJackson().toString(object));
        return pgObject;
    }

    /**
     * 将 postgresql 中 JSONB 类型的数据转换为 java 的 {@link JSONArray} 类型
     *
     * @param jsonbString postgresql 中 JSONB 类型的数据
     * @return 转换结果集
     */
    default JSONArray convertJsonbStringToJsonArray(String jsonbString) {
        return JSON.parseArray(jsonbString);
    }

    /**
     * 将 postgresql 中 JSONB 类型的数据转换为 java 的 {@link JSONObject} 类型
     *
     * @param jsonbString postgresql 中 JSONB 类型的数据
     * @return 转换结果集
     */
    default JSONObject convertJsonbStringToJsonObject(String jsonbString) {
        return JSON.parseObject(jsonbString);
    }

    /**
     * 将 postgresql 中 JSONB 类型的数据转换为 java 的 {@link List<T>} 类型
     *
     * @param jsonbString postgresql 中 JSONB 类型的数据
     * @return 转换结果集
     */
    default List<T> convertJsonbStringToList(String jsonbString) {
        return CollectionsUtil.isBlankOrEmptyJsonArray(jsonbString) ? new ArrayList<>() : Converts.withJackson().toBeans(jsonbString, getTargetClass());
    }

    /**
     * 将 postgresql 中 JSONB 类型的数据转换为 java 的 {@link T} 类型
     *
     * @param jsonbString postgresql 中 JSONB 类型的数据
     * @return 转换结果集
     */
    @SneakyThrows
    default T convertJsonbStringToObject(String jsonbString) {
        return CollectionsUtil.isBlankOrEmptyJsonObject(jsonbString) ? null : Converts.withJackson().toBean(jsonbString, getTargetClass());
    }

}


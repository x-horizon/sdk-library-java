package cn.library.java.orm.mybatis.contract.postgresql.handler;

import cn.srd.library.java.tool.convert.all.Converts;
import cn.srd.library.java.tool.lang.collection.Collections;
import lombok.SneakyThrows;
import org.postgresql.util.PGobject;

import java.util.List;

/**
 * postgresql JSONB 类型处理器
 *
 * @author wjm
 * @since 2022-09-07 10:35:42
 */
public interface JdbcJsonbTypeHandler2<T> {

    /**
     * postgresql 的 JSONB 类型名字 TODO wjm 抽象为枚举的策略字段类型获取
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
     * 将 postgresql 中 JSONB 类型的数据转换为 java 的 {@link List<T>} 类型
     *
     * @param jsonbString postgresql 中 JSONB 类型的数据
     * @return 转换结果集
     */
    default List<T> convertJsonbStringToList(String jsonbString) {
        return Collections.isBlankOrEmptyArrayString(jsonbString) ? Collections.newArrayList() : Converts.withJackson().toBeans(jsonbString, getTargetClass());
    }

    /**
     * 将 postgresql 中 JSONB 类型的数据转换为 java 的 {@link T} 类型
     *
     * @param jsonbString postgresql 中 JSONB 类型的数据
     * @return 转换结果集
     */
    @SneakyThrows
    default T convertJsonbStringToObject(String jsonbString) {
        return Collections.isBlankOrEmptyMapString(jsonbString) ? null : Converts.withJackson().toBean(jsonbString, getTargetClass());
    }

}


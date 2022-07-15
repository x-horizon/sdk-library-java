package cn.srd.itcp.sugar.mybatis.plus.support;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.postgresql.util.PGobject;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

/**
 * 数据类型转换：{@link UUID} => {@link String}
 *
 * @author wjm
 * @date 2022-07-12
 */
public class PostgresqlJSONObjectToStringTypeHandler extends BaseTypeHandler<JSONObject> {

    /**
     * 定义设置参数时，如何把Java类型的参数转换为对应的数据库类型
     *
     * @param ps
     * @param columnIndex
     * @param parameter   Java 类型的参数值
     * @param jdbcType
     */
    @Override
    @SneakyThrows
    public void setNonNullParameter(PreparedStatement ps, int columnIndex, JSONObject parameter, JdbcType jdbcType) {
        if (null != parameter) {
            PGobject pgobject = new PGobject();
            pgobject.setType("json");
            pgobject.setValue(parameter.toJSONString());
        }
    }

    /**
     * 定义通过字段名称获取字段数据时，如何把数据库类型转换为对应的Java类型
     *
     * @param rs
     * @param columnName
     * @return
     */
    @Override
    @SneakyThrows
    public JSONObject getNullableResult(ResultSet rs, String columnName) {
        return JSON.parseObject(columnName);
    }

    /**
     * 定义通过字段索引获取字段数据时，如何把数据库类型转换为对应的Java类型
     *
     * @param rs
     * @param columnIndex
     * @return
     */
    @Override
    public JSONObject getNullableResult(ResultSet rs, int columnIndex) {
        return null;
    }

    /**
     * 定义调用存储过程后，如何把数据库类型转换为对应的Java类型
     *
     * @param cs
     * @param columnIndex
     * @return
     */
    @Override
    public JSONObject getNullableResult(CallableStatement cs, int columnIndex) {
        return null;
    }

}

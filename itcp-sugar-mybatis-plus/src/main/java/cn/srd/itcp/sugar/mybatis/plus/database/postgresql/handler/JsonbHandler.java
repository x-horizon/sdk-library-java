package cn.srd.itcp.sugar.mybatis.plus.database.postgresql.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import org.postgresql.util.PGobject;

public interface JsonbHandler {

    String POSTGRESQL_TYPE = "jsonb";

    @SneakyThrows
    default PGobject convertObjectToJsonb(Object object) {
        PGobject pgObject = new PGobject();
        pgObject.setType(POSTGRESQL_TYPE);
        pgObject.setValue(JSON.toJSONString(object));
        return pgObject;
    }

    default JSONArray convertJsonbStringToJsonArray(String jsonbString) {
        return JSON.parseArray(jsonbString);
    }

    default JSONObject convertJsonbStringToJsonObject(String jsonbString) {
        return JSON.parseObject(jsonbString);
    }

}


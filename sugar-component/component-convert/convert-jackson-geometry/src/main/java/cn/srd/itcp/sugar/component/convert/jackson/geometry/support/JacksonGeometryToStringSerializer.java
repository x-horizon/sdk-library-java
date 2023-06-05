package cn.srd.itcp.sugar.component.convert.jackson.geometry.support;

import cn.srd.itcp.sugar.component.geometry.core.GeometryUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.SneakyThrows;
import org.locationtech.jts.geom.Geometry;

/**
 * Jackson 序列化处理器：{@link Geometry} =&gt; String
 *
 * @author wjm
 * @since 2023-03-15 09:51:11
 */
public class JacksonGeometryToStringSerializer extends JsonSerializer<Geometry> {

    @Override
    @SneakyThrows
    public void serialize(Geometry prepareToSerializer, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeObject(GeometryUtil.toString(prepareToSerializer));
    }

}
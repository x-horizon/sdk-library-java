package cn.srd.itcp.sugar.component.convert.jackson.geometry.support;

import cn.srd.itcp.sugar.component.geometry.core.GeometryUtil;
import com.fasterxml.jackson.databind.util.StdConverter;
import org.locationtech.jts.geom.Geometry;

/**
 * Jackson 反序列化处理器：String =&gt; {@link Geometry}
 *
 * @author wjm
 * @since 2023-03-15 09:51:11
 */
public class JacksonStringToGeometryDeserializer extends StdConverter<String, Geometry> {

    @Override
    public Geometry convert(String value) {
        return GeometryUtil.toGeometry(value);
    }

}

package cn.srd.library.tool.geometry.jackson.support;

import cn.srd.library.tool.geometry.common.core.GeometryUtil;
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

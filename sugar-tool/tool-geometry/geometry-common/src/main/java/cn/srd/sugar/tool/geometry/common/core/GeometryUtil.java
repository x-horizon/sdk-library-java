package cn.srd.sugar.tool.geometry.common.core;

import cn.srd.sugar.tool.lang.core.object.Objects;
import lombok.SneakyThrows;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKTReader;

/**
 * Geometry 工具
 *
 * @author wjm
 * @since 2023-03-14 15:39:11
 */
public class GeometryUtil {

    private GeometryUtil() {
    }

    /**
     * 二维
     */
    private static final int TWO_DIMENSION = 2;

    /**
     * 三维
     */
    private static final int THREE_DIMENSION = 3;

    /**
     * {@link WKTReader} singleton
     */
    private static final WKTReader WKT_READER = new WKTReader();

    /**
     * {@link Geometry} =&gt; {@link String}
     *
     * @param input 输入
     * @return 输出
     */
    public static String toString(Geometry input) {
        if (Objects.isNull(input)) {
            return null;
        }
        return input.toText();
    }

    /**
     * {@link String} =&gt; {@link Geometry}
     *
     * @param input 输入
     * @return 输出
     */
    @SneakyThrows
    public static Geometry toGeometry(String input) {
        if (Objects.isBlank(input)) {
            return null;
        }
        return WKT_READER.read(input);
    }

    /**
     * 获取维度
     *
     * @param input 输入
     * @return 维度
     */
    public static int getDimension(Geometry input) {
        Objects.requireNotNull(input);
        return !Double.isNaN(input.getCoordinates()[0].z) ? THREE_DIMENSION : TWO_DIMENSION;
    }

}

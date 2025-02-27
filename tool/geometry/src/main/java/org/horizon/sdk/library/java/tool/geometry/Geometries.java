package org.horizon.sdk.library.java.tool.geometry;

import org.horizon.sdk.library.java.contract.model.throwable.LibraryJavaInternalException;
import org.horizon.sdk.library.java.tool.lang.functional.Assert;
import org.horizon.sdk.library.java.tool.lang.object.Nil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKTReader;

/**
 * toolkit for geometry
 *
 * @author wjm
 * @since 2023-03-14 15:39
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Geometries {

    private static final int TWO_DIMENSION = 2;

    private static final int THREE_DIMENSION = 3;

    private static final WKTReader WKT_READER = new WKTReader();

    public static String toString(Geometry input) {
        return Nil.isNull(input) ? null : input.toText();
    }

    @SneakyThrows
    public static Geometry toGeometry(String input) {
        return Nil.isBlank(input) ? null : WKT_READER.read(input);
    }

    public static int getDimension(Geometry input) {
        Assert.of(LibraryJavaInternalException.class).throwsIfNull(input);
        return !Double.isNaN(input.getCoordinates()[0].z) ? THREE_DIMENSION : TWO_DIMENSION;
    }

}
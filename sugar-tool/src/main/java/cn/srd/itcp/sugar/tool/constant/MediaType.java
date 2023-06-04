package cn.srd.itcp.sugar.tool.constant;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

/**
 * 媒体类型相关常量
 *
 * @author wjm
 * @since 2022-08-26 16:52:18
 */
public class MediaType {

    /**
     * private block constructor
     */
    private MediaType() {
    }

    /**
     * 媒体类型：image/png
     */
    public static final String IMAGE_PNG = "image/png";

    /**
     * 媒体类型：image/jpg
     */
    public static final String IMAGE_JPG = "image/jpg";

    /**
     * 媒体类型：image/jpeg
     */
    public static final String IMAGE_JPEG = "image/jpeg";

    /**
     * 媒体类型：image/bmp
     */
    public static final String IMAGE_BMP = "image/bmp";

    /**
     * 媒体类型：image/gif
     */
    public static final String IMAGE_GIF = "image/gif";

    /**
     * 根据前缀获取实际类型
     *
     * @param prefix 前缀
     * @return 实际类型
     */
    public static String getExtension(String prefix) {
        return switch (prefix) {
            case IMAGE_PNG -> "png";
            case IMAGE_JPG -> "jpg";
            case IMAGE_JPEG -> "jpeg";
            case IMAGE_BMP -> "bmp";
            case IMAGE_GIF -> "gif";
            default -> "";
        };
    }

    /**
     * 图片类型
     */
    public static final List<String> IMAGE_TYPE = new ArrayList<>() {
        @Serial private static final long serialVersionUID = -4524320491600249834L;

        {
            add("bmp");
            add("gif");
            add("jpg");
            add("jpeg");
            add("png");
        }
    };

}
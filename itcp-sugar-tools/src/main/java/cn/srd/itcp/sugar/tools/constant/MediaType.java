package cn.srd.itcp.sugar.tools.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 媒体类型相关常量
 *
 * @author wjm
 * @since 2022-08-26 16:52:18
 */
public class MediaType {

    public static final String IMAGE_PNG = "image/png";

    public static final String IMAGE_JPG = "image/jpg";

    public static final String IMAGE_JPEG = "image/jpeg";

    public static final String IMAGE_BMP = "image/bmp";

    public static final String IMAGE_GIF = "image/gif";

    public static String getExtension(String prefix) {
        switch (prefix) {
            case IMAGE_PNG:
                return "png";
            case IMAGE_JPG:
                return "jpg";
            case IMAGE_JPEG:
                return "jpeg";
            case IMAGE_BMP:
                return "bmp";
            case IMAGE_GIF:
                return "gif";
            default:
                return "";
        }
    }

    /**
     * 图片类型
     */
    public static final List<String> IMAGE_TYPE = new ArrayList<String>() {{
        add("bmp");
        add("gif");
        add("jpg");
        add("jpeg");
        add("png");
    }};

}
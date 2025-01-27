package cn.library.java.contract.constant.file;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wjm
 * @since 2024-07-22 15:49
 */
@Getter
@AllArgsConstructor
public enum FileMimeType {

    BMP(PictureMimeType.BMP.getValue(), PictureMimeType.BMP.getExtensionType()),
    GIF(PictureMimeType.GIF.getValue(), PictureMimeType.GIF.getExtensionType()),
    JPEG(PictureMimeType.JPEG.getValue(), PictureMimeType.JPEG.getExtensionType()),
    PNG(PictureMimeType.PNG.getValue(), PictureMimeType.PNG.getExtensionType()),
    SVG(PictureMimeType.SVG.getValue(), PictureMimeType.SVG.getExtensionType()),

    CSS("text/css", FileExtensionType.CSS),
    HTML("text/html", FileExtensionType.HTML),
    TXT("text/plain", FileExtensionType.TXT),

    EXE("application/x-msdownload", FileExtensionType.EXE),
    JS("application/javascript", FileExtensionType.JS),
    JSON("application/json", FileExtensionType.JSON),
    PDF("application/pdf", FileExtensionType.PDF),
    XML("application/xml", FileExtensionType.XML),
    ZIP("application/zip", FileExtensionType.ZIP),

    ;

    private final String value;

    private final FileExtensionType extensionType;

}
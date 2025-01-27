package cn.library.java.contract.constant.file;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wjm
 * @since 2024-07-17 18:29
 */
@Getter
@AllArgsConstructor
public enum FileExtensionType {

    BMP("bmp"),
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg"),

    M4A("m4a"),
    MP3("mp3"),
    WAV("wav"),
    WMA("wma"),
    WMV("wmv"),

    AVI("avi"),
    FLV("flv"),
    MKV("mkv"),
    MOV("mov"),
    MP4("mp4"),
    MPEG("mpeg"),

    CSS("css"),
    CSV("csv"),
    DOC("doc"),
    DOCX("docx"),
    EXE("exe"),
    HTML("html"),
    JS("js"),
    JSON("json"),
    PDF("pdf"),
    PPT("ppt"),
    PPTX("pptx"),
    RAR("rar"),
    TXT("txt"),
    XLS("xls"),
    XLSX("xlsx"),
    XML("xml"),
    ZIP("zip"),

    ;

    private final String value;

}
// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.contract.constant.file;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wjm
 * @since 2024-07-17 18:29
 */
@Getter
@AllArgsConstructor
public enum FileExtensionType {

    BMP(100, "bmp"),
    GIF(101, "gif"),
    JPG(102, "jpg"),
    JPEG(103, "jpeg"),
    PNG(104, "png"),
    SVG(105, "svg"),

    M4A(200, "m4a"),
    MP3(201, "mp3"),
    WAV(202, "wav"),
    WMA(203, "wma"),
    WMV(204, "wmv"),

    AVI(300, "avi"),
    FLV(301, "flv"),
    MKV(302, "mkv"),
    MOV(303, "mov"),
    MP4(304, "mp4"),
    MPEG(305, "mpeg"),

    ZIP(400, "zip"),
    RAR(401, "rar"),

    CSV(500, "csv"),
    DOC(501, "doc"),
    DOCX(502, "docx"),
    PDF(503, "pdf"),
    PPT(504, "ppt"),
    PPTX(505, "pptx"),
    TXT(506, "txt"),
    XLS(507, "xls"),
    XLSX(508, "xlsx"),

    ;

    private final int code;

    private final String description;

}
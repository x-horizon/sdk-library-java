// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.content.analysis;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.tika.Tika;

/**
 * @author wjm
 * @since 2024-07-22 16:08
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContentAnalysis {

    private static final Tika TIKA = new Tika();

    public static String getMimeType(byte[] value) {
        return TIKA.detect(value);
    }

    public static String getMimeType(String value) {
        return TIKA.detect(value);
    }

}
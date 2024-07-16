// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.oss.contract.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wjm
 * @since 2024-07-16 19:45
 */
@Getter
@AllArgsConstructor
public enum OssType {

    MINIO(1),

    ;

    private final int code;

}
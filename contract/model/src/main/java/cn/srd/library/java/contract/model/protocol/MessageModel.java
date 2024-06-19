// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.contract.model.protocol;

import cn.srd.library.java.contract.constant.web.HttpStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

/**
 * message specification
 *
 * @author wjm
 * @since 2024-05-24 15:20
 */
@Data
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageModel<T extends Serializable> implements TransportModel<T> {

    @Serial private static final long serialVersionUID = -3530554978681104595L;

    @Builder.Default
    private Integer status = HttpStatus.OK.getStatus();

    @Builder.Default
    private String message = HttpStatus.OK.getDescription();

    private T data;

}
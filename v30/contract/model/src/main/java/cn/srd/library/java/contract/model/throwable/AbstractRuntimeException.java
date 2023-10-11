// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.contract.model.throwable;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.StandardException;

import java.io.Serial;

/**
 * @author wjm
 * @since 2020-06-07 20:52
 */
@Getter
@Setter
@Accessors(chain = true)
@StandardException
public abstract class AbstractRuntimeException extends RuntimeException {

    @Serial private static final long serialVersionUID = 8499653235633312994L;

    private Integer status;

}

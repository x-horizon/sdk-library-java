// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.contract.model.throwable;

import lombok.experimental.StandardException;

import java.io.Serial;

/**
 * standard business exception
 *
 * @author wjm
 * @since 2020-06-07 20:52
 */
@StandardException
public class RunningException extends AbstractRuntimeException {

    @Serial private static final long serialVersionUID = 3975594528435116604L;

}
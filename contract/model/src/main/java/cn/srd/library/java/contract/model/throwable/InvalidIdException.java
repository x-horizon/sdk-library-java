// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.contract.model.throwable;

import lombok.experimental.StandardException;

import java.io.Serial;

/**
 * invalid id exception
 *
 * @author wjm
 * @since 2024-07-03 10:47
 */
@StandardException
public class InvalidIdException extends AbstractRuntimeException {

    @Serial private static final long serialVersionUID = 8981649768811793539L;

}
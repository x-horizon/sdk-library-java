// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.contract.model.throwable;

import lombok.experimental.StandardException;

import java.io.Serial;

/**
 * data not found exception
 *
 * @author wjm
 * @since 2024-04-16 20:56
 */
@StandardException
public class DataNotFoundException extends AbstractRuntimeException {

    @Serial private static final long serialVersionUID = 4265717372386486126L;

}
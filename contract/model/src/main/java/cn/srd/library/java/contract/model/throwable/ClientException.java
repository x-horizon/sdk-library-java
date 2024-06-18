// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.contract.model.throwable;

import lombok.experimental.StandardException;

import java.io.Serial;

/**
 * standard client exception
 *
 * @author wjm
 * @since 2024-06-18 10:53
 */
@StandardException
public class ClientException extends AbstractRuntimeException {

    @Serial private static final long serialVersionUID = -5061184430024294179L;

}
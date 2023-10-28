// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.contract.model.throwable;

import lombok.experimental.StandardException;

import java.io.Serial;

/**
 * warning exception
 *
 * @author wjm
 * @since 2021-05-06 17:27
 */
@StandardException
public class WarningException extends AbstractRuntimeException {

    @Serial private static final long serialVersionUID = 5255390150143370707L;

}

// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.contract.model.throwable;

import lombok.experimental.StandardException;

import java.io.Serial;

/**
 * @author wjm
 * @since 2021-04-13 11:52
 */
@StandardException
public class UnsupportedException extends AbstractRuntimeException {

    @Serial private static final long serialVersionUID = -6315727057670035084L;

}

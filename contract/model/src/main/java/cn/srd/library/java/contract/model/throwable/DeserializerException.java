// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.contract.model.throwable;

import lombok.experimental.StandardException;

import java.io.Serial;

/**
 * standard deserializer exception
 *
 * @author wjm
 * @since 2021-05-01 14:13
 */
@StandardException
public class DeserializerException extends AbstractRuntimeException {

    @Serial private static final long serialVersionUID = -2587853132749498768L;

}

// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.convert.mapstruct.exception;

import cn.srd.library.java.contract.model.throwable.AbstractRuntimeException;
import lombok.experimental.StandardException;

import java.io.Serial;

/**
 * 未找到转换方法时的异常
 *
 * @author wjm
 * @since 2021/5/1 14:13
 */
@StandardException
@Deprecated
public class MapstructConvertMethodNotFoundException extends AbstractRuntimeException {

    @Serial private static final long serialVersionUID = -6760724713927618055L;

}

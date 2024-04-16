// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.contract.model.base;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;

/**
 * the persistent model define
 *
 * @author wjm
 * @since 2023-11-23 22:54
 */
public interface PO extends POJO {

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    VO toVO();

}
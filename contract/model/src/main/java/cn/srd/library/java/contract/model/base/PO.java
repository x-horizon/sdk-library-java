// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.contract.model.base;

/**
 * the persistent model define
 *
 * @author wjm
 * @since 2023-11-23 22:54
 */
public interface PO extends POJO {

    VO toVO();

}
// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.client.contract.strategy;

/**
 * @author wjm
 * @since 2024-05-30 10:54
 */
public interface UniqueClientIdGenerateStrategy<T> {

    T getId();

}
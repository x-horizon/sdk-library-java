// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.concurrent.actor.my;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.concurrent.ExecutorService;

/**
 * @author wjm
 * @since 2024-09-25 09:03
 */
@Data
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class Dispatcher {

    private final String dispatcherId;

    private final ExecutorService executor;

}
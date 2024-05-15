// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.contract.model.base;

import cn.srd.library.java.orm.contract.model.page.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author wjm
 * @since 2024-04-17 10:42
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class BasePageConditionVO extends PageParam implements Serializable {

    @Serial private static final long serialVersionUID = 2083935475405702780L;

}
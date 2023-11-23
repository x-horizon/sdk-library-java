// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.contract.model.base;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

/**
 * the plain ordinary object define
 *
 * @author wjm
 * @since 2023-11-23 22:56
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class POJO implements Serializable {

    @Serial private static final long serialVersionUID = 5152469842778471348L;

}

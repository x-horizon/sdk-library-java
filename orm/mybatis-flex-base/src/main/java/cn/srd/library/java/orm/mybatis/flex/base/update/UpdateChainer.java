// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.update;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.orm.contract.model.base.PO;
import com.mybatisflex.core.update.UpdateChain;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wjm
 * @since 2023-12-05 23:25
 */
@AllArgsConstructor(access = AccessLevel.MODULE)
@SuppressWarnings(SuppressWarningConstant.UNUSED)
public class UpdateChainer<T extends PO> extends AbstractUpdateChainer<T> {

    @Getter(AccessLevel.PROTECTED) private final UpdateChain<T> nativeUpdateChainer;

}

// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.id;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.tool.lang.text.Strings;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * the invalid type id generator
 *
 * @author wjm
 * @since 2023-11-12 21:06
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdInvalidGenerator implements IdGenerator {

    @Override
    public Object generate(Object entity, String keyColumn) {
        throw new LibraryJavaInternalException(Strings.format("{}could not use invalid id generator to generate id!", ModuleView.ORM_MYBATIS_SYSTEM));
    }

}
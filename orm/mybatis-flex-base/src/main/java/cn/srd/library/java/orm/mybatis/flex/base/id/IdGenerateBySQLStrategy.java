// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.id;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.tool.lang.functional.Assert;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * the sql type id generate strategy
 *
 * @author wjm
 * @see IdConfig
 * @since 2023-11-12 21:06
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IdGenerateBySQLStrategy implements IdGenerateStrategy {

    @Override
    public void validateIdConfig(IdConfig idConfig) {
        warningIfNotDefaultIdGenerator(idConfig.generator());
        Assert.of().setMessage("{}id generator config - current id generate strategy is [{}] but no generated sql specified in [{}]!", ModuleView.ORM_MYBATIS_SYSTEM, this.getClass().getName(), IdConfig.class.getName())
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfBlank(idConfig.generateSQL());
    }

    @Override
    public String buildMybatisFlexKeyConfigValue(IdConfig idConfig) {
        return idConfig.generateSQL();
    }

}
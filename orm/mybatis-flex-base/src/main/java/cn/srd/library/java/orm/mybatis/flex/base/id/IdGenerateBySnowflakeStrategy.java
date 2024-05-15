// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.id;

import cn.srd.library.java.tool.id.snowflake.SnowflakeIds;
import com.mybatisflex.core.keygen.KeyGeneratorFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * the snowflake type id generate strategy
 *
 * @author wjm
 * @see IdConfig
 * @since 2023-11-12 21:06
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IdGenerateBySnowflakeStrategy implements IdGenerateStrategy {

    @Override
    public void registerIdGenerator(IdConfig idConfig) {
        KeyGeneratorFactory.register(getGeneratorName(), (IdGenerator) (entity, keyColumn) -> SnowflakeIds.get());
    }

}
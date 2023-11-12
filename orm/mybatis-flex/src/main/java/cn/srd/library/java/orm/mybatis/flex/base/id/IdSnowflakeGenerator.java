// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.id;

import cn.srd.library.java.tool.id.snowflake.SnowflakeIds;

/**
 * the invalid type id generator
 *
 * @author wjm
 * @since 2023-11-12 21:06
 */
public class IdSnowflakeGenerator implements IdGenerator {

    @Override
    public Object generate(Object entity, String keyColumn) {
        return SnowflakeIds.getId();
    }

}
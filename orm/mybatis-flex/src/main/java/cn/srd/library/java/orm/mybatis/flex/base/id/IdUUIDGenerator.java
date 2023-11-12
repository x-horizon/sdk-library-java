// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.id;

import java.util.UUID;

/**
 * the snowflake type id generator
 *
 * @author wjm
 * @since 2023-11-12 21:06
 */
public class IdUUIDGenerator implements IdGenerator {

    private static final class SingleTonHolder {

        private static final IdUUIDGenerator INSTANCE = new IdUUIDGenerator();

    }

    public static IdUUIDGenerator getInstance() {
        return SingleTonHolder.INSTANCE;
    }

    @Override
    public Object generate(Object entity, String keyColumn) {
        return UUID.randomUUID().toString();
    }

}
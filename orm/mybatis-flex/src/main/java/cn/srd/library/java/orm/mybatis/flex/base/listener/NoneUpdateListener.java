// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.listener;

/**
 * @author wjm
 * @since 2023-11-13 21:14
 */
public class NoneUpdateListener implements UpdateListener<Void> {

    @Override
    public Class<Void> getEntityType() {
        return Void.class;
    }

    @Override
    public void action(Void entity) {

    }

}

package org.horizon.sdk.library.java.orm.mybatis.flex.mysql.config;

import org.horizon.sdk.library.java.orm.contract.mybatis.flex.model.bo.BaseBO;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.listener.BaseInsertListener;

import java.time.LocalDateTime;

public class TestInsertListener implements BaseInsertListener<BaseBO> {

    @Override
    public Class<BaseBO> getEntityType() {
        return BaseBO.class;
    }

    @Override
    public void action(BaseBO entity) {
        LocalDateTime now = LocalDateTime.now();
        entity.setCreatorId(1L)
                .setUpdaterId(1L)
                .setCreateTime(now)
                .setUpdateTime(now);

    }

}
package org.horizon.library.java.orm.mybatis.flex.postgis.config;

import org.horizon.library.java.orm.contract.mybatis.flex.model.bo.BaseBO;
import org.horizon.library.java.orm.mybatis.flex.base.listener.BaseInsertListener;

import java.time.LocalDateTime;

/**
 * @author wjm
 * @since 2024-07-24 09:27
 */
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
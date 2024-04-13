package cn.srd.library.java.orm.mybatis.flex.postgresql.config;

import cn.srd.library.java.orm.mybatis.flex.base.listener.BaseInsertListener;
import cn.srd.library.java.orm.mybatis.flex.postgresql.model.po.BasePO;

import java.time.LocalDateTime;

public class TestInsertListener implements BaseInsertListener<BasePO> {

    @Override
    public Class<BasePO> getEntityType() {
        return BasePO.class;
    }

    @Override
    public void action(BasePO entity) {
        LocalDateTime now = LocalDateTime.now();
        entity.setCreatorId(1L)
                .setUpdaterId(1L)
                .setCreateTime(now)
                .setUpdateTime(now);

    }

}
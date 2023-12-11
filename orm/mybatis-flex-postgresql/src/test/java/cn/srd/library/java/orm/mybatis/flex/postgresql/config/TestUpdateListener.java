package cn.srd.library.java.orm.mybatis.flex.postgresql.config;

import cn.srd.library.java.orm.mybatis.flex.base.listener.BaseUpdateListener;
import cn.srd.library.java.orm.mybatis.flex.postgresql.model.po.curd.BasePO;

import java.time.LocalDateTime;

public class TestUpdateListener implements BaseUpdateListener<BasePO> {

    @Override
    public Class<BasePO> getEntityType() {
        return BasePO.class;
    }

    @Override
    public void action(BasePO entity) {
        entity.setCreateTime(LocalDateTime.now()).setCreatorId(2L);
    }

}

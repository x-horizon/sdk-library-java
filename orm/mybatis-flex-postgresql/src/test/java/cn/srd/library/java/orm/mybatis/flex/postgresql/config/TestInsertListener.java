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
        entity.setCreateTime(LocalDateTime.now()).setCreatorId(1L);
    }

}

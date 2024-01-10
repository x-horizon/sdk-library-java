package cn.srd.library.java.orm.mybatis.flex.postgresql.config;

import cn.srd.library.java.orm.mybatis.flex.base.listener.BaseInsertListener;
import cn.srd.library.java.orm.mybatis.flex.postgresql.model.po.curd.version.BaseWithVersionPO;

import java.time.LocalDateTime;

public class TestInsertListener implements BaseInsertListener<BaseWithVersionPO> {

    @Override
    public Class<BaseWithVersionPO> getEntityType() {
        return BaseWithVersionPO.class;
    }

    @Override
    public void action(BaseWithVersionPO entity) {
        entity.setCreateTime(LocalDateTime.now()).setCreatorId(1L);
    }

}
package cn.srd.library.java.orm.mybatis.flex.postgresql.config;

import cn.srd.library.java.orm.mybatis.flex.base.listener.BaseUpdateListener;
import cn.srd.library.java.orm.mybatis.flex.postgresql.model.po.curd.version.BaseWithVersionPO;

import java.time.LocalDateTime;

public class TestUpdateListener implements BaseUpdateListener<BaseWithVersionPO> {

    @Override
    public Class<BaseWithVersionPO> getEntityType() {
        return BaseWithVersionPO.class;
    }

    @Override
    public void action(BaseWithVersionPO entity) {
        entity.setCreateTime(LocalDateTime.now()).setCreatorId(2L);
    }

}
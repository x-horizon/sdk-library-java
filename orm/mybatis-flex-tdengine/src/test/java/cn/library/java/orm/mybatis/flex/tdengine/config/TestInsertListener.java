package cn.library.java.orm.mybatis.flex.tdengine.config;

import cn.library.java.orm.contract.mybatis.flex.model.bo.BaseBO;
import cn.library.java.orm.mybatis.flex.base.listener.BaseInsertListener;

import java.time.LocalDateTime;

/**
 * @author wjm
 * @since 2023-11-04 00:19
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
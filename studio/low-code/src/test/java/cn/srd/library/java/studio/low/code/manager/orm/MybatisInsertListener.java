package cn.srd.library.java.studio.low.code.manager.orm;

import cn.srd.library.java.orm.contract.mybatis.flex.model.bo.BaseBO;
import cn.srd.library.java.orm.mybatis.flex.base.listener.BaseInsertListener;

import java.time.LocalDateTime;

/**
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
public class MybatisInsertListener implements BaseInsertListener<BaseBO> {

    @Override
    public Class<BaseBO> getEntityType() {
        return BaseBO.class;
    }

    @Override
    public void action(BaseBO entity) {
        LocalDateTime now = LocalDateTime.now();
        entity.setCreatorId(1L)
                .setCreateTime(now)
                .setUpdaterId(1L)
                .setUpdateTime(now);
    }

}
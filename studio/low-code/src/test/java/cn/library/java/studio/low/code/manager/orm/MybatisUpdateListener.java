package cn.library.java.studio.low.code.manager.orm;

import cn.library.java.orm.contract.mybatis.flex.model.bo.BaseBO;
import cn.library.java.orm.mybatis.flex.base.listener.BaseUpdateListener;

import java.time.LocalDateTime;

/**
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
public class MybatisUpdateListener implements BaseUpdateListener<BaseBO> {

    @Override
    public Class<BaseBO> getEntityType() {
        return BaseBO.class;
    }

    @Override
    public void action(BaseBO entity) {
        entity.setUpdaterId(1L).setUpdateTime(LocalDateTime.now());
    }

}
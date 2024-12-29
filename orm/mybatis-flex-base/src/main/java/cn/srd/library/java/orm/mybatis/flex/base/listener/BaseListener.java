package cn.srd.library.java.orm.mybatis.flex.base.listener;

import cn.srd.library.java.contract.constant.suppress.SuppressWarningConstant;
import com.mybatisflex.annotation.InsertListener;
import com.mybatisflex.annotation.UpdateListener;

/**
 * the base listener when operate database
 *
 * @param <P> the entity type
 * @author wjm
 * @since 2023-11-13 21:14
 */
public interface BaseListener<P> extends InsertListener, UpdateListener {

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    @Override
    default void onInsert(Object entity) {
        action((P) entity);
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    @Override
    default void onUpdate(Object entity) {
        action((P) entity);
    }

    /**
     * return the entity type
     *
     * @return the entity type
     */
    Class<P> getEntityType();

    /**
     * the action when operate database
     */
    void action(P entity);

}
package org.horizon.sdk.library.java.orm.mybatis.flex.base.listener;

import com.mybatisflex.annotation.InsertListener;
import com.mybatisflex.annotation.UpdateListener;
import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;

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
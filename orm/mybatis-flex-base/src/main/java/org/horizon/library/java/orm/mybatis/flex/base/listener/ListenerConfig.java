package org.horizon.library.java.orm.mybatis.flex.base.listener;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * the global listener config
 *
 * @author wjm
 * @since 2023-11-13 21:13
 */
@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface ListenerConfig {

    /**
     * <pre>
     * the action before data insert.
     *
     * example code:
     * {@code
     *     public class TestInsertListener implements BaseInsertListener<TestPO> {
     *
     *         @Override
     *         public Class<TestPO> getEntityType() {
     *             return TestPO.class;
     *         }
     *
     *         @Override
     *         public void action(TestPO entity) {
     *             entity.setCreateTime(LocalDateTime.now()).setCreatorId(1L);
     *         }
     *
     *     }
     * }
     * </pre>
     *
     * @return the action before data insert
     */
    Class<? extends BaseInsertListener> whenInsert() default UnsupportedInsertListener.class;

    /**
     * <pre>
     * the action before data update.
     *
     * example code:
     * {@code
     *     public class TestUpdateListener implements BaseUpdateListener<TestPO> {
     *
     *         @Override
     *         public Class<TestPO> getEntityType() {
     *             return TestPO.class;
     *         }
     *
     *         @Override
     *         public void action(TestPO entity) {
     *             entity.setUpdateTime(LocalDateTime.now()).setUpdaterId(1L);
     *         }
     *
     *     }
     * }
     * </pre>
     *
     * @return the action before data update
     */
    Class<? extends BaseUpdateListener> whenUpdate() default UnsupportedUpdateListener.class;

}
package cn.srd.itcp.sugar.mybatis.plus.core;

import cn.srd.itcp.sugar.tools.core.Objects;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * mybatis-plus 自动填充字段值，配合 {@link com.baomidou.mybatisplus.annotation.TableField} 使用
 * <pre>
 *    以下示例代码表示插入、更新、插入和更新时自动填充值：
 *
 *    {@code
 *        `@TableName(value = "table_name", autoResultMap = true)
 *         public class TableNamePO implements Serializable {
 *             private static final long serialVersionUID = -4579156082783453314L;
 *            `@TableLogic
 *            `@TableField(fill = FieldFill.INSERT)
 *             private Boolean deleteFlag;
 *            `@TableField(fill = FieldFill.INSERT)
 *             private LocalDateTime createTime;
 *            `@TableField(fill = FieldFill.INSERT_UPDATE)
 *             private LocalDateTime updateTime;
 *         }
 *    }
 * </pre>
 *
 * @author wjm
 * @date 2022-07-05
 */
public interface MybatisPlusMetaDataFiller extends MetaObjectHandler {

    /**
     * 创建时间字段的字段名
     *
     * @return
     */
    String getCreateTimeFieldName();

    /**
     * 更新时间字段的字段名
     *
     * @return
     */
    String getUpdateTimeFieldName();

    /**
     * 删除标识字段的字段名
     *
     * @return
     */
    String getDeleteFlagFieldName();

    /**
     * 插入表时填充属性
     *
     * @param metaObject
     */
    @Override
    default void insertFill(MetaObject metaObject) {
        LocalDateTime localDateTime = LocalDateTime.now();
        if (needToFill(metaObject, getCreateTimeFieldName())) {
            this.setFieldValByName(getCreateTimeFieldName(), localDateTime, metaObject);
        }
        if (needToFill(metaObject, getUpdateTimeFieldName())) {
            this.setFieldValByName(getUpdateTimeFieldName(), localDateTime, metaObject);
        }
        if (needToFill(metaObject, getDeleteFlagFieldName())) {
            this.setFieldValByName(getDeleteFlagFieldName(), false, metaObject);
        }
    }

    /**
     * 更新表时填充属性
     *
     * @param metaObject
     */
    @Override
    default void updateFill(MetaObject metaObject) {
        if (needToFill(metaObject, getUpdateTimeFieldName())) {
            setFieldValByName(getUpdateTimeFieldName(), LocalDateTime.now(), metaObject);
        }
    }

    private boolean needToFill(MetaObject metaObject, String fieldName) {
        if (metaObject.hasSetter(fieldName)) {
            return Objects.isEmpty(metaObject.getValue(fieldName));
        }
        return false;
    }

}

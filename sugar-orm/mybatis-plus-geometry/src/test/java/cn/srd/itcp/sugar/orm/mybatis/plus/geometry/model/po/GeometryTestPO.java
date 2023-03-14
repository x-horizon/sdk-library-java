package cn.srd.itcp.sugar.orm.mybatis.plus.geometry.model.po;

import cn.srd.itcp.sugar.orm.mybatis.plus.geometry.database.postgresql.handler.GeometryTypeHandler;
import cn.srd.itcp.sugar.orm.mybatis.plus.geometry.database.postgresql.support.PostgresqlGeometryToTextFunctionOnSelectSqlWrapper;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.locationtech.jts.geom.Geometry;

import java.io.Serial;
import java.io.Serializable;

/**
 * table geometry_test orm test
 *
 * @author wjm
 * @since 2023-03-14 15:39:11
 */
@Data
@Accessors(chain = true)
@TableName(value = "geometry_test", autoResultMap = true)
public class GeometryTestPO implements Serializable {

    @Serial
    private static final long serialVersionUID = -1462811800300035941L;

    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @TableField(value = "location_info", typeHandler = GeometryTypeHandler.class)
    @PostgresqlGeometryToTextFunctionOnSelectSqlWrapper
    private Geometry locationInfo;

}

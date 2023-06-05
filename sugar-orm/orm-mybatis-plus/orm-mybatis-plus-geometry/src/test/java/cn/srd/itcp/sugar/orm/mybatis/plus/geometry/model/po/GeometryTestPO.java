package cn.srd.itcp.sugar.orm.mybatis.plus.geometry.model.po;

import cn.srd.itcp.sugar.component.convert.jackson.geometry.support.JacksonGeometryToStringSerializer;
import cn.srd.itcp.sugar.component.convert.jackson.geometry.support.JacksonStringToGeometryDeserializer;
import cn.srd.itcp.sugar.orm.mybatis.plus.geometry.database.postgresql.handler.GeometryTypeHandler;
import cn.srd.itcp.sugar.orm.mybatis.plus.geometry.database.postgresql.support.PostgresqlGeometryToTextFunctionOnSelectSqlWrapper;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
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
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@TableName(value = "geometry_test", autoResultMap = true)
public class GeometryTestPO implements Serializable {

    @Serial
    private static final long serialVersionUID = -1462811800300035941L;

    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @TableField(value = "location_info", typeHandler = GeometryTypeHandler.class)
    @JsonDeserialize(converter = JacksonStringToGeometryDeserializer.class)
    @JsonSerialize(using = JacksonGeometryToStringSerializer.class)
    @PostgresqlGeometryToTextFunctionOnSelectSqlWrapper
    private Geometry locationInfo;

}

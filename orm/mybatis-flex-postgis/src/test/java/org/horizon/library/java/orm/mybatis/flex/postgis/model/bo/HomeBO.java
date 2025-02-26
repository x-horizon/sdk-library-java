package org.horizon.library.java.orm.mybatis.flex.postgis.model.bo;

import org.horizon.library.java.doc.knife4j.contract.constant.ApiDocConstant;
import org.horizon.library.java.orm.contract.mybatis.base.postgis.handler.JdbcGeometryMappingJavaGeometryTypeHandler;
import org.horizon.library.java.orm.contract.mybatis.flex.model.bo.BaseBO;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.locationtech.jts.geom.Geometry;

import java.io.Serial;

/**
 * @author wjm
 * @since 2024-07-24 09:27
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class HomeBO extends BaseBO {

    @Serial private static final long serialVersionUID = -3844167583281366035L;

    @Schema(description = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.NUMBER)
    @Column(value = "id")
    @Id
    private Long id;

    @Column(value = "name")
    private String name;

    @Column(value = "location", typeHandler = JdbcGeometryMappingJavaGeometryTypeHandler.class)
    private Geometry location;

}
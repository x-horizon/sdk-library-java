package org.horizon.sdk.library.java.orm.mybatis.flex.tdengine.model.bo;

import org.horizon.sdk.library.java.doc.knife4j.contract.constant.ApiDocConstant;
import org.horizon.sdk.library.java.orm.contract.mybatis.flex.model.bo.BaseBO;
import org.horizon.sdk.library.java.orm.mybatis.flex.tdengine.model.po.HomePO;
import org.horizon.sdk.library.java.orm.mybatis.flex.tdengine.model.vo.HomeVO;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * @author wjm
 * @since 2023-11-04 00:19
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@AutoMappers({@AutoMapper(target = HomePO.class), @AutoMapper(target = HomeVO.class)})
public class HomeBO extends BaseBO {

    @Serial private static final long serialVersionUID = -6137206413570817335L;

    @Schema(description = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.NUMBER)
    @Column(value = "id")
    @Id
    private Long id;

    @Column(value = "name")
    private String name;

}
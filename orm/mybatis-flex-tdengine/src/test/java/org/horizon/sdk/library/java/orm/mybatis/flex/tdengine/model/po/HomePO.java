package org.horizon.sdk.library.java.orm.mybatis.flex.tdengine.model.po;

import com.mybatisflex.annotation.Table;
import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.horizon.sdk.library.java.contract.model.base.PO;
import org.horizon.sdk.library.java.orm.mybatis.flex.tdengine.model.bo.HomeBO;
import org.horizon.sdk.library.java.orm.mybatis.flex.tdengine.model.vo.HomeVO;
import org.horizon.sdk.library.java.tool.convert.api.Converts;

import java.io.Serial;

/**
 * @author wjm
 * @since 2024-04-15 18:44
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@Table(value = "home")
@EqualsAndHashCode(callSuper = true)
@AutoMappers({@AutoMapper(target = HomeBO.class), @AutoMapper(target = HomeVO.class)})
public class HomePO extends HomeBO implements PO {

    @Serial private static final long serialVersionUID = -5698067987095394L;

    @Override
    public HomeVO toVO() {
        return Converts.onMapstruct().toBean(this, HomeVO.class);
    }

}
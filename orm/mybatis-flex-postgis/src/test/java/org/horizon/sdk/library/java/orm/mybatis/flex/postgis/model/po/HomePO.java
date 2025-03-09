package org.horizon.sdk.library.java.orm.mybatis.flex.postgis.model.po;

import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.horizon.sdk.library.java.contract.model.base.PO;
import org.horizon.sdk.library.java.contract.model.base.VO;
import org.horizon.sdk.library.java.orm.mybatis.flex.postgis.model.bo.HomeBO;

import java.io.Serial;

/**
 * @author wjm
 * @since 2024-07-24 09:27
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@Table(value = "home")
@EqualsAndHashCode(callSuper = true)
public class HomePO extends HomeBO implements PO {

    @Serial private static final long serialVersionUID = -5698067987095394L;

    @Override
    public VO toVO() {
        return null;
    }

}
package cn.srd.library.java.orm.mybatis.flex.postgresql.model.bo;

import cn.srd.library.java.orm.contract.model.generic.NameModel;
import cn.srd.library.java.orm.contract.mybatis.flex.model.bo.BaseBO;
import com.mybatisflex.annotation.Column;
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
public class HomeBO extends BaseBO implements NameModel {

    @Serial private static final long serialVersionUID = -6137206413570817335L;

    @Column(value = "name")
    private String name;

}
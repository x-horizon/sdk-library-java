package cn.srd.library.java.orm.mybatis.flex.postgresql.model.po.curd.version;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Table(value = "bed")
public class BedWithVersionPO extends BaseWithVersionPO {

    @Serial private static final long serialVersionUID = -4052782393689675979L;

    @Id
    @Column(value = "id")
    private Long id;

    @Column(value = "home_id")
    private Long homeId;

    @Column(value = "name")
    private String name;

}
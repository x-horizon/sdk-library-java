package cn.srd.library.java.orm.mybatis.flex.postgresql.model.po;

import cn.srd.library.java.orm.mybatis.flex.postgresql.model.po.curd.version.BaseWithVersionPO;
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
@Table(value = "curd_two_id")
public class CurdTwoIdWithVersionPO extends BaseWithVersionPO {

    @Serial private static final long serialVersionUID = -2963941116323035038L;

    @Id
    @Column(value = "id")
    private Long id;

    @Id
    @Column(value = "id2")
    private Long id2;

    @Column(value = "name")
    private String name;

}
package cn.srd.library.java.orm.mybatis.flex.postgresql.model.po;

import cn.srd.library.java.orm.mybatis.flex.postgresql.model.po.curd.BasePO;
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
@Table(value = "join_one")
public class JoinOnePO extends BasePO {

    @Serial private static final long serialVersionUID = 2292915698629113992L;

    @Id
    @Column(value = "id")
    private Long id;

    @Column(value = "join_two_id")
    private Long joinTwoId;

    @Column(value = "name")
    private String name;

}

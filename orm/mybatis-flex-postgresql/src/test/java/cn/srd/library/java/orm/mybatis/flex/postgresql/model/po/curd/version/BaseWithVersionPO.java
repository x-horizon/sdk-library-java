package cn.srd.library.java.orm.mybatis.flex.postgresql.model.po.curd.version;

import cn.srd.library.java.orm.mybatis.flex.postgresql.model.po.curd.BasePO;
import com.mybatisflex.annotation.Column;
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
public class BaseWithVersionPO extends BasePO {

    @Serial private static final long serialVersionUID = 2152316212528302390L;

    @Column(value = "version", version = true)
    private Long version;

}
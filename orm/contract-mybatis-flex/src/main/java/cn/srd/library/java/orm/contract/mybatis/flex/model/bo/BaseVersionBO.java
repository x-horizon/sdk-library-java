package cn.srd.library.java.orm.contract.mybatis.flex.model.bo;

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
public class BaseVersionBO extends BaseBO {

    @Serial private static final long serialVersionUID = 2152316212528302390L;

    @Column(value = "version", version = true)
    private Long version;

}
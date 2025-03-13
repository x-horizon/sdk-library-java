package org.horizon.sdk.library.java.orm.contract.model.base;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.horizon.sdk.library.java.contract.model.base.POJO;
import org.horizon.sdk.library.java.orm.contract.model.page.PageParam;

import java.io.Serial;

/**
 * @author wjm
 * @since 2024-04-17 10:42
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class BasePageConditionVO extends PageParam implements POJO {

    @Serial private static final long serialVersionUID = 2083935475405702780L;

}
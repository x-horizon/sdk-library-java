package org.horizon.sdk.library.java.tool.lang.validation.model.po;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.horizon.sdk.library.java.contract.model.base.PO;
import org.horizon.sdk.library.java.contract.model.base.VO;
import org.horizon.sdk.library.java.tool.lang.validation.model.bo.UserBO;

import java.io.Serial;

@Schema(name = "用户信息")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class UserPO extends UserBO implements PO {

    @Serial private static final long serialVersionUID = 664477221446789L;

    @Override
    public VO toVO() {
        return null;
    }

}
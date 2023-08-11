package cn.srd.sugar.tool.lang.core.pieline;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class TenantVO implements Serializable {

    @Serial private static final long serialVersionUID = 1546320085406573801L;

    private Boolean legalPass;
    private Boolean loginPass;
    private Boolean authorizationPass;

    public static TenantVO ofLegalPass() {
        return TenantVO.builder()
                .legalPass(true)
                .loginPass(false)
                .authorizationPass(false)
                .build();
    }

    public static TenantVO ofLoginPass() {
        return TenantVO.builder()
                .legalPass(false)
                .loginPass(true)
                .authorizationPass(false)
                .build();
    }

    public static TenantVO ofAuthorizationPass() {
        return TenantVO.builder()
                .legalPass(false)
                .loginPass(false)
                .authorizationPass(true)
                .build();
    }

    public static TenantVO ofAllPass() {
        return TenantVO.builder()
                .legalPass(true)
                .loginPass(true)
                .authorizationPass(true)
                .build();
    }

}
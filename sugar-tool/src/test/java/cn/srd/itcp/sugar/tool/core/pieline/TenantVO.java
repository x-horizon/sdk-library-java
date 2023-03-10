package cn.srd.itcp.sugar.tool.core.pieline;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TenantVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1546320085406573801L;

    private Boolean legalPass;
    private Boolean loginPass;
    private Boolean authorizationPass;

    public static TenantVO ofLegalPass() {
        return new TenantVO()
                .setLegalPass(true)
                .setLoginPass(false)
                .setAuthorizationPass(false);
    }

    public static TenantVO ofLoginPass() {
        return new TenantVO()
                .setLegalPass(false)
                .setLoginPass(true)
                .setAuthorizationPass(false);
    }

    public static TenantVO ofAuthorizationPass() {
        return new TenantVO()
                .setLegalPass(false)
                .setLoginPass(false)
                .setAuthorizationPass(true);
    }

    public static TenantVO ofAllPass() {
        return new TenantVO()
                .setLegalPass(true)
                .setLoginPass(true)
                .setAuthorizationPass(true);
    }

}
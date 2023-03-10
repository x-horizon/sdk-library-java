package cn.srd.itcp.sugar.tool.core.pieline;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class CustomerVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 402799623781524456L;

    private Boolean legalPass;
    private Boolean loginPass;
    private Boolean authorizationPass;

    public static CustomerVO ofLegalPass() {
        return new CustomerVO()
                .setLegalPass(true)
                .setLoginPass(false)
                .setAuthorizationPass(false);
    }

    public static CustomerVO ofLoginPass() {
        return new CustomerVO()
                .setLegalPass(false)
                .setLoginPass(true)
                .setAuthorizationPass(false);
    }

    public static CustomerVO ofAuthorizationPass() {
        return new CustomerVO()
                .setLegalPass(false)
                .setLoginPass(false)
                .setAuthorizationPass(true);
    }

    public static CustomerVO ofAllPass() {
        return new CustomerVO()
                .setLegalPass(true)
                .setLoginPass(true)
                .setAuthorizationPass(true);
    }

}
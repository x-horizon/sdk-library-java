package cn.srd.itcp.sugar.tool.core.pieline;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class UserVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 402799623781524456L;

    private Boolean legalPass;
    private Boolean loginPass;
    private Boolean authorizationPass;

    public static UserVO ofLegalPass() {
        return new UserVO()
                .setLegalPass(true)
                .setLoginPass(false)
                .setAuthorizationPass(false);
    }

    public static UserVO ofLoginPass() {
        return new UserVO()
                .setLegalPass(false)
                .setLoginPass(true)
                .setAuthorizationPass(false);
    }

    public static UserVO ofAuthorizationPass() {
        return new UserVO()
                .setLegalPass(false)
                .setLoginPass(false)
                .setAuthorizationPass(true);
    }

    public static UserVO ofAllPass() {
        return new UserVO()
                .setLegalPass(true)
                .setLoginPass(true)
                .setAuthorizationPass(true);
    }

}
package cn.srd.library.tool.lang.core.pieline;

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
public class UserVO implements Serializable {

    @Serial private static final long serialVersionUID = 402799623781524456L;

    private Boolean legalPass;
    private Boolean loginPass;
    private Boolean authorizationPass;

    public static UserVO ofLegalPass() {
        return UserVO.builder()
                .legalPass(true)
                .loginPass(false)
                .authorizationPass(false)
                .build();
    }

    public static UserVO ofLoginPass() {
        return UserVO.builder()
                .legalPass(false)
                .loginPass(true)
                .authorizationPass(false)
                .build();
    }

    public static UserVO ofAuthorizationPass() {
        return UserVO.builder()
                .legalPass(false)
                .loginPass(false)
                .authorizationPass(true)
                .build();
    }

    public static UserVO ofAllPass() {
        return UserVO.builder()
                .legalPass(true)
                .loginPass(true)
                .authorizationPass(true)
                .build();
    }

}
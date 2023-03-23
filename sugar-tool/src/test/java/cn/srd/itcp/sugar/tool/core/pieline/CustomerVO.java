package cn.srd.itcp.sugar.tool.core.pieline;

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
public class CustomerVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 402799623781524456L;

    private Boolean legalPass;
    private Boolean loginPass;
    private Boolean authorizationPass;

    public static CustomerVO ofLegalPass() {
        return CustomerVO.builder()
                .legalPass(true)
                .loginPass(false)
                .authorizationPass(false)
                .build();
    }

    public static CustomerVO ofLoginPass() {
        return CustomerVO.builder()
                .legalPass(false)
                .loginPass(true)
                .authorizationPass(false)
                .build();
    }

    public static CustomerVO ofAuthorizationPass() {
        return CustomerVO.builder()
                .legalPass(false)
                .loginPass(false)
                .authorizationPass(true)
                .build();
    }

    public static CustomerVO ofAllPass() {
        return CustomerVO.builder()
                .legalPass(true)
                .loginPass(true)
                .authorizationPass(true)
                .build();
    }

}
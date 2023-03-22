package cn.srd.itcp.sugar.tool.core.pieline;

import cn.srd.itcp.sugar.tool.core.asserts.Assert;
import cn.srd.itcp.sugar.tool.core.pipeline.NodeConsumer2;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class CheckTenantAndUserAuthorizationNode implements NodeConsumer2<TenantVO, UserVO> {

    private static final class SingleTonHolder {
        private static final CheckTenantAndUserAuthorizationNode INSTANCE = new CheckTenantAndUserAuthorizationNode();
    }

    public static CheckTenantAndUserAuthorizationNode getInstance() {
        return SingleTonHolder.INSTANCE;
    }

    @Override
    public void handle(TenantVO tenantVO, UserVO userVO) {
        Assert.INSTANCE.set("租户鉴权不通过").throwsIfFalse(tenantVO.getAuthorizationPass());
        Assert.INSTANCE.set("用户鉴权不通过").throwsIfFalse(userVO.getAuthorizationPass());
    }

}

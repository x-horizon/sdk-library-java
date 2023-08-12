package cn.srd.library.tool.lang.core.pieline;

import cn.srd.library.tool.lang.core.asserts.Assert;
import cn.srd.library.tool.lang.core.pipeline.NodeConsumer2;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@Setter
@NoArgsConstructor
@Accessors(chain = true)
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

package cn.srd.itcp.sugar.tool.core.pieline;

import cn.srd.itcp.sugar.tool.core.asserts.Assert;
import cn.srd.itcp.sugar.tool.core.pipeline.NodeConsumer2;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Accessors(chain = true)
public class CheckTenantAndUserLoginNode implements NodeConsumer2<TenantVO, UserVO> {

    private static final class SingleTonHolder {
        private static final CheckTenantAndUserLoginNode INSTANCE = new CheckTenantAndUserLoginNode();
    }

    public static CheckTenantAndUserLoginNode getInstance() {
        return SingleTonHolder.INSTANCE;
    }

    @Override
    public void handle(TenantVO tenantVO, UserVO userVO) {
        Assert.INSTANCE.set("租户未登录").throwsIfFalse(tenantVO.getLoginPass());
        Assert.INSTANCE.set("用户未登录").throwsIfFalse(tenantVO.getLoginPass());
    }

}

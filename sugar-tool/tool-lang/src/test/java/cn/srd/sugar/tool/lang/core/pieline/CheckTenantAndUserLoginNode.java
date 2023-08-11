package cn.srd.sugar.tool.lang.core.pieline;

import cn.srd.sugar.tool.lang.core.asserts.Assert;
import cn.srd.sugar.tool.lang.core.pipeline.NodeConsumer2;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@Setter
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
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

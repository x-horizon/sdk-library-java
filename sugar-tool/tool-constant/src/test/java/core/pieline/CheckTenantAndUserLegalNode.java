package core.pieline;

import cn.srd.itcp.sugar.tool.core.asserts.Assert;
import cn.srd.itcp.sugar.tool.core.pipeline.NodeConsumer2;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@Setter
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class CheckTenantAndUserLegalNode implements NodeConsumer2<TenantVO, UserVO> {

    private static final class SingleTonHolder {
        private static final CheckTenantAndUserLegalNode INSTANCE = new CheckTenantAndUserLegalNode();
    }

    public static CheckTenantAndUserLegalNode getInstance() {
        return SingleTonHolder.INSTANCE;
    }

    @Override
    public void handle(TenantVO tenantVO, UserVO userVO) {
        Assert.INSTANCE.set("租户不合法").throwsIfFalse(tenantVO.getLegalPass());
        Assert.INSTANCE.set("用户不合法").throwsIfFalse(tenantVO.getLegalPass());
    }

}

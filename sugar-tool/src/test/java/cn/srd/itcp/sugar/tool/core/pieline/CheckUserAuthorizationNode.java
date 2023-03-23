package cn.srd.itcp.sugar.tool.core.pieline;

import cn.srd.itcp.sugar.tool.core.asserts.Assert;
import cn.srd.itcp.sugar.tool.core.pipeline.NodeConsumer1;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@Setter
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class CheckUserAuthorizationNode implements NodeConsumer1<UserVO> {

    private static final class SingleTonHolder {
        private static final CheckUserAuthorizationNode INSTANCE = new CheckUserAuthorizationNode();
    }

    public static CheckUserAuthorizationNode getInstance() {
        return SingleTonHolder.INSTANCE;
    }

    @Override
    public void handle(UserVO userVO) {
        Assert.INSTANCE.set("用户鉴权不通过").throwsIfFalse(userVO.getAuthorizationPass());
    }

    public static boolean enable(UserVO userVO) {
        return true;
    }

}

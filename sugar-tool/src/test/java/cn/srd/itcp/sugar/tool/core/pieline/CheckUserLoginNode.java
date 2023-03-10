package cn.srd.itcp.sugar.tool.core.pieline;

import cn.srd.itcp.sugar.tool.core.asserts.Assert;
import cn.srd.itcp.sugar.tool.core.pipeline.NodeConsumer1;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Accessors(chain = true)
public class CheckUserLoginNode implements NodeConsumer1<UserVO> {

    private static final class SingleTonHolder {
        private static final CheckUserLoginNode INSTANCE = new CheckUserLoginNode();
    }

    public static CheckUserLoginNode getInstance() {
        return SingleTonHolder.INSTANCE;
    }

    @Override
    public void handle(UserVO userVO) {
        Assert.INSTANCE.set("用户未登录").throwsIfFalse(userVO.getLoginPass());
    }

}

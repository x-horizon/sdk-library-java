package cn.srd.itcp.sugar.tool.core.pieline;

import cn.srd.itcp.sugar.tool.core.asserts.Assert;
import cn.srd.itcp.sugar.tool.core.pipeline.NodeConsumer1;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class CheckUserLegalNode implements NodeConsumer1<UserVO> {

    private static final class SingleTonHolder {
        private static final CheckUserLegalNode INSTANCE = new CheckUserLegalNode();
    }

    public static CheckUserLegalNode getInstance() {
        return SingleTonHolder.INSTANCE;
    }

    @Override
    public void handle(UserVO userVO) {
        Assert.INSTANCE.set("用户不合法").throwsIfFalse(userVO.getLegalPass());
    }

}

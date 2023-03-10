package cn.srd.itcp.sugar.tool.core.pieline;

import cn.srd.itcp.sugar.tool.core.pipeline.Pipeline;

public class Main {

    public static void main(String[] args) {
        UserVO userVO = UserVO.ofLegalPass();
        TenantVO tenantVO = TenantVO.ofAllPass();
        Pipeline.getInstance()
                .<UserVO>ofChainConsumer1()
                .addNode(CheckUserAuthorizationNode.getInstance(), CheckUserAuthorizationNode::enable, userVO)
                .addNode(CheckUserLegalNode.getInstance())
                .addNode(CheckUserLoginNode.getInstance())
                .execute(userVO)
                .<TenantVO, UserVO>ofChainConsumer2()
                .addNode(CheckTenantAndUserAuthorizationNode.getInstance())
                .addNode(CheckTenantAndUserLegalNode.getInstance())
                .addNode(CheckTenantAndUserLoginNode.getInstance())
                .execute(tenantVO, userVO)
        ;
    }

}

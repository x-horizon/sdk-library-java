package cn.srd.itcp.sugar.security.sa.token.webmvc.tmp;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.context.SaTokenContext;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

/**
 * TODO wjm 修改 javax 为 Jakarta，暂时用于兼容 JDK 17，后续官方版本支持后去掉
 *
 * @author wjm
 * @since 2023-01-28 00:29:11
 */
@Component
public class SaTokenContextHackerForSpringMVC {

    /**
     * 注入自定义 {@link SaTokenContext}
     */
    @PostConstruct
    public void init() {
        SaTokenContext saTokenContext = new SaTokenContextForSpringMVC();
        SaManager.setSaTokenContext(saTokenContext);
    }

}

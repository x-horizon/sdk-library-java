package cn.srd.itcp.sugar.component.actor.thingsboard.core;

import cn.srd.itcp.sugar.framework.spring.tool.common.core.SpringsUtil;
import cn.srd.itcp.sugar.tool.core.ClassesUtil;
import cn.srd.itcp.sugar.tool.core.CollectionsUtil;
import cn.srd.itcp.sugar.tool.core.Objects;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * default actor system
 *
 * @author wjm
 * @since 2023-03-20 11:04:19
 */
@Component
public class DefaultActorSystem implements ActorSystem {

    /**
     * all actor system inherit from {@link ActorSystem}
     */
    private static Set<Class<? extends ActorSystem>> ACTOR_SYSTEMS;

    @PostConstruct
    @Override
    public void init() {
        ACTOR_SYSTEMS = CollectionsUtil.filter(ClassesUtil.scanPackagesBySuper(new String[]{SpringsUtil.getRootPackagePath()}, ActorSystem.class), item -> Objects.notEquals(DefaultActorSystem.class, item));
        ACTOR_SYSTEMS.forEach(item -> SpringsUtil.getBean(item).init());
    }

    @PreDestroy
    @Override
    public void destroy() {
        ACTOR_SYSTEMS.forEach(item -> SpringsUtil.getBean(item).destroy());
    }

}
